package tool.generator.api

class Metadata {
    String filePath
    String pointName

    static Metadata create(Closure closure) {
        def m = new Metadata()
        closure.delegate = m
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure()
        return m
    }
}
