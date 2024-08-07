package com.picpay.desafio.android.logger

import android.annotation.SuppressLint
import android.util.Log
import timber.log.Timber

@SuppressLint("LogNotTimber")
class DebugTimberTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        when (priority) {
            Log.DEBUG -> {
                processDebug(tag, message)
            }
            Log.ERROR -> {
                processError(tag, message, t)
            }
            Log.INFO -> {
                processInfo(tag, message)
            }
            Log.VERBOSE -> {
                processVerbose(tag, message)
            }
            Log.WARN -> {
                processWarn(tag, message)
            }
        }
    }

    private fun processDebug(tag: String?, message: String) {
        Log.d(tag, message)
    }

    private fun processInfo(tag: String?, message: String) {
        Log.i(tag, message)
    }

    private fun processVerbose(tag: String?, message: String) {
        Log.v(tag, message)
    }

    private fun processWarn(tag: String?, message: String) {
        Log.w(tag, message)
    }

    private fun processError(tag: String?, message: String, error: Throwable?) {
        Log.e(tag, message, error)
    }
}