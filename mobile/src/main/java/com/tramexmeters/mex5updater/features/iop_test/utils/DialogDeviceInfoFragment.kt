package com.tramexmeters.mex5updater.features.iop_test.utils

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.tramexmeters.mex5updater.databinding.DialogDeviceInfoLayoutBinding

class DialogDeviceInfoFragment : DialogFragment() {
    var title: String? = null
    var message: String? = null
    var positiveButtonText: String? = null
    var negativeButtonText: String? = null
    var positiveClickListener: DialogInterface.OnClickListener? = null
    var negativeClickListener: DialogInterface.OnClickListener? = null
    lateinit var binding: DialogDeviceInfoLayoutBinding

    private var dialogShowing = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogDeviceInfoLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Set title and message
        binding.dialogTitle.text = title
        binding.dialogMessage.text = message

        // Set positive button click listener
        binding.btnPositive.setOnClickListener {
            positiveClickListener?.onClick(dialog, DialogInterface.BUTTON_POSITIVE)
            dismiss()
        }

        // Set negative button click listener
        binding.btnNegative.setOnClickListener {
            negativeClickListener?.onClick(dialog, DialogInterface.BUTTON_NEGATIVE)
            dismiss()
        }

        // Set button texts
        binding.btnPositive.text = positiveButtonText
        binding.btnNegative.text = negativeButtonText

    }
    /*override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())

        builder.setTitle(title)
        builder.setMessage(message)

        if (positiveButtonText != null) {
            builder.setPositiveButton(positiveButtonText, positiveClickListener)
        }

        if (negativeButtonText != null) {
            builder.setNegativeButton(negativeButtonText, negativeClickListener)
        }

        val dialog = builder.create()
        dialog.setOnShowListener {
            dialogShowing = true
        }
        dialog.setOnDismissListener {
            dialogShowing = false
        }

        return dialog
    }*/

    // Builder pattern for setting dialog properties
    class Builder {
        private val fragment = DialogDeviceInfoFragment()

        fun setTitle(title: String?): Builder {
            fragment.title = title
            return this
        }

        fun setMessage(message: String?): Builder {
            fragment.message = message
            return this
        }

        fun setPositiveButton(text: String?, listener: DialogInterface.OnClickListener?): Builder {
            fragment.positiveButtonText = text
            fragment.positiveClickListener = listener
            return this
        }

        fun setNegativeButton(text: String?, listener: DialogInterface.OnClickListener?): Builder {
            fragment.negativeButtonText = text
            fragment.negativeClickListener = listener
            return this
        }

        fun build(): DialogDeviceInfoFragment {
            return fragment
        }
    }

    fun isShowing(): Boolean {
        return dialogShowing
    }
}