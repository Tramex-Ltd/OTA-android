package com.tramexmeters.mex5update.features.scan.browser.adapters

import com.tramexmeters.mex5update.features.scan.browser.models.Mapping

interface MappingCallback {
    fun onNameChanged(mapping: Mapping)
}
