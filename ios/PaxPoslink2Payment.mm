#import "PaxPoslink2Payment.h"
#import "POSLinkSemi.h"
#import "PLDoCreditRequest.h"
#import "PLDoCreditResponse.h"

#import "PLDoCashRequest.h"
#import "PLDoCashResponse.h"

#import "PLLogSetting.h"
#import "PLCommunicationSetting.h"

@implementation PaxPoslink2Payment
RCT_EXPORT_MODULE()

PLCommunicationSetting* communicationSettings;
- (void)initPOSLink:(nonnull NSString *)type timeout:(double)timeout nameOrMac:(nonnull NSString *)nameOrMac ipOrSerial:(nonnull NSString *)ipOrSerial portOrBaud:(nonnull NSString *)portOrBaud resolve:(nonnull RCTPromiseResolveBlock)resolve reject:(nonnull RCTPromiseRejectBlock)reject {
  reject(@"not supported on iOS", @"not supported on iOS", nil);
  // PLLogSetting *plLogSetting = [[PLLogSetting alloc] init];
  // plLogSetting.level = LoggerLevelDebug;
  // plLogSetting.dirName = @"POSLinkLog";
  // plLogSetting.keepDays = 30;
  // POSLinkSemi* posLinkSemi = [POSLinkSemi getInstance];
  // [posLinkSemi setLogSetting:plLogSetting];
  // [self initPaymentCommunication:type timeout:timeout nameOrMac:nameOrMac ipOrSerial:ipOrSerial portOrBaud:portOrBaud resolve:resolve reject:reject];
}

- (void)initPaymentCommunication:(nonnull NSString *)type timeout:(double)timeout nameOrMac:(nonnull NSString *)nameOrMac ipOrSerial:(nonnull NSString *)ipOrSerial portOrBaud:(nonnull NSString *)portOrBaud resolve:(nonnull RCTPromiseResolveBlock)resolve reject:(nonnull RCTPromiseRejectBlock)reject {
  reject(@"not supported on iOS", @"not supported on iOS", nil);
//   if([@"TCP" isEqualToString:type]) {
//     communicationSettings = [[PLCommunicationSetting alloc] init];
//     communicationSettings.communicationType = CommunicationTypeTCP;
//     communicationSettings.destIP = ipOrSerial;
//     communicationSettings.destPort = portOrBaud;
//     communicationSettings.timeout = [[NSNumber numberWithInt:((int)timeout)] stringValue];
// //  } else if([@"USB" isEqualToString:type]) {
// //    communicationSettings = [[PLCommunicationSetting alloc] init];
// //    communicationSettings.communicationType = CommunicationTypeTCP;
// //    communicationSettings.destIP = ipOrSerial;
// //    communicationSettings.destPort = portOrBaud;
// //    communicationSettings.timeout = [[NSNumber numberWithInt:((int)timeout)] stringValue];
//   } else if([@"BLUETOOTH" isEqualToString:type]) {
//     communicationSettings = [[PLCommunicationSetting alloc] init];
//     communicationSettings.communicationType = CommunicationTypeBLUETOOTH;
//     communicationSettings.bluetoothAddr = nameOrMac;
// //  } else if([@"AIDL" isEqualToString:type]) {
// //  } else if([@"UART" isEqualToString:type]) {
//   } else {
//     reject(@"Invalid type", type, nil);
//   }
//   resolve(@"success");
}

- (void)makeCashPayment:(nonnull NSString *)amount tip:(nonnull NSString *)tip referenceNumber:(nonnull NSString *)referenceNumber resolve:(nonnull RCTPromiseResolveBlock)resolve reject:(nonnull RCTPromiseRejectBlock)reject { 
  reject(@"not supported on iOS", @"not supported on iOS", nil);
  // POSLinkSemi* posLinkSemi = [POSLinkSemi getInstance];
  // PLSemiTerminal* terminal = [posLinkSemi getTerminalWithCommunicationSetting:communicationSettings];
  // PLAmountRequest* amountRequest = [[PLAmountRequest alloc] init];
  // amountRequest.transactionAmount = amount;
  // amountRequest.tipAmount = tip;

  // PLTraceRequest* traceRequest = [[PLTraceRequest alloc] init];
  // traceRequest.ecrReferenceNumber = referenceNumber;

  // PLDoCreditRequest* doCreditRequest = [[PLDoCreditRequest alloc] init];
  // doCreditRequest.transactionType = TransactionTypeSale;
  // doCreditRequest.amountInformation = amountRequest;
  // doCreditRequest.traceInformation = traceRequest;

  // PLDoCreditResponse* doCreditResponse = [[PLDoCreditResponse alloc] init];
  // [[terminal getTransaction] doCreditWithRequest:doCreditRequest completion:^(PLDoCreditResponse * _Nonnull response, PLExecutionResult * _Nonnull result) {
  //     [self handleCreditExecutionResult:response withExecutionResult:result withResolve:resolve withReject:reject];
  // }];
}

