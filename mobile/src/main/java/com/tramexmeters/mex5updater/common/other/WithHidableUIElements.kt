package com.tramexmeters.mex5updater.common.other

import android.content.Context

interface WithHidableUIElements {
    fun getLayoutManagerWithHidingUIElements(context: Context?): LinearLayoutManagerWithHidingUIElements
}