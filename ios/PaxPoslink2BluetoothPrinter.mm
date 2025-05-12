#import "PaxPoslink2BluetoothPrinter.h"

@implementation PaxPoslink2BluetoothPrinter
RCT_EXPORT_MODULE()

- (void)setMacAddress:(NSString*)macAddress resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject {
}

- (void)printImageBase64:(NSString*)base64Image cutMode:(double)cutMode printWidth:(double)printWidth resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject {
}

- (void)cutPaper:(double)cutMode resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject {
}

- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativePaxPoslink2SpecJSI>(params);
}

@end
