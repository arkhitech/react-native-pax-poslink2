import { NativeModules, Platform } from 'react-native';
const LINKING_ERROR = `The package 'react-native-pax-poslink2' doesn't seem to be linked. Make sure: \n\n${
  Platform.select({
    ios: "- You have run 'pod install'\n",
    default: ''
  })}- You rebuilt the app after installing the package\n- You are not using Expo Go\n`;
const PaxPoslink2 = NativeModules.PaxPoslink2Printer ? NativeModules.PaxPoslink2Printer : new Proxy({}, {
  get() {
    throw new Error(LINKING_ERROR);
  }
});

export const initPOSLink = (type, timeout, nameOrMac, ipOrSerial, portOrBaud) => {
  return PaxPoslink2.initPOSLink(type, timeout, nameOrMac, ipOrSerial, portOrBaud);
}

export const initPaymentCommunication = (type, timeout, nameOrMac, ipOrSerial, portOrBaud) => {
  return PaxPoslink2.initPaymentCommunication(type, timeout, nameOrMac, ipOrSerial, portOrBaud);
}

export const makeCreditPayment = (amount, tip, referenceNumber) => {
  return PaxPoslink2.makeCreditPayment(amount, tip, referenceNumber); 
}

export const makeCashPayment = (amount, tip, referenceNumber) => {
  return PaxPoslink2.makeCashPayment(amount, tip, referenceNumber); 
}
