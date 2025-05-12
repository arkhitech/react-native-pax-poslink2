package com.paxposlink2

import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ViewManager

class PaxPoslink2Package : ReactPackage {
    override fun createNativeModules(reactContext: ReactApplicationContext): List<NativeModule> {
        val modules: MutableList<NativeModule> = ArrayList()
        modules.add(PaxPoslink2Module(reactContext))
        modules.add(PaxPoslink2BluetoothPrinterModule(reactContext))
        modules.add(PaxPoslink2CashDrawerModule(reactContext))
        modules.add(PaxPoslink2ScannerModule(reactContext))
        return modules
    }

    override fun createViewManagers(reactContext: ReactApplicationContext): List<ViewManager<*, *>> {
        return emptyList()
    }
}
