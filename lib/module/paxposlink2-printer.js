import { NativeModules, Platform } from 'react-native';
const LINKING_ERROR = `The package 'react-native-pax-poslink2' doesn't seem to be linked. Make sure: \n\n${
  Platform.select({
    ios: "- You have run 'pod install'\n",
    default: ''
  })}- You rebuilt the app after installing the package\n- You are not using Expo Go\n`;
const PaxPoslink2Printer = NativeModules.PaxPoslink2Printer ? NativeModules.PaxPoslink2Printer : new Proxy({}, {
  get() {
    throw new Error(LINKING_ERROR);
  }
});

export const printPaxPrinterImageBase64 = (base64Image, cutMode, printWidth) => (
  PaxPoslink2Printer.printImageBase64(base64Image, Number(cutMode), printWidth)
);

export const cutPaxPrinterPaper = (cutMode) => (
  PaxPoslink2Printer.cutPaper(Number(cutMode))
);
