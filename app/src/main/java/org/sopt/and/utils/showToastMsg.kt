package org.sopt.and.utils

import android.content.Context
import android.widget.Toast

fun Context.showToastMsg(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}