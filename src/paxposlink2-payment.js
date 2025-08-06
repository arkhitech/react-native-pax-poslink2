import { NativeModules, Platform } from 'react-native';
const LINKING_ERROR = `The package 'react-native-pax-poslink2' doesn't seem to be linked. Make sure: \n\n${
  Platform.select({
    ios: "- You have run 'pod install'\n",
    default: ''
  })}- You rebuilt the app after installing the package\n- You are not using Expo Go\n`;
const PaxPoslink2Payment = NativeModules.PaxPoslink2Payment ? NativeModules.PaxPoslink2Payment : new Proxy({}, {
  get() {
    throw new Error(LINKING_ERROR);
  }
});

export const initPOSLink = (type, timeout, nameOrMac, ipOrSerial, portOrBaud) => {
  return PaxPoslink2Payment.initPOSLink(type, timeout, nameOrMac, ipOrSerial, portOrBaud);
}

export const verifyPOSTerminal = () => {
  return PaxPoslink2Payment.verifyPOSTerminal();
}

export const initPaymentCommunication = (type, timeout, nameOrMac, ipOrSerial, portOrBaud) => {
  return PaxPoslink2Payment.initPaymentCommunication(type, timeout, nameOrMac, ipOrSerial, portOrBaud);
}

export const makeCreditPayment = (amount, tip, referenceNumber) => {
  return PaxPoslink2Payment.makeCreditPayment(amount, tip, referenceNumber); 
}

export const makeCashPayment = (amount, tip, referenceNumber) => {
  return PaxPoslink2Payment.makeCashPayment(amount, tip, referenceNumber); 
}

/**
 * Initiates a refund transaction.
 * @param {string} amount - The amount to refund.
 * @returns {Promise<PaxResponseModel>} A promise resolving to the refund result.
 */
export const makeRefund = (amount) => {
  return PaxPoslink2Payment.refund({ amount });
}

/**
 * Voids a transaction for the given amount.
 * @param {string} amount - The amount to void.
 * @returns {Promise<PaxResponseModel>} A promise resolving to the void result.
 */
export const makeVoid = (amount) => {
  return PaxPoslink2Payment.void({ amount });
}

/**
 * Closes the current batch of transactions.
 * @returns {Promise<PaxResponseModel>} A promise resolving to the batch closeout result.
 */
export const makeCloseBatch = () => {
  return PaxPoslink2Payment.batchCloseout();
}