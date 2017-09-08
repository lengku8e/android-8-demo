package com.example.sunhailong01.demo

import android.content.Context
import android.view.View
import android.widget.Toast

/**
 * Created by sunhailong01 on 17/9/5.
 */
fun Context.longToast(mssage : String) {
    Toast.makeText(this, mssage, Toast.LENGTH_LONG).show()
}


fun Context.toast(message: CharSequence, time : Int =  Toast.LENGTH_LONG) {
    Toast.makeText(this, message, time)
}

fun View.setHeight(height: Int) {
    val params = layoutParams
    params.height = height
    layoutParams = params
}

fun View.visible() {
    visibility = View.VISIBLE
}
fun View.gone() {
    visibility = View.GONE
}
