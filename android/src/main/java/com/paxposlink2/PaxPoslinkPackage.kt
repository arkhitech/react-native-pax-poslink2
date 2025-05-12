package com.paxposlink2;

import com.facebook.react.BaseReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.model.ReactModuleInfo
import com.facebook.react.module.model.ReactModuleInfoProvider

class CalculatorPackage : BaseReactPackage() {
  override fun getModule(name: String, reactContext: ReactApplicationContext): NativeModule? =
    if (name == PaxPoslink2BluetoothPrinterModule.NAME) {
      PaxPoslink2BluetoothPrinterModule(reactContext)
    } else if(name == PaxPoslink2CashDrawerModule.NAME) {
      PaxPoslink2CashDrawerModule(reactContext)
    } else if(name == PaxPoslink2PaymentModule.NAME) {
      PaxPoslink2PaymentModule(reactContext)
    } else if(name == PaxPoslink2PrinterModule.NAME) {
      PaxPoslink2PrinterModule(reactContext)
    } else if(name == PaxPoslink2ScannerModule.NAME) {
      PaxPoslink2ScannerModule(reactContext)
    } else {
      null
    }

  override fun getReactModuleInfoProvider() = ReactModuleInfoProvider {
    mapOf(
      PaxPoslink2BluetoothPrinterModule.NAME to ReactModuleInfo(
        PaxPoslink2BluetoothPrinterModule.NAME,
        PaxPoslink2BluetoothPrinterModule.NAME,
        false, // canOverrideExistingModule
        false, // needsEagerInit
        false, // isCxxModule
        true // isTurboModule
      ),
      PaxPoslink2PrinterModule.NAME to ReactModuleInfo(
        PaxPoslink2PrinterModule.NAME,
        PaxPoslink2PrinterModule.NAME,
        false, // canOverrideExistingModule
        false, // needsEagerInit
        false, // isCxxModule
        true // isTurboModule
      ),
      PaxPoslink2CashDrawerModule.NAME to ReactModuleInfo(
        PaxPoslink2CashDrawerModule.NAME,
        PaxPoslink2CashDrawerModule.NAME,
        false, // canOverrideExistingModule
        false, // needsEagerInit
        false, // isCxxModule
        true // isTurboModule
      ),
      PaxPoslink2PaymentModule.NAME to ReactModuleInfo(
        PaxPoslink2PaymentModule.NAME,
        PaxPoslink2PaymentModule.NAME,
        false, // canOverrideExistingModule
        false, // needsEagerInit
        false, // isCxxModule
        true // isTurboModule
      ),
      PaxPoslink2ScannerModule.NAME to ReactModuleInfo(
        PaxPoslink2ScannerModule.NAME,
        PaxPoslink2ScannerModule.NAME,
        false, // canOverrideExistingModule
        false, // needsEagerInit
        false, // isCxxModule
        true // isTurboModule
      )
    )
  }
}