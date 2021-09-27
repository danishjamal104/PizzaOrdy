package com.github.danishjamal104.pizzaordy.utils

import android.content.Context
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * sets the color of the status bar
 * @param [color] int color reference to be applied
 */
fun Window.setStatusColor(@ColorInt color: Int) {
    this.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    this.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    this.statusBarColor = color
}

/**
 * Displays short toast
 */
fun Fragment.shortToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

/**
 * Displays long toast
 */
fun Fragment.longToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}


fun Context.showDefaultMaterialAlert(
    title: String,
    message: String,
    positiveButtonPress: () -> Unit
) {
    MaterialAlertDialogBuilder(this)
        .setTitle(title).setMessage(message)
        .setPositiveButton("Yes") { _, _ -> positiveButtonPress() }
        .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }.create().show()
}

/**
 * sets visibility of [View] to [View.GONE]
 */
fun View?.hide() {
    this?.apply {
        if (visibility != View.GONE) {
            this.visibility = View.GONE
        }
    }
}

/**
 * sets visibility of [View] to [View.VISIBLE]
 */
fun View?.show() {
    this?.apply {
        if (visibility != View.VISIBLE) {
            this.visibility = View.VISIBLE
        }
    }
}