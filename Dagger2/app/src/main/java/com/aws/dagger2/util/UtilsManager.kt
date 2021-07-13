package com.aws.dagger2.util

import android.util.Log

class UtilsManager {

    companion object {
        fun log(TAG: String, message: String) {
            Log.e(TAG, message)
        }
    }

}