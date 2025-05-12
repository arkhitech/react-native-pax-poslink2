package com.paxposlink2

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
//import com.pax.poslink.peripheries.POSLinkPrinter
//import com.pax.poslink.peripheries.ProcessResult
import com.pax.poslinkperipheries.ProcessResult
import com.pax.poslinkperipheries.printer.POSLinkPrinter

class PaxPoslink2PrinterModule(reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {
    private val context: Context = reactContext

    override fun getName(): String {
        return NAME
    }

    @ReactMethod
    fun printImageBase64(base64Image: String?, cutMode: Int, printWidth: Int, promise: Promise) {
        val printListener: POSLinkPrinter.PrintListener = object : POSLinkPrinter.PrintListener {
            override fun onSuccess() {
                promise.resolve("success")
            }

            override fun onError(processResult: ProcessResult) {
                promise.reject(processResult.code, processResult.message)
            }
        }
        val decodedString = Base64.decode(base64Image, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

        val posLinkPrinter = POSLinkPrinter.getInstance(this.context)
        val isSync = false
        posLinkPrinter.setPrintWidth(printWidth)
        posLinkPrinter.print(bitmap, cutMode, printListener, isSync)
    }

    @ReactMethod
    fun cutPaper(cutMode: Int, promise: Promise) {
        try {
            val posLinkPrinter = POSLinkPrinter.getInstance(this.context)
            val response = posLinkPrinter.cutPaper(cutMode)
            promise.resolve("" + response)
        } catch (e: Exception) {
            Log.e("PaxPOSLink", e.message!!)
            promise.reject("Exception", e.message)
        }
    }

    companion object {
        const val NAME: String = "PaxPoslink2Printer"
    }
}
