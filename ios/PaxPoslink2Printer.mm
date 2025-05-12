#import "PaxPoslink2Printer.h"
#import "POSLinkSemi"

@implementation PaxPoslink2Printer
RCT_EXPORT_MODULE()

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
