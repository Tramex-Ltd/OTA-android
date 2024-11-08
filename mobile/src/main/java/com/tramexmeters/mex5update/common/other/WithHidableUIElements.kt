package com.tramexmeters.mex5update.common.other

import android.content.Context

interface WithHidableUIElements {
    fun getLayoutManagerWithHidingUIElements(context: Context?): LinearLayoutManagerWithHidingUIElements
}