package imgui;

import imgui.binding.ImGuiStruct;

public final class ImGuiTableSortSpecs extends ImGuiStruct {

    private static final ImGuiTableColumnSortSpecs COLUMN_SORT_SPECS = new ImGuiTableColumnSortSpecs(0);

    public ImGuiTableSortSpecs(final long ptr) {
        super(ptr);
    }

    /*JNI
        #include "_common.h"

        #define IMGUI_TABLE_SORT_SPECS ((ImGuiTableSortSpecs*)STRUCT_PTR)
     */

    // Currently support just 1 sorting column
    public ImGuiTableColumnSortSpecs getSpecs() {
        COLUMN_SORT_SPECS.ptr = nGetSpecs();
        return COLUMN_SORT_SPECS;
    }

    private native long nGetSpecs(); /*
        return (intptr_t)IMGUI_TABLE_SORT_SPECS->Specs;
    */

    public native int getSpecsCount(); /*
        return IMGUI_TABLE_SORT_SPECS->SpecsCount;
    */

    public native boolean getSpecsDirty(); /*
        return IMGUI_TABLE_SORT_SPECS->SpecsDirty;
    */

    public native void setSpecsDirty(boolean dirty); /*
        IMGUI_TABLE_SORT_SPECS->SpecsDirty = dirty;
    */
}
