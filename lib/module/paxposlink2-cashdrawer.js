"use strict";

import { NativeModules, Platform } from 'react-native';
const LINKING_ERROR = `The package 'react-native-pax-poslink2' doesn't seem to be linked. Make sure: \n\n${Platform.select({
  ios: "- You have run 'pod install'\n",
  default: ''
})}- You rebuilt the app after installing the package\n- You are not using Expo Go\n`;
const PaxPoslink2CashDrawer = NativeModules.PaxPoslink2CashDrawer ? NativeModules.PaxPoslink2CashDrawer : new Proxy({}, {
  get() {
    throw new Error(LINKING_ERROR);
  }
});
export const getPaxBluetoothPrinterCashBoxStatus = () => PaxPoslink2CashDrawer.cashBoxStatus();
export const openPaxBluetoothPrinter = () => PaxPoslink2CashDrawer.open();
//# sourceMappingURL=paxposlink2-cashdrawer.js.map