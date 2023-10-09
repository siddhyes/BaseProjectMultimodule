package com.nst.baseproject.common.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun FragmentActivity.showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}

fun Context.showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}


fun View.showSnackbar(message: String, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, message, length).show()
}

fun View.showSnackbarWithAction(
    message: String,
    actionText: String,
    length: Int = Snackbar.LENGTH_INDEFINITE,
    action: (() -> Unit)? = null
) {
    val snackbar = Snackbar.make(this, message, length)
    if (action != null) {
        snackbar.setAction(actionText) { action.invoke() }
    }
    snackbar.show()
}

fun EditText.addDelayedTextListener(
    coroutineScope: CoroutineScope = MainScope(),
    delayMillis: Long = 300L,
    onTextChanged: (String) -> Unit
): TextWatcher {
    var job: Job? = null
    val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            job?.cancel()
            job = coroutineScope.launch {
                delay(delayMillis)
                s?.toString()?.let {
                    //if (it.isNotEmpty()) {
                    onTextChanged(it)
                    //}
                }
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    }
    addTextChangedListener(textWatcher)
    return textWatcher
}

fun AutoCompleteTextView.onSelected(action: (position: Int) -> Unit) {
    this.setOnItemClickListener { _, _, position, _ ->
        action(position)
    }


}
fun AppCompatActivity.getDrawableResource(id: Int): Drawable? {
    return  AppCompatResources.getDrawable(this.applicationContext,id)
}