import { NativeModules, Platform } from 'react-native';
const LINKING_ERROR = `The package 'react-native-pax-poslink2' doesn't seem to be linked. Make sure: \n\n${
  Platform.select({
    ios: "- You have run 'pod install'\n",
    default: ''
  })}- You rebuilt the app after installing the package\n- You are not using Expo Go\n`;
const PaxLinkUpPrinter = NativeModules.PaxLinkUpPrinter ? NativeModules.PaxLinkUpPrinter : new Proxy({}, {
  get() {
    throw new Error(LINKING_ERROR);
  }
});

export const getPaxLinkUpPrinterDeviceList = (testMode) => (
  PaxLinkUpPrinter.getPrinterDeviceList(Boolean(testMode))
);

export const printPaxLinkUpPrinterImageBase64 = (printerDeviceId, printerComponentId, base64Image, imageWidth, imageHeight, cutMode, testMode) => (
  PaxLinkUpPrinter.printImageBase64(printerDeviceId, printerComponentId, base64Image, Number(imageWidth), Number(imageHeight), Number(cutMode), Boolean(testMode))
);

export const printPaxLinkUpPrinterImageUrl = (printerDeviceId, printerComponentId, imageUrl, imageWidth, imageHeight, cutMode, testMode) => (
  PaxLinkUpPrinter.printImageUrl(printerDeviceId, printerComponentId, imageUrl, Number(imageWidth), Number(imageHeight), Number(cutMode), Boolean(testMode))
);

export const printPaxLinkUpPrinterRawCommands = (printerDeviceId, printerComponentId, base64Strings, testMode) => (
  PaxLinkUpPrinter.printRawCommands(printerDeviceId, printerComponentId, base64Strings, Boolean(testMode))
);
