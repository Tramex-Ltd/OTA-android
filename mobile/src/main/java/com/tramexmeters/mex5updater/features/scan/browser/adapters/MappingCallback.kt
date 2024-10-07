package com.tramexmeters.mex5updater.features.scan.browser.adapters

import com.tramexmeters.mex5updater.features.scan.browser.models.Mapping

interface MappingCallback {
    fun onNameChanged(mapping: Mapping)
}
