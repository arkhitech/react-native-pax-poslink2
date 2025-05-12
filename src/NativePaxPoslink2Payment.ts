import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
  initPOSLink(type: string, timeout: number, nameOrMac?: string, ipOrSerial?: string, portOrBaud?: string) : Promise<string>;
  
  initPaymentCommunication(type: string, timeout: number, nameOrMac?: string, ipOrSerial?: string, portOrBaud?: string) : Promise<string>;
  
  makeCreditPayment(amount: string, tip: string, referenceNumber?: string) : Promise<string>;

  makeCashPayment(amount: string, tip: string, referenceNumber?: string) : Promise<string>;
  
}

export default TurboModuleRegistry.getEnforcing<Spec>('PaxPoslink2Payment');
