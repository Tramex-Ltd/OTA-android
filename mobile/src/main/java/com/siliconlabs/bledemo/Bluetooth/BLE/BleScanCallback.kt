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


//        fun ByteArray.toHexString(): String = joinToString("") { "%02x".format(it) }

        result?.scanRecord?.manufacturerSpecificData?.let { manufacturerData ->
//             Parse manufacturerData (it's a SparseArray<ParcelUuid, ByteArray>)

            val advertisingBytes = result?.scanRecord?.bytes
            if(manufacturerData.size() > 0 && manufacturerData.keyAt(0) == 1450){
                Timber.d("YAY")
//                Timber.d(advertisingBytes)

                for (b in advertisingBytes) {
                    Timber.d(advertisingBytes.keyAt(i).toString())
                }

                service.handleScanCallback(ScanResultCompat.from(result))
            }
//
//            for (i in 0 until manufacturerData.size()) {
//                val manufacturerId = manufacturerData.keyAt(i)
//                val data = manufacturerData.valueAt(i)
//                // Analyze manufacturerId and data
//                // ... (Logic to identify manufacturer based on ID and data)
//
//                if (manufacturerId === 1450) {
//
//                    Timber.d("YAY")
//                    Timber.d(String.format("manu str len:%d",manufacturerData))
//                    service.handleScanCallback(ScanResultCompat.from(result))
//                }
//            }
//
//
            // manufacturerId.startsWith("05AA") // Example: Bluetooth SIG manufacturer ID
//            Toast.makeText( requireContext(), manufacturerData.toString(), Toast.LENGTH_SHORT).show()
//            Toast.makeText(applicationContext, manufacturerData.toString(), Toast.LENGTH_SHORT).show()

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