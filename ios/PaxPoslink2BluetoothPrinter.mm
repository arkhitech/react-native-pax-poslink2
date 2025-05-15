#import "PaxPoslink2BluetoothPrinter.h"
#import "POSLinkSemi.h"

@implementation PaxPoslink2BluetoothPrinter
RCT_EXPORT_MODULE()

- (void)setMacAddress:(NSString*)macAddress resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject
{
  reject(@"not supported on iOS", @"not supported on iOS", nil);
}

- (void)printImageBase64:(nonnil NSString *)base64Image cutMode:(double)cutMode resolve:(nonnil RCTPromiseResolveBlock)resolve reject:(nonnil RCTPromiseRejectBlock)reject {
  reject(@"not supported on iOS", @"not supported on iOS", nil);
}

- (void)cutPaper:(double)cutMode resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject
{
  reject(@"not supported on iOS", @"not supported on iOS", nil);
}

- (void)disconnect:(nonnil RCTPromiseResolveBlock)resolve reject:(nonnil RCTPromiseRejectBlock)reject { 
  reject(@"not supported on iOS", @"not supported on iOS", nil);
}



- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativePaxPoslink2BluetoothPrinterSpecJSI>(params);
}

@end
