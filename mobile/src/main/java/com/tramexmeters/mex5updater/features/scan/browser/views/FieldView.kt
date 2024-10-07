package com.tramexmeters.mex5updater.features.scan.browser.views

import android.content.Context
import com.tramexmeters.mex5updater.bluetooth.data_types.Field

abstract class FieldView(
        context: Context?,
        protected val field: Field,
        fieldValue: ByteArray
) : ValueView(context, fieldValue)