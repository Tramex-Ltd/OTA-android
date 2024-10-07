package com.tramexmeters.mex5updater.features.scan.browser.view_states

import com.tramexmeters.mex5updater.home_screen.viewmodels.ScanFragmentViewModel

data class GraphFragmentViewState(
        val isScanningOn: Boolean,
        val labelsInfo: List<ScanFragmentViewModel.LabelViewState>,
        val graphInfo: List<ScanFragmentViewModel.GraphDeviceState>,
        val highlightedLabel: ScanFragmentViewModel.LabelViewState?,
        val scanTimestamp: Long
)
