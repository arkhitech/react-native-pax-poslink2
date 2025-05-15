import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
  printImageBase64(base64Image: string, cutMode: number, printWidth: number) : Promise<string>;
  
  cutPaper(cutMode: number) : Promise<string>;
}

export default TurboModuleRegistry.getEnforcing<Spec>('PaxPoslink2Printer');
