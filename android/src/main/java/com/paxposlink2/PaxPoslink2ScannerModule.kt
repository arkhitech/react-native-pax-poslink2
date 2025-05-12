package com.paxposlink2

import android.content.Context
import android.util.Log
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.pax.poslink.entity.ScanResult
import com.pax.poslink.peripheries.POSLinkScanner

class PaxPoslink2ScannerModule(reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {
    private val context: Context = reactContext

    override fun getName(): String {
        return NAME
    }

    @ReactMethod
    fun open(scannerType: String?, promise: Promise) {
        try {
            val posLinkScanner = POSLinkScanner.getPOSLinkScanner(this.context, scannerType)
            val processResult = posLinkScanner.open()
            promise.resolve(processResult.code, processResult.message)
        } catch (e: Exception) {
            Log.e("PaxPOSLink", e.message!!)
            promise.reject("Exception", e.message)
        }
    }

    @ReactMethod
    fun close(scannerType: String?, promise: Promise) {
        try {
            val posLinkScanner = POSLinkScanner.getPOSLinkScanner(this.context, scannerType)
            val processResult = posLinkScanner.close()
            promise.resolve(processResult.code, processResult.message)
        } catch (e: Exception) {
            Log.e("PaxPOSLink", e.message!!)
            promise.reject("Exception", e.message)
        }
    }

    @ReactMethod
    fun start(scannerType: String?, promise: Promise) {
        try {
            val posLinkScanner = POSLinkScanner.getPOSLinkScanner(this.context, scannerType)
            posLinkScanner.start(object : POSLinkScanner.IScanListener {
                override fun onRead(scanResult: ScanResult) {
                    val map = Arguments.createMap()
                    map.putString("Content", scanResult.content)
                    map.putString("Format", scanResult.format)
                    promise.resolve(map)
                }

                override fun onFinish() {
                    Log.i("PaxPOSLink", "Scanner onFinish")
                }
            })
        } catch (e: Exception) {
            Log.e("PaxPOSLink", e.message!!)
            promise.reject("Exception", e.message)
        }
    }

    companion object {
        const val NAME: String = "PaxPoslink2Scanner"
    }
}
