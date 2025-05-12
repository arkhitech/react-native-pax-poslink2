/*
 * ============================================================================
 * = COPYRIGHT
 *          PAX Computer Technology(Shenzhen) Co., Ltd. PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or nondisclosure
 *   agreement with PAX Computer Technology(Shenzhen) Co., Ltd. and may not be copied or
 *   disclosed except in accordance with the terms in that agreement.
 *     Copyright (C) 2023 PAX Computer Technology(Shenzhen) Co., Ltd. All rights reserved.
 * ============================================================================
 */
/**
 Do debit transaction response.
 */

#import <Foundation/Foundation.h>
#import "PLResponse.h"
#import "PLSemiConst.h"
#import "PLAdminConst.h"
#import "PLFpsResponse.h"
#import "PLTransactionBehaviorResponse.h"
#import "PLHostTraceResponse.h"
#import "PLTaxDetail.h"
#import "PLHostCredentialResponse.h"
#import "PLTorResponse.h"
#import "PLVasResponse.h"
#import "PLSignatureResponse.h"
#import "PLFleetCardResponse.h"
#import "PLPaymentEmvTag.h"
#import "PLMultiMerchant.h"
#import "PLCardInformation.h"
#import "PLPaymentTransactionInformation.h"
#import "PLRestaurant.h"
#import "PLTraceResponse.h"
#import "PLAccountResponse.h"
#import "PLAmountResponse.h"
#import "PLAdditionalResponseData.h"
#import "PLHostInformationResponse.h"
#import "PLDoDebitResponse.h"

@interface PLDoDebitResponse : PLResponse
/**
 Host information. 
 */
@property (readwrite, nonatomic, strong)PLHostInformationResponse *hostInformation;
/**
 Transaction type. 
 */
@property (readwrite, nonatomic, assign)enum TransactionType transactionType;
/**
 Amount information. 
 */
@property (readwrite, nonatomic, strong)PLAmountResponse *amountInformation;
/**
 Account information. 
 */
@property (readwrite, nonatomic, strong)PLAccountResponse *accountInformation;
/**
 Trace information. 
 */
@property (readwrite, nonatomic, strong)PLTraceResponse *traceInformation;
/**
 Restaurant information. 
 */
@property (readwrite, nonatomic, strong)PLRestaurant *restaurant;
/**
 Transaction information.
 @deprecated Since V2.01.00. 
 */
@property (readwrite, nonatomic, strong)PLPaymentTransactionInformation *paymentTransactionInformation __attribute__((deprecated("Deprecated since V2.01.00")));
/**
 Card information.
 @deprecated Since V2.01.00. 
 */
@property (readwrite, nonatomic, strong)PLCardInformation *cardInformation __attribute__((deprecated("Deprecated since V2.01.00")));
/**
 Multi merchant information. 
 */
@property (readwrite, nonatomic, strong)PLMultiMerchant *multiMerchant;
/**
 EMV Tag information. 
 */
@property (readwrite, nonatomic, strong)PLPaymentEmvTag *paymentEmvTag;
/**
 Fleet card information. 
 */
@property (readwrite, nonatomic, strong)PLFleetCardResponse *fleetCard;
/**
 Signature response information. 
 */
@property (readwrite, nonatomic, strong)PLSignatureResponse *signatureInformation;
/**
 VAS information. 
 */
@property (readwrite, nonatomic, strong)PLVasResponse *vasInformation;
/**
 TOR information. 
 */
@property (readwrite, nonatomic, strong)PLTorResponse *torInformation;
/**
 Response Host Credential Information. 
 */
@property (readwrite, nonatomic, strong)PLHostCredentialResponse *hostCredentialInformation;
/**
 Returns the approved tax amounts to be printed on the receipt. 
 */
@property (readwrite, nonatomic, copy)NSArray<PLTaxDetail *> *taxDetails;
/**
 Host Trace information. 
 */
@property (readwrite, nonatomic, strong)PLHostTraceResponse *hostTraceInformation;
/**
 Transaction Behavior. 
 */
@property (readwrite, nonatomic, strong)PLTransactionBehaviorResponse *transactionBehavior;
/**
 FPS Response Information. 
 */
@property (readwrite, nonatomic, strong)PLFpsResponse *fpsInformation;


@end