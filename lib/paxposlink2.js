import { NativeModules, Platform } from 'react-native';
const LINKING_ERROR = `The package 'react-native-pax-poslink2' doesn't seem to be linked. Make sure: \n\n${
  Platform.select({
    ios: "- You have run 'pod install'\n",
    default: ''
  })}- You rebuilt the app after installing the package\n- You are not using Expo Go\n`;
const PaxPoslink2 = NativeModules.PaxPoslink2 ? NativeModules.PaxPoslink2 : new Proxy({}, {
  get() {
    throw new Error(LINKING_ERROR);
  }
});
export function initPOSLink(type, timeout, nameOrMac, ipOrSerial, portOrBaud) {
  return PaxPoslink2.initPOSLink(type, timeout, nameOrMac, ipOrSerial, portOrBaud);
}
export function makeCreditPayment(amount, tip, referenceNumber) {
  return PaxPoslink2.makeCreditPayment(amount, tip, referenceNumber);
}
export function makeReturn(amount) {
  return PaxPoslink2.runReturn(amount);
}
export function makeAdjustment(amount, refNum) {
  return PaxPoslink2.runAdjustment(amount, refNum);
}
export function makeAuth(amount) {
  return PaxPoslink2.runAuth(amount);
}
export function makePostAuth(amount, refNum, autCode) {
  return PaxPoslink2.runPostAuth(amount, refNum, autCode);
}
export function voidTransaction(refNum, autCode) {
  return PaxPoslink2.runVoid(refNum, autCode);
}
export function closeBatch() {
  return PaxPoslink2.closeBatch();
}
//# sourceMappingURL=index.js.map