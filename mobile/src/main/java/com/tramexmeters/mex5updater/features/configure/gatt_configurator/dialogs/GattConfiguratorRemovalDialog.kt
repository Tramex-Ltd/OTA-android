package com.tramexmeters.mex5updater.features.configure.gatt_configurator.dialogs

import androidx.annotation.StringRes
import com.tramexmeters.mex5updater.utils.RemovalDialog
import com.tramexmeters.mex5updater.features.configure.gatt_configurator.utils.GattConfiguratorStorage

class GattConfiguratorRemovalDialog(
    @StringRes name: Int,
    onOkClicked: () -> Unit
) : RemovalDialog(name, onOkClicked) {

    override fun blockDisplayingRemovalDialog() {
        context?.let { GattConfiguratorStorage(it).setDisplayRemovalDialog(false) }
    }
}
