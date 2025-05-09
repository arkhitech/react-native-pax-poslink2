package com.paxposlink2

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.annotations.ReactModule

@ReactModule(name = PaxPoslink2Module.NAME)
class PaxPoslink2ModuleTemp(reactContext: ReactApplicationContext) :
  NativePaxPoslink2Spec(reactContext) {

  override fun getName(): String {
    return NAME
  }

  // Example method
  // See https://reactnative.dev/docs/native-modules-android
  override fun multiply(a: Double, b: Double): Double {
    return a * b
  }

  companion object {
    const val NAME = "PaxPoslink2Temp"
  }
}
