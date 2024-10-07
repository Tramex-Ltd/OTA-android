package com.tramexmeters.mex5updater.home_screen.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tramexmeters.mex5updater.R
import com.tramexmeters.mex5updater.base.viewmodels.ScannerViewModel
import com.tramexmeters.mex5updater.bluetooth.ble.BluetoothDeviceInfo
import com.tramexmeters.mex5updater.bluetooth.ble.ManufacturerDataFilter
import com.tramexmeters.mex5updater.bluetooth.ble.ScanResultCompat
import com.tramexmeters.mex5updater.bluetooth.services.BluetoothService


class SelectDeviceViewModel : ScannerViewModel() {

    private val _scannedDevices: MutableLiveData<MutableMap<String, BluetoothDeviceInfo>> =
        MutableLiveData(mutableMapOf())
    val scannedDevices: LiveData<MutableMap<String, BluetoothDeviceInfo>> = _scannedDevices
    private val _deviceToInsert: MutableLiveData<BluetoothDeviceInfo> = MutableLiveData()
    val deviceToInsert: LiveData<BluetoothDeviceInfo> = _deviceToInsert
    private val _numberOfDevices: MutableLiveData<Int> = MutableLiveData(0)
    val numberOfDevices: LiveData<Int> = _numberOfDevices

    override fun handleScanResult(
        result: ScanResultCompat,
        connectType: BluetoothService.GattConnectType?,
        context: Context?
    ) {
        val manufacturerDataFilter = ManufacturerDataFilter(
            id = 71,
            data = byteArrayOf(2, 0)
        )

        synchronized(_scannedDevices) {
            val deviceName = result.device?.name
            var shouldAddDevice = true
            if (deviceName == null) {
                shouldAddDevice = false
            }

            if (connectType != null && connectType == BluetoothService.GattConnectType.BLINKY) {
                if (deviceName != null) {
                    if (context != null) {
                        if (!deviceName.startsWith(context.getString(R.string.blinky_service_name),ignoreCase = true)
                            &&  !matchesManufacturerData(result, manufacturerDataFilter)) {
                            shouldAddDevice = false
                        }
                    }
                }
            }
            if (shouldAddDevice) {
                _scannedDevices.value?.apply {
                    val address = result.device?.address!!
                    val isNewDevice = !keys.contains(address)

                    getOrPut(address, { BluetoothDeviceInfo(result.device!!) }).also {
                        updateScanInfo(it, result)
                    }

                    if (isNewDevice) {
                        _deviceToInsert.value = this[address]
                        _numberOfDevices.value = size
                    }
                }
            }
            _isAnyDeviceDiscovered.value = _scannedDevices.value?.isNotEmpty() ?: false
        }


    }

    fun clearDevices() {
        _scannedDevices.postValue(mutableMapOf())
        _numberOfDevices.postValue(0)
    }

    fun getScannedDevicesList() : List<BluetoothDeviceInfo> {
        return _scannedDevices.value?.values?.toList() ?: listOf()
    }

    fun matchesManufacturerData(result: ScanResultCompat, filter: ManufacturerDataFilter): Boolean {
        val manufacturerData = result.scanRecord?.getManufacturerSpecificData(filter.id)
        return manufacturerData != null && manufacturerData.contentEquals(filter.data)
    }

}