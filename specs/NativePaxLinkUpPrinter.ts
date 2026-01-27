import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
  getPrinterDeviceList(testMode?: boolean) : Promise<string>;
  printImageBase64(printerDeviceId: string, printerComponentId: string, base64Image: string, imageWidth: number, imageHeight: number, cutMode: number, testMode?: boolean) : Promise<string>;
  printImageUrl(printerDeviceId: string, printerComponentId: string, imageUrl: string, imageWidth: number, imageHeight: number, cutMode: number, testMode?: boolean) : Promise<string>;
  printRawCommands(printerDeviceId: string, printerComponentId: string, base64Strings: string[], testMode?: boolean) : Promise<string>;
}

export default TurboModuleRegistry.getEnforcing<Spec>('PaxLinkUpPrinter');
