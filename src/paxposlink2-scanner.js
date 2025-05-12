import { NativeModules, Platform } from 'react-native';
const LINKING_ERROR = `The package 'react-native-pax-poslink2' doesn't seem to be linked. Make sure: \n\n${
  Platform.select({
    ios: "- You have run 'pod install'\n",
    default: ''
  })}- You rebuilt the app after installing the package\n- You are not using Expo Go\n`;
const PaxPoslink2Scanner = NativeModules.PaxPoslink2Scanner ? NativeModules.PaxPoslink2Scanner : new Proxy({}, {
  get() {
    throw new Error(LINKING_ERROR);
  }
});

export const openPaxScanner = (scannerType) => (
  PaxPoslink2Scanner.open(scannerType)
);

export const closePaxScanner = (scannerType) => (
  PaxPoslink2Scanner.close(scannerType)
);

export const startPaxScanner = (scannerType) => (
  PaxPoslink2Scanner.start(scannerType)
);