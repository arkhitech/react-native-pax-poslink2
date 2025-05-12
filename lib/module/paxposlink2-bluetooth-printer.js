import { NativeModules, Platform } from 'react-native';
const LINKING_ERROR = `The package 'react-native-pax-poslink2' doesn't seem to be linked. Make sure: \n\n${
  Platform.select({
    ios: "- You have run 'pod install'\n",
    default: ''
  })}- You rebuilt the app after installing the package\n- You are not using Expo Go\n`;
const PaxPoslink2BluetoothPrinter = NativeModules.PaxPoslink2BluetoothPrinter ? NativeModules.PaxPoslink2BluetoothPrinter : new Proxy({}, {
  get() {
    throw new Error(LINKING_ERROR);
  }
});

export const setPaxBluetoothPrinterMacAddress = (macAddress) => (
  PaxPoslink2BluetoothPrinter.setMacAddress(macAddress)
);

export const printPaxBluetoothPrinterImageBase64 = (base64Image, cutMode) => (
  PaxPoslink2BluetoothPrinter.printImageBase64(base64Image, Number(cutMode))
);

export const cutPaxBluetoothPrinterPaper = (cutMode) => (
  PaxPoslink2BluetoothPrinter.cutPaper(Number(cutMode))
);

export const disconnectPaxBluetoothPrinter = () => (
  PaxPoslink2BluetoothPrinter.disconnect()
);
