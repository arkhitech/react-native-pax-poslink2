#import "PaxPoslink2CashDrawer.h"

@implementation PaxPoslink2CashDrawer
RCT_EXPORT_MODULE()

- (void)open:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject
{
  reject(@"not supported on iOS", @"not supported on iOS", nil);
}

- (void)cashBoxStatus:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject
{
  reject(@"not supported on iOS", @"not supported on iOS", nil);
}

- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativePaxPoslink2CashDrawerSpecJSI>(params);
}

@end
