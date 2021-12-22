package tool.generator

import groovy.io.FileType
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import spoon.Launcher
import spoon.SpoonException
import spoon.reflect.declaration.CtClass
import tool.generator.api.Metadata

class GenerateApi extends DefaultTask {
    @Internal
    String group = 'build'
    @Internal
    String description = 'Generate API for native binaries.'

    private static final GroovyShell SHELL = new GroovyShell(GenerateApi.getClassLoader())

    static {
        SHELL.setVariable('metadata', Metadata::create)
    }

    @TaskAction
    void generate() {
        println 'Generating API...'

        println 'Removing old generated files...'
        project.file('build/generated/api').deleteDir()

        println 'Copying raw sources...'
        project.copy {
            it.from('src/main/java')
            it.into('build/generated/api')
        }

        println 'Processing raw sources...'
        project.file('build/generated/api').eachFileRecurse(FileType.FILES) {
            try {
                processClass(Launcher.parseClass(it.text))
            } catch (SpoonException ignored) {
                println " - Skipping: $it"
            }
        }
    }

    static void processClass(CtClass<?> ctClass) {
        def genMetadataValue = findGenMetadataValue(ctClass)

        if (!genMetadataValue) {
            return
        }

        println " + ${ctClass.qualifiedName} => $genMetadataValue"

        def metadata = SHELL.evaluate(readResourceFile(genMetadataValue))
        println()
    }

    static String findGenMetadataValue(CtClass<?> ctClass) {
        for (it in ctClass.annotations) {
            if (it.annotationType.simpleName == 'ApiMetadata') {
                return it.getValue('value').toString().with {
                    'metadata/' + it.subSequence(1, it.length() - 1)
                }
            }
        }
        return null
    }

    static String readResourceFile(String fileName) {
        GenerateApi.getClassLoader().getResourceAsStream(fileName).text
    }
}
