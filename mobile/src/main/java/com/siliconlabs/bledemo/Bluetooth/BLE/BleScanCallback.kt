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

    // override fun onScanResult(callbackType: Int, result: ScanResult) {
    //     Timber.d( "onScanResult: $result")
    //     service.handleScanCallback(ScanResultCompat.from(result))
    // }

//    fun ByteArray.toHexString(): String = joinToString("") { "%02x".format(it) }

    override fun onScanResult(callbackType: Int, result: ScanResult?) {
        super.onScanResult(callbackType, result);

        result?.scanRecord?.manufacturerSpecificData?.let { manufacturerData ->
            val advertisingBytes = result?.scanRecord?.bytes
            if(manufacturerData.size() > 0 && manufacturerData.keyAt(0) == 1450){
                Timber.d("YAY")

                advertisingBytes?.let { bytes ->
                    if (bytes.size >= 25 && bytes[25].toInt() == 82) { // Check if the array has at least 11 bytes
                        service.handleScanCallback(ScanResultCompat.from(result))
                        //                        val byte10 = bytes[25] // Access the 11th byte (index 10)
//                        Timber.d(byte10.toString(16).padStart(2, '0'))
//                        Timber.d("%02x".format(byte10.toInt() and 0xFF))
                        Timber.d(bytes[25].toString())
                        // ... use byte10 ...
                    }
                }

//                advertisingBytes?.let {  // Use let block to handle null case
//                    var counter = 0
//                    for (b in it) {
//                        counter ++
//                        Timber.d(counter.toString())
////                        Timber.d(b.toString(16).padStart(2, '0'))
//                        Timber.d("%02x".format(b.toInt() and 0xFF))
//                    }
//                }
//                service.handleScanCallback(ScanResultCompat.from(result))
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