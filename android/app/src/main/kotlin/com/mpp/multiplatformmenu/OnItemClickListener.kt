package com.mpp.multiplatformmenu

import android.view.View
import android.widget.TextView
import android.widget.Toast

object OnItemClickListener : View.OnClickListener {
    override fun onClick(v: View?) {
        v?.also {
            Toast.makeText(it.context, (v as TextView).text, Toast.LENGTH_LONG).show()
        }
    }
}