- (void)handleCreditExecutionResult:(PLDoCreditResponse*)doCreditResponse withExecutionResult:(PLExecutionResult*)executionResult withResolve:(nonnull RCTPromiseResolveBlock)resolve withReject:(nonnull RCTPromiseRejectBlock)reject {
  if (executionResult.isSuccessful) {
    NSDictionary* dictionary = [[NSDictionary alloc] init];
    [dictionary setValue:doCreditResponse.amountInformation.approvedAmount forKey:@"ApprovedAmount"];
    [dictionary setValue:doCreditResponse.amountInformation.approvedCashBackAmount forKey:@"ApprovedCashBackAmount"];
    [dictionary setValue:doCreditResponse.amountInformation.approvedMerchantFee forKey:@"ApprovedMerchantFee"];
    [dictionary setValue:doCreditResponse.amountInformation.approvedTaxAmount forKey:@"ApprovedTaxAmount"];
    [dictionary setValue:doCreditResponse.amountInformation.approvedTipAmount forKey:@"ApprovedTipAmount"];
    [dictionary setValue:doCreditResponse.responseCode forKey:@"AuthCode"];
    [dictionary setValue:doCreditResponse.responseMessage forKey:@"AuthorizationResponse"];
    [dictionary setValue:doCreditResponse.avsInformation.avsMessage forKey:@"AvsResponse"];
    [dictionary setValue:doCreditResponse.accountInformation.currentAccountNumber forKey:@"CurrentAccountNumber"];
    
    NSString* cardType = [[NSNumber numberWithInt:((int)doCreditResponse.accountInformation.cardType)] stringValue];
    [dictionary setValue:cardType forKey:@"CardType"];
    [dictionary setValue:doCreditResponse.accountInformation.cvdApprovalCode forKey:@"CvdApprovalCode"];
    NSString* debitAccountType = [[NSNumber numberWithInt:((int)doCreditResponse.accountInformation.debitAccountType)] stringValue];
    [dictionary setValue:debitAccountType forKey:@"DebitAccountType"];

    [dictionary setValue:doCreditResponse.hostTraceInformation.ecrTransactionId forKey:@"ECRTransID"];
    [dictionary setValue:doCreditResponse.edcType forKey:@"EDCType"];
    [dictionary setValue:doCreditResponse.hostInformation.gatewayTransactionId forKey:@"GatewayTransactionID"];
    
    NSString* giftCardType = [[NSNumber numberWithInt:((int)doCreditResponse.accountInformation.giftCardType)] stringValue];
    [dictionary setValue:giftCardType forKey:@"GiftCardType"];
    [dictionary setValue:doCreditResponse.traceInformation.referenceNumber forKey:@"ReferenceNumber"];
    [dictionary setValue:doCreditResponse.accountInformation.hostCardType forKey:@"HostCardType"];

    
    [dictionary setValue:doCreditResponse.hostInformation.hostResponseCode forKey:@"HostResponseCode"];
    [dictionary setValue:doCreditResponse.torInformation.hostResponseMessage forKey:@"HostResponseMessage"];
    [dictionary setValue:doCreditResponse.hostInformation.hostDetailedMessage forKey:@"HostDetailMessage"];
    [dictionary setValue:doCreditResponse.hostTraceInformation.hostTimeStamp forKey:@"HostTimeStamp"];
    [dictionary setValue:doCreditResponse.hostTraceInformation.hostReferenceNumber forKey:@"HostReferenceNumber"];
    [dictionary setValue:doCreditResponse.hostInformation.issuerResponseCode forKey:@"IssuerResponseCode"];
    [dictionary setValue:doCreditResponse.torInformation.maskedPan forKey:@"MaskedPAN"];
    [dictionary setValue:doCreditResponse.responseMessage forKey:@"ResponseMessage"];
    [dictionary setValue:doCreditResponse.payloadData forKey:@"PayloadData"];
    [dictionary setValue:doCreditResponse.hostInformation.paymentAccountReferenceId forKey:@"PaymentAccountReferenceID"];
    [dictionary setValue:doCreditResponse.torInformation.originalAmount forKey:@"RequestedAmount"];
    [dictionary setValue:doCreditResponse.hostInformation.retrievalReferenceNumber forKey:@"RetrievalReferenceNumber"];

    [dictionary setValue:doCreditResponse.signatureInformation.signatureData forKey:@"SignData"];
    [dictionary setValue:doCreditResponse.traceInformation.timeStamp forKey:@"Timestamp"];
    [dictionary setValue:doCreditResponse.accountInformation.track1Data forKey:@"Track1Data"];
    [dictionary setValue:doCreditResponse.accountInformation.track2Data forKey:@"Track2Data"];
    [dictionary setValue:doCreditResponse.accountInformation.track3Data forKey:@"Track3Data"];
    [dictionary setValue:doCreditResponse.hostInformation.transactionIntegrityClass forKey:@"TransactionIntegrityClass"];
    [dictionary setValue:doCreditResponse.amountInformation.transactionRemainingAmount forKey:@"TransactionRemainingAmount"];

    resolve(dictionary);
  } else {
    reject(doCreditResponse.responseCode, executionResult.responseMessage, nil);
  }
}

