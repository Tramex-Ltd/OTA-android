package com.tramexmeters.mex5updater.base.activities

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.tramexmeters.mex5updater.R
import com.tramexmeters.mex5updater.base.dialogs.ProgressDialogWithSpinner
import timber.log.Timber
abstract class BaseActivity : AppCompatActivity() {
    enum class ConnectionStatus {
        CONNECTING, READING_DEVICE_STATE
    }

    private var connectionStatusModalDialog: ProgressDialogWithSpinner? = null
    private var isDialogVisible = false // Custom flag for dialog visibility

    @JvmOverloads
    fun showModalDialog(
        connStat: ConnectionStatus? = null,
        cancelListener: () -> Unit = {},
    ) {
        dismissModalDialog()
        runOnUiThread {
            val dialogMessage = when(connStat) {
                ConnectionStatus.CONNECTING ->
                    R.string.demo_connection_progress_dialog_connecting
                ConnectionStatus.READING_DEVICE_STATE ->
                    R.string.demo_connection_progress_dialog_reading_device_state
                else -> R.string.demo_connection_progress_dialog_loading
            }

            // note that the dialog state is never shown when disconnecting from a device
            connectionStatusModalDialog = ProgressDialogWithSpinner(
                caption = dialogMessage,
                onCancelAction = cancelListener,
            )

            if (!this@BaseActivity.isFinishing) {
                connectionStatusModalDialog?.show(supportFragmentManager, null)
                isDialogVisible = true
            }
        }
    }

    fun setModalDialogMessage(@StringRes message: Int) {
        runOnUiThread { connectionStatusModalDialog?.setCaption(message) }
    }

    fun dismissModalDialog() {
        runOnUiThread {
            if (isDialogVisible) {
                Timber.e("Attempting to dismiss the dialog")
                connectionStatusModalDialog?.dismiss()
                connectionStatusModalDialog = null
                isDialogVisible = false // Update the visibility flag after dismissing
            } else {
                Timber.e("Dialog is not visible, cannot dismiss")
            }

        }
    }

    fun showMessage(message: String) {
        runOnUiThread { Toast.makeText(this, message, Toast.LENGTH_SHORT).show() }
    }

    fun showMessage(stringResId: Int) {
        runOnUiThread { Toast.makeText(this, stringResId, Toast.LENGTH_SHORT).show() }
    }

    fun showLongMessage(message: String) {
        runOnUiThread { Toast.makeText(this, message, Toast.LENGTH_LONG).show() }
    }

    fun hideKeyboard() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = this.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) view = View(this)
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}