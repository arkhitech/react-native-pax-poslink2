#import "PaxPoslink2Printer.h"

@implementation PaxPoslink2Printer
RCT_EXPORT_MODULE()

- (void)printImageBase64:(NSString*)base64Image cutMode:(double)cutMode printWidth:(double)printWidth resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject
{
  reject(@"not supported on iOS", @"not supported on iOS", nil);
}

- (void)cutPaper:(double)cutMode resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject
{
  reject(@"not supported on iOS", @"not supported on iOS", nil);
}

- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativePaxPoslink2PrinterSpecJSI>(params);
}

@end
