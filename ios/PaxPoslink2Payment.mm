#import "PaxPoslink2Payment.h"

@implementation PaxPoslink2Payment
RCT_EXPORT_MODULE()
- (void)initPOSLink:(NSString *)type timeout:(double)timeout nameOrMac:(NSString*)nameOrMac ipOrSerial:(NSString*)ipOrSerial portOrBaud:(NSString*) resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject {
}

- (void)initPaymentCommunication:(NSString *)type timeout:(double)timeout nameOrMac:(NSString*)nameOrMac ipOrSerial:(NSString*)ipOrSerial portOrBaud:(NSString*) resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject {
}
- (void)makeCreditPayment:(NSString *)amount tip:(NSString *)tip referenceNumber:(NSString *) resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject {
  
}

- (void)makeCashPayment:(NSString *)amount tip:(NSString *)tip referenceNumber:(NSString *) resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject {
  
}

- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativePaxPoslink2SpecJSI>(params);
}

@end
