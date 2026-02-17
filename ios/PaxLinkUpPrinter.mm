#import "PaxLinkUpPrinter.h"

@implementation PaxLinkUpPrinter
RCT_EXPORT_MODULE()

- (void)getPrinterDeviceList:(BOOL)testMode resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject
{
  reject(@"not supported on iOS", @"not supported on iOS", nil);
}

- (void)printImageBase64:(NSString*)printerDeviceId printerComponentId:(NSString*)printerComponentId base64Image:(NSString*)base64Image imageWidth:(double)imageWidth imageHeight:(double)imageHeight cutMode:(double)cutMode testMode:(BOOL)testMode resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject
{
  reject(@"not supported on iOS", @"not supported on iOS", nil);
}

- (void)printImageUrl:(NSString*)printerDeviceId printerComponentId:(NSString*)printerComponentId imageUrl:(NSString*)imageUrl imageWidth:(double)imageWidth imageHeight:(double)imageHeight cutMode:(double)cutMode testMode:(BOOL)testMode resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject
{
  reject(@"not supported on iOS", @"not supported on iOS", nil);
}

- (void)printRawCommands:(NSString*)printerDeviceId printerComponentId:(NSString*)printerComponentId base64Strings:(NSArray<NSString*>*)base64Strings testMode:(BOOL)testMode resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject
{
  reject(@"not supported on iOS", @"not supported on iOS", nil);
}

- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativePaxLinkUpPrinterSpecJSI>(params);
}

@end