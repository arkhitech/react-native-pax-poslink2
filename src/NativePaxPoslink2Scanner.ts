import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
  open(scannerType: string) : Promise<string>;
  
  close(scannerType: string) : Promise<string>;
  
  start(scannerType: string) : Promise<string>;
}

export default TurboModuleRegistry.getEnforcing<Spec>('PaxPoslink2Scanner');
