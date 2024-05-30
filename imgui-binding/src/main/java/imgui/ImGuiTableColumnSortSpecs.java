package imgui;

import imgui.binding.ImGuiStruct;

public final class ImGuiTableColumnSortSpecs extends ImGuiStruct {

    public ImGuiTableColumnSortSpecs(final long ptr) {
        super(ptr);
    }

    /*JNI
        #include "_common.h"

        #define IMGUI_TABLE_COLUMN_SORT_SPECS ((ImGuiTableColumnSortSpecs*)STRUCT_PTR)
     */

    public native int getColumnUserId(); /*
        return IMGUI_TABLE_COLUMN_SORT_SPECS->ColumnUserID;
    */

    public native int getColumnIndex(); /*
        return IMGUI_TABLE_COLUMN_SORT_SPECS->ColumnIndex;
    */

    public native int getSortOrder(); /*
        return IMGUI_TABLE_COLUMN_SORT_SPECS->SortOrder;
    */

    public native int getSortDirection(); /*
        return IMGUI_TABLE_COLUMN_SORT_SPECS->SortDirection;
    */
}
