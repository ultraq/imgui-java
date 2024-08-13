package imgui;

import imgui.binding.ImGuiStruct;

/**
 * Sorting specification for one column of a table
 */
public final class ImGuiTableColumnSortSpecs extends ImGuiStruct {

    public ImGuiTableColumnSortSpecs(final long ptr) {
        super(ptr);
    }

    /*JNI
        #include "_common.h"

        #define IMGUI_TABLE_COLUMN_SORT_SPECS ((ImGuiTableColumnSortSpecs*)STRUCT_PTR)
     */

    /**
     * User id of the column (if specified by a TableSetupColumn() call)
     */
    public native int getColumnUserId(); /*
        return IMGUI_TABLE_COLUMN_SORT_SPECS->ColumnUserID;
    */

    /**
     * Index of the column
     */
    public native int getColumnIndex(); /*
        return IMGUI_TABLE_COLUMN_SORT_SPECS->ColumnIndex;
    */

    /**
     * Index within parent ImGuiTableSortSpecs (always stored in order starting from 0, tables sorted on a single criteria will always have a 0 here)
     */
    public native int getSortOrder(); /*
        return IMGUI_TABLE_COLUMN_SORT_SPECS->SortOrder;
    */

    /**
     * ImGuiSortDirection_Ascending or ImGuiSortDirection_Descending (you can use this or SortSign, whichever is more convenient for your sort function)
     */
    public native int getSortDirection(); /*
        return IMGUI_TABLE_COLUMN_SORT_SPECS->SortDirection;
    */
}
