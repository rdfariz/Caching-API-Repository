package org.d3if4127.miwokapp

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter

@BindingAdapter("isLoading")
fun isLoading(progressBar: ProgressBar, bool: Boolean) {
    progressBar.bringToFront()
    if (bool) { progressBar.visibility = View.VISIBLE }
    else { progressBar.visibility = View.INVISIBLE }
}