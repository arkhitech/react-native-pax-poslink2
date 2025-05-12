package com.paxposlink2

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.pax.poslinkperipheries.ProcessResult
import com.pax.poslinkperipheries.printer.POSLinkBluetoothPrinter
import com.pax.poslinkperipheries.printer.POSLinkPrinter

import com.paxposlink2.NativePaxPoslink2BluetoothPrinterSpec
//import com.pax.poslink.peripheries.POSLinkBluetoothPrinter
//import com.pax.poslink.peripheries.POSLinkPrinter
//import com.pax.poslink.peripheries.ProcessResult

class PaxPoslink2BluetoothPrinterModule(reactContext: ReactApplicationContext) :
    NativePaxPoslink2BluetoothPrinterSpec(reactContext) {
    private val context: Context = reactContext

    override fun getName(): String {
        return NAME
    }

    override fun setMacAddress(macAddress: String, promise: Promise) {
        try {
            val posLinkBluetoothPrinter = POSLinkBluetoothPrinter.getInstance(
                this.context
            )
            posLinkBluetoothPrinter.setMacAddress(macAddress)
            promise.resolve("applied")
        } catch (e: Exception) {
            Log.e("PaxPOSLink", e.message!!)
            promise.reject("Exception", e.message)
        }
    }

    override fun printImageBase64(base64Image: String, cutMode: Double, promise: Promise) {
        try {
            val printListener: POSLinkPrinter.PrintListener =
                object : POSLinkPrinter.PrintListener {
                    override fun onSuccess() {
                        promise.resolve("success")
                    }

                    override fun onError(processResult: ProcessResult) {
                        promise.reject(processResult.code, processResult.message)
                    }
                }
            val decodedString = Base64.decode(base64Image, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

            val posLinkBluetoothPrinter = POSLinkBluetoothPrinter.getInstance(
                this.context
            )
            posLinkBluetoothPrinter.print(bitmap, cutMode.toInt(), printListener)
        } catch (e: Exception) {
            Log.e("PaxPOSLink", e.message!!)
            promise.reject("Exception", e.message)
        }
    }

    override fun cutPaper(cutMode: Double, promise: Promise) {
        try {
            val posLinkBluetoothPrinter = POSLinkBluetoothPrinter.getInstance(
                this.context
            )
            val response = posLinkBluetoothPrinter.cutPaper(cutMode.toInt())
            promise.resolve("" + response)
        } catch (e: Exception) {
            Log.e("PaxPOSLink", e.message!!)
            promise.reject("Exception", e.message)
        }
    }

    override fun disconnect(promise: Promise) {
        try {
            val posLinkBluetoothPrinter = POSLinkBluetoothPrinter.getInstance(
                this.context
            )
            posLinkBluetoothPrinter.disconnectPrinter()
            promise.resolve("disconnected")
        } catch (e: Exception) {
            Log.e("PaxPOSLink", e.message!!)
            promise.reject("Exception", e.message)
        }
    }

    companion object {
        const val NAME: String = "PaxPoslink2BluetoothPrinter"
    }
}
