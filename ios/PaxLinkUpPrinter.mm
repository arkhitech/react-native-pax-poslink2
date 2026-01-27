#import "PaxLinkUpPrinter.h"

@implementation PaxLinkUpPrinter
RCT_EXPORT_MODULE()

- (void)getPrinterDeviceList:(boolean)testMode resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject

- (void)printImageBase64:(NSString*)printerDeviceId printerComponentId: (NSString*)printerComponentId (NSString*)base64Image cutMode:(double)cutMode testMode:(boolean)testMode resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject
{
  reject(@"not supported on iOS", @"not supported on iOS", nil);
}

- (void)printRawCommands:(NSString*)printerDeviceId printerComponentId: (NSString*)printerComponentId base64Strings: (NSArray<NSString*>*)base64Strings testMode:(boolean)testMode resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject
{
  reject(@"not supported on iOS", @"not supported on iOS", nil);
}

- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativePaxLinkUpPrinterSpecJSI>(params);
}

@end
