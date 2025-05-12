#import "PaxPoslink2CashDrawer.h"

@implementation PaxPoslink2CashDrawer
RCT_EXPORT_MODULE()

- (void)open:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject {
}

- (void)cashBoxStatus:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject {
}

- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativePaxPoslink2SpecJSI>(params);
}

@end