- (void)makeCreditPayment:(nonnull NSString *)amount tip:(nonnull NSString *)tip referenceNumber:(nonnull NSString *)referenceNumber resolve:(nonnull RCTPromiseResolveBlock)resolve reject:(nonnull RCTPromiseRejectBlock)reject {
  reject(@"not supported on iOS", @"not supported on iOS", nil);
  // POSLinkSemi* posLinkSemi = [POSLinkSemi getInstance];
  // PLSemiTerminal* terminal = [posLinkSemi getTerminalWithCommunicationSetting:communicationSettings];
  // PLAmountRequest* amountRequest = [[PLAmountRequest alloc] init];
  // amountRequest.transactionAmount = amount;
  // amountRequest.tipAmount = tip;

  // PLTraceRequest* traceRequest = [[PLTraceRequest alloc] init];
  // traceRequest.ecrReferenceNumber = referenceNumber;

  // PLDoCreditRequest* doCreditRequest = [[PLDoCreditRequest alloc] init];
  // doCreditRequest.transactionType = TransactionTypeSale;
  // doCreditRequest.amountInformation = amountRequest;
  // doCreditRequest.traceInformation = traceRequest;

  // PLDoCreditResponse* doCreditResponse = [[PLDoCreditResponse alloc] init];
  // [[terminal getTransaction] doCreditWithRequest:doCreditRequest completion:^(PLDoCreditResponse * _Nonnull response, PLExecutionResult * _Nonnull result) {
  //     [self handleCreditExecutionResult:response withExecutionResult:result withResolve:resolve withReject:reject];
  // }];
}

