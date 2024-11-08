package com.tramexmeters.mex5update.features.scan.browser.view_states

import com.tramexmeters.mex5update.home_screen.viewmodels.ScanFragmentViewModel

data class ScannerFragmentViewState(
        val isScanningOn: Boolean,
        val devicesToShow: List<ScanFragmentViewModel.BluetoothInfoViewState>
)
