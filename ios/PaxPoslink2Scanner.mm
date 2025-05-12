#import "PaxPoslink2Scanner.h"

@implementation PaxPoslink2Scanner
RCT_EXPORT_MODULE()

- (void)open:(NSString*)scannerType resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject {
}

- (void)close:(NSString*)scannerType resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject {
}

- (void)start:(NSString*)scannerType resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject {
}

- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativePaxPoslink2SpecJSI>(params);
}

@end
