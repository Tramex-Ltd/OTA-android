package com.siliconlabs.bledemo.bluetooth.ble

import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContentProviderCompat.requireContext
import com.siliconlabs.bledemo.bluetooth.services.BluetoothService
import timber.log.Timber
import java.util.HexFormat

/** Callback returning bluetooth devices of types: LE, dual, unrecognized. Used when the scanning
 * device supports LE feature.
 */
class BleScanCallback(private val service: BluetoothService) : ScanCallback() {
    private val handler: Handler = Handler(Looper.getMainLooper())

    override fun onScanResult(callbackType: Int, result: ScanResult?) {
        super.onScanResult(callbackType, result);

        result?.scanRecord?.manufacturerSpecificData?.let { manufacturerData ->
            val advertisingBytes = result?.scanRecord?.bytes
            if(manufacturerData.size() > 0 && manufacturerData.keyAt(0) == 1450){
                //Timber.d("YAY")
                advertisingBytes?.let { bytes ->
                    if (bytes.size >= 25 && bytes[25].toInt() == 82) { // Check if the array has at least 25 bytes
                        service.handleScanCallback(ScanResultCompat.from(result))// Only handle callback on the device we want to update
                    }
                }
            }
        }
    }

    override fun onBatchScanResults(results: List<ScanResult>) {
        results.forEach {
            Timber.d("onBatchScanResults: $it")
            service.handleScanCallback(ScanResultCompat.from(it))
        }
    }

    override fun onScanFailed(errorCode: Int) {
        if (errorCode != SCAN_FAILED_ALREADY_STARTED) {
            handler.post { service.onDiscoveryFailed(BluetoothService.ScanError.ScannerError, errorCode) }
        }
    }

}
