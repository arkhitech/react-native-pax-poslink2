package com.paxposlink2

import android.content.Context
import android.util.Log
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.pax.poslinkperipheries.cashdrawer.POSLinkCashDrawer

import com.paxposlink2.NativePaxPoslink2CashDrawerSpec

class PaxPoslink2CashDrawerModule(reactContext: ReactApplicationContext) :
    NativePaxPoslink2CashDrawerSpec(reactContext) {
    private val context: Context = reactContext

    override fun getName(): String {
        return NAME
    }

    override fun open(promise: Promise) {
        try {
            val posLinkCashDrawer = POSLinkCashDrawer.getInstance(this.context)
            val processResult = posLinkCashDrawer.open()
            promise.resolve(processResult.message)
        } catch (e: Exception) {
            Log.e("PaxPOSLink", e.message!!)
            promise.reject("Exception", e.message)
        }
    }

    override fun cashBoxStatus(promise: Promise) {
        try {
            val posLinkCashDrawer = POSLinkCashDrawer.getInstance(this.context)
            val status = posLinkCashDrawer.cashBoxStatus()
            promise.resolve(status.toString() + "")
        } catch (e: Exception) {
            Log.e("PaxPOSLink", e.message!!)
            promise.reject("Exception", e.message)
        }
    }

    companion object {
        const val NAME: String = "PaxPoslink2CashDrawer"
    }
}
