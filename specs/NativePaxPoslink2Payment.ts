import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
  initPOSLink(type: string, timeout: number, nameOrMac?: string, ipOrSerial?: string, portOrBaud?: string) : Promise<string>;

  verifyPOSTerminal() : Promise<string>;
  
  initPaymentCommunication(type: string, timeout: number, nameOrMac?: string, ipOrSerial?: string, portOrBaud?: string) : Promise<string>;
  
  makeCreditPayment(amount: string, tip: string, referenceNumber?: string, testMode?: boolean) : Promise<Map>;

  makeCashPayment(amount: string, tip: string, referenceNumber?: string, testMode?: boolean) : Promise<Map>;

  makeCreditPaymentWithAmountRequestMap(amountRequestMap: Object, referenceNumber?: string, testMode?: boolean) : Promise<Map>;

  makeCashPaymentWithAmountRequestMap(amountRequestMap: Object, referenceNumber?: string, testMode?: boolean) : Promise<Map>;

  voidCreditPayment(amount: string, tip: string, referenceNumber?: string, testMode?: boolean) : Promise<Map>;

  returnCreditPayment(amount: string, tip: string, referenceNumber?: string, testMode?: boolean) : Promise<Map>;
  
  closeBatch() : Promise<string>;
}

export default TurboModuleRegistry.getEnforcing<Spec>('PaxPoslink2Payment');
