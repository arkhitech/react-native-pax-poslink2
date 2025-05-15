import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
  setMacAddress(macAddress: string) : Promise<string>;
  
  printImageBase64(base64Image: string, cutMode: number) : Promise<string>;
  
  cutPaper(cutMode: number) : Promise<string>;
  
  disconnect() : Promise<string>;  
}

export default TurboModuleRegistry.getEnforcing<Spec>('PaxPoslink2BluetoothPrinter');
