package com.aws.dagger2.util

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.aws.dagger2.R

/**
 * Created by Rahul Gaur on 11, December, 2019
 * Email: rahul@appwebstudios.com
 */
class ProgressManager {

    private var aDProgress: AlertDialog? = null

    fun showProgressDialog(context: Context?, progress: String?) {
        try {
            val builder = AlertDialog.Builder(context!!)
            @SuppressLint("InflateParams") val view =
                LayoutInflater.from(context).inflate(R.layout.pop_up_custom_progress, null)
            builder.setCancelable(false)
            builder.setView(view)
            val tvProgress = view.findViewById<TextView>(R.id.tvProgress)
            tvProgress.text = progress
            aDProgress = builder.create()
            aDProgress!!.window!!
                .setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            aDProgress!!.setCanceledOnTouchOutside(false)
            aDProgress!!.setCancelable(false)
            try {
                aDProgress!!.show()
            } catch (e: Exception) {
                e.printStackTrace()
                UtilsManager.log(
                    "ProgressManager",
                    "showProgressDialog: exception showing progress bar ${e.message}"
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(
                "ProgressManager",
                "showProgressDialog: exception in progress dialog " + e.message
            )
        }
    }

    fun dismissDialog() {
        if (aDProgress != null) {
            try {
                aDProgress!!.dismiss()
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ProgressManager", "dismissDialog: exception with progressbar ")
            }
        }
    }
}