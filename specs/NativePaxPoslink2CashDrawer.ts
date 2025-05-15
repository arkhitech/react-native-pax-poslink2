import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
  cashBoxStatus() : Promise<string>;  
  open() : Promise<string>;  
}

export default TurboModuleRegistry.getEnforcing<Spec>('PaxPoslink2CashDrawer');
