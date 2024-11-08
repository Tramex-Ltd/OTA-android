package com.tramexmeters.mex5update.features.configure.gatt_configurator.dialogs

import androidx.annotation.StringRes
import com.tramexmeters.mex5update.utils.RemovalDialog
import com.tramexmeters.mex5update.features.configure.gatt_configurator.utils.GattConfiguratorStorage

class GattConfiguratorRemovalDialog(
    @StringRes name: Int,
    onOkClicked: () -> Unit
) : RemovalDialog(name, onOkClicked) {

    override fun blockDisplayingRemovalDialog() {
        context?.let { GattConfiguratorStorage(it).setDisplayRemovalDialog(false) }
    }
}
