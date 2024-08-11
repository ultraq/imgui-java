package imgui;

import imgui.binding.ImGuiStruct;

/**
 * Sorting specifications for a table (often handling sort specs for a single column, occasionally more)
 * Obtained by calling TableGetSortSpecs().
 * When 'SpecsDirty == true' you can sort your data. It will be true with sorting specs have changed since last call, or the first time.
 * Make sure to set 'SpecsDirty = false' after sorting, else you may wastefully sort your data every frame!
 */
public final class ImGuiTableSortSpecs extends ImGuiStruct {

    private static final ImGuiTableColumnSortSpecs COLUMN_SORT_SPECS = new ImGuiTableColumnSortSpecs(0);

    public ImGuiTableSortSpecs(final long ptr) {
        super(ptr);
    }

    /*JNI
        #include "_common.h"

        #define IMGUI_TABLE_SORT_SPECS ((ImGuiTableSortSpecs*)STRUCT_PTR)
     */

    /**
     * Pointer to sort spec array.
     */
    // Currently support just 1 sorting column
    public ImGuiTableColumnSortSpecs getSpecs() {
        COLUMN_SORT_SPECS.ptr = nGetSpecs();
        return COLUMN_SORT_SPECS;
    }

    /**
     * Sort spec count. Most often 1. May be > 1 when ImGuiTableFlags_SortMulti is enabled. May be == 0 when ImGuiTableFlags_SortTristate is enabled.
     */
    private native long nGetSpecs(); /*
        return (intptr_t)IMGUI_TABLE_SORT_SPECS->Specs;
    */

    public native int getSpecsCount(); /*
        return IMGUI_TABLE_SORT_SPECS->SpecsCount;
    */

    /**
     * Set to true when specs have changed since last time! Use this to sort again, then clear the flag.
     */
    public native boolean getSpecsDirty(); /*
        return IMGUI_TABLE_SORT_SPECS->SpecsDirty;
    */

    public native void setSpecsDirty(boolean dirty); /*
        IMGUI_TABLE_SORT_SPECS->SpecsDirty = dirty;
    */
}
