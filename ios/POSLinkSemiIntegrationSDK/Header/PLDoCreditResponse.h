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
 Do credit transaction response.
 */

#import <Foundation/Foundation.h>
#import "PLResponse.h"
#import "PLSemiConst.h"
#import "PLAdminConst.h"
#import "PLFpsResponse.h"
#import "PLTransactionBehaviorResponse.h"
#import "PLHostTraceResponse.h"
#import "PLCofResponse.h"
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
#import "PLMotoECommerceResponse.h"
#import "PLCommercialResponse.h"
#import "PLAvsResponse.h"
#import "PLTraceResponse.h"
#import "PLAccountResponse.h"
#import "PLAmountResponse.h"
#import "PLAdditionalResponseData.h"
#import "PLHostInformationResponse.h"
#import "PLDoCreditResponse.h"

@interface PLDoCreditResponse : PLResponse
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
 AVS information. 
 */
@property (readwrite, nonatomic, strong)PLAvsResponse *avsInformation;
/**
 Commercial information. 
 */
@property (readwrite, nonatomic, strong)PLCommercialResponse *commercialInformation;
/**
 MotoECommerce information. 
 */
@property (readwrite, nonatomic, strong)PLMotoECommerceResponse *motoECommerceInformation;
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
 Host-specific data, formatted according to host specifications.

 Attribute : ans…4000 
 */
@property (readwrite, nonatomic, copy)NSString *payloadData;
/**
 Response Host Credential Information. 
 */
@property (readwrite, nonatomic, strong)PLHostCredentialResponse *hostCredentialInformation;
/**
 Returns the approved tax amounts to be printed on the receipt. 
 */
@property (readwrite, nonatomic, copy)NSArray<PLTaxDetail *> *taxDetails;
/**
 COF Response. 
 */
@property (readwrite, nonatomic, strong)PLCofResponse *cofInformation;
/**
 Host Trace information. 
 */
@property (readwrite, nonatomic, strong)PLHostTraceResponse *hostTraceInformation;
/**
 The EDC type.

The EDC type returned for CREDIT/DEBIT prompt is on. Once DEBIT selected on terminal for Do Credit command, "DEBIT" as this field will be returned.

 Attribute : ans...16 
 */
@property (readwrite, nonatomic, copy)NSString *edcType;
/**
 Transaction Behavior. 
 */
@property (readwrite, nonatomic, strong)PLTransactionBehaviorResponse *transactionBehavior;
/**
 FPS Response Information. 
 */
@property (readwrite, nonatomic, strong)PLFpsResponse *fpsInformation;


@end