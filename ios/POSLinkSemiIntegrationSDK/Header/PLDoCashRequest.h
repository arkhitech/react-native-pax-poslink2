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
 Do cash transaction request.
 */

#import <Foundation/Foundation.h>
#import "PLRequest.h"
#import "PLSemiConst.h"
#import "PLAdminConst.h"
#import "PLHostTraceRequest.h"
#import "PLSignatureRequest.h"
#import "PLTaxDetail.h"
#import "PLHostCredentialRequest.h"
#import "PLMultiMerchant.h"
#import "PLOriginal.h"
#import "PLHostInformationRequest.h"
#import "PLTransactionPromptBitmap.h"
#import "PLEntryModeBitmap.h"
#import "PLProgramBitmap.h"
#import "PLCardTypeBitmap.h"
#import "PLTransactionBehaviorRequest.h"
#import "PLRestaurant.h"
#import "PLCashierRequest.h"
#import "PLTraceRequest.h"
#import "PLAmountRequest.h"
#import "PLDoCashRequest.h"

@interface PLDoCashRequest : PLRequest
/**
 Transaction type. 
 */
@property (readwrite, nonatomic, assign)enum TransactionType transactionType;
/**
 Amount information. 
 */
@property (readwrite, nonatomic, strong)PLAmountRequest *amountInformation;
/**
 Trace information. 
 */
@property (readwrite, nonatomic, strong)PLTraceRequest *traceInformation;
/**
 Cashier information. 
 */
@property (readwrite, nonatomic, strong)PLCashierRequest *cashierInformation;
/**
 Restaurant information. 
 */
@property (readwrite, nonatomic, strong)PLRestaurant *restaurant;
/**
 Transaction behavior information. 
 */
@property (readwrite, nonatomic, strong)PLTransactionBehaviorRequest *transactionBehavior;
/**
 Host information. 
 */
@property (readwrite, nonatomic, strong)PLHostInformationRequest *hostInformation;
/**
 Original information.
 @deprecated Since V2.01.00. 
 */
@property (readwrite, nonatomic, strong)PLOriginal *original __attribute__((deprecated("Deprecated since V2.01.00")));
/**
 Multi merchant information. 
 */
@property (readwrite, nonatomic, strong)PLMultiMerchant *multiMerchant;
/**
 Host credential information. 
 */
@property (readwrite, nonatomic, strong)PLHostCredentialRequest *hostCredential;
/**
 Used to report the amounts for different Tax Types in the transaction. 
 */
@property (readwrite, nonatomic, copy)NSArray<PLTaxDetail *> *taxDetails;
/**
 Signature information. 
 */
@property (readwrite, nonatomic, strong)PLSignatureRequest *signatureInformation;
/**
 Host Trace information. 
 */
@property (readwrite, nonatomic, strong)PLHostTraceRequest *hostTraceInformation;
/**
 Original transaction type. Used for follow up transactions. 
 */
@property (readwrite, nonatomic, assign)enum TransactionType originalTransactionType;


@end