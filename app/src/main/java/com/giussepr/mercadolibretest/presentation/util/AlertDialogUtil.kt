package com.giussepr.mercadolibretest.presentation.util

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.giussepr.mercadolibretest.R

object AlertDialogUtil {

    fun generalDialog(
        context: Context,
        title: String? = null,
        message: String,
        isCancelable: Boolean = true,
        textButtonAccept: String = context.resources.getString(R.string.retry),
        textButtonCancel: String = context.resources.getString(R.string.cancel),
        action: () -> Unit
    ) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setCancelable(isCancelable)
            .setMessage(message)
            .setNegativeButton(textButtonCancel) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(textButtonAccept) { dialog, _ ->
                dialog.dismiss()
                action()
            }
            .show()
    }
}