- (void)handleCashExecutionResult:(PLDoCashResponse*)doCashResponse withExecutionResult:(PLExecutionResult*)executionResult withResolve:(nonnull RCTPromiseResolveBlock)resolve withReject:(nonnull RCTPromiseRejectBlock)reject {
  if (executionResult.isSuccessful) {
    NSDictionary* dictionary = [[NSDictionary alloc] init];
    [dictionary setValue:doCashResponse.amountInformation.approvedAmount forKey:@"ApprovedAmount"];
    [dictionary setValue:doCashResponse.amountInformation.approvedCashBackAmount forKey:@"ApprovedCashBackAmount"];
    [dictionary setValue:doCashResponse.amountInformation.approvedMerchantFee forKey:@"ApprovedMerchantFee"];
    [dictionary setValue:doCashResponse.amountInformation.approvedTaxAmount forKey:@"ApprovedTaxAmount"];
    [dictionary setValue:doCashResponse.amountInformation.approvedTipAmount forKey:@"ApprovedTipAmount"];
    [dictionary setValue:doCashResponse.responseCode forKey:@"AuthCode"];
    [dictionary setValue:doCashResponse.responseMessage forKey:@"AuthorizationResponse"];
//    [dictionary setValue:doCashResponse.avsInformation.avsMessage forKey:@"AvsResponse"];
//    [dictionary setValue:doCashResponse.accountInformation.currentAccountNumber forKey:@"CurrentAccountNumber"];
    
//    NSString* cardType = [[NSNumber numberWithInt:((int)doCashResponse.accountInformation.cardType)] stringValue];
//    [dictionary setValue:cardType forKey:@"CardType"];
//    [dictionary setValue:doCashResponse.accountInformation.cvdApprovalCode forKey:@"CvdApprovalCode"];
//    NSString* debitAccountType = [[NSNumber numberWithInt:((int)doCashResponse.accountInformation.debitAccountType)] stringValue];
//    [dictionary setValue:debitAccountType forKey:@"DebitAccountType"];

    [dictionary setValue:doCashResponse.hostTraceInformation.ecrTransactionId forKey:@"ECRTransID"];
//    [dictionary setValue:doCashResponse.edcType forKey:@"EDCType"];
    [dictionary setValue:doCashResponse.hostInformation.gatewayTransactionId forKey:@"GatewayTransactionID"];
    
//    NSString* giftCardType = [[NSNumber numberWithInt:((int)doCashResponse.accountInformation.giftCardType)] stringValue];
//    [dictionary setValue:giftCardType forKey:@"GiftCardType"];
    [dictionary setValue:doCashResponse.traceInformation.referenceNumber forKey:@"ReferenceNumber"];
//    [dictionary setValue:doCashResponse.accountInformation.hostCardType forKey:@"HostCardType"];

    
    [dictionary setValue:doCashResponse.hostInformation.hostResponseCode forKey:@"HostResponseCode"];
    [dictionary setValue:doCashResponse.torInformation.hostResponseMessage forKey:@"HostResponseMessage"];
    [dictionary setValue:doCashResponse.hostInformation.hostDetailedMessage forKey:@"HostDetailMessage"];
    [dictionary setValue:doCashResponse.hostTraceInformation.hostTimeStamp forKey:@"HostTimeStamp"];
    [dictionary setValue:doCashResponse.hostTraceInformation.hostReferenceNumber forKey:@"HostReferenceNumber"];
    [dictionary setValue:doCashResponse.hostInformation.issuerResponseCode forKey:@"IssuerResponseCode"];
    [dictionary setValue:doCashResponse.torInformation.maskedPan forKey:@"MaskedPAN"];
    [dictionary setValue:doCashResponse.responseMessage forKey:@"ResponseMessage"];
//    [dictionary setValue:doCashResponse.payloadData forKey:@"PayloadData"];
    [dictionary setValue:doCashResponse.hostInformation.paymentAccountReferenceId forKey:@"PaymentAccountReferenceID"];
    [dictionary setValue:doCashResponse.torInformation.originalAmount forKey:@"RequestedAmount"];
    [dictionary setValue:doCashResponse.hostInformation.retrievalReferenceNumber forKey:@"RetrievalReferenceNumber"];

    [dictionary setValue:doCashResponse.signatureInformation.signatureData forKey:@"SignData"];
    [dictionary setValue:doCashResponse.traceInformation.timeStamp forKey:@"Timestamp"];
//    [dictionary setValue:doCashResponse.accountInformation.track1Data forKey:@"Track1Data"];
//    [dictionary setValue:doCashResponse.accountInformation.track2Data forKey:@"Track2Data"];
//    [dictionary setValue:doCashResponse.accountInformation.track3Data forKey:@"Track3Data"];
    [dictionary setValue:doCashResponse.hostInformation.transactionIntegrityClass forKey:@"TransactionIntegrityClass"];
    [dictionary setValue:doCashResponse.amountInformation.transactionRemainingAmount forKey:@"TransactionRemainingAmount"];

    resolve(dictionary);
  } else {
    reject(doCashResponse.responseCode, executionResult.responseMessage, nil);
  }
}

- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativePaxPoslink2PaymentSpecJSI>(params);
}

@end
