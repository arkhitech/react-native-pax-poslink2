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
 Do credit transaction request.
 */

#import <Foundation/Foundation.h>
#import "PLRequest.h"
#import "PLSemiConst.h"
#import "PLAdminConst.h"
#import "PLFsaData.h"
#import "PLHostTraceRequest.h"
#import "PLSignatureRequest.h"
#import "PLCofRequest.h"
#import "PLHostCredentialRequest.h"
#import "PLExtraChargeItem.h"
#import "PLAutoRental.h"
#import "PLLodgingItem.h"
#import "PLRoomRate.h"
#import "PLLodging.h"
#import "PLMultiMerchant.h"
#import "PLFleetData.h"
#import "PLFleetCardRequest.h"
#import "PLOriginal.h"
#import "PLHostInformationRequest.h"
#import "PLTransactionPromptBitmap.h"
#import "PLEntryModeBitmap.h"
#import "PLProgramBitmap.h"
#import "PLCardTypeBitmap.h"
#import "PLTransactionBehaviorRequest.h"
#import "PLRestaurant.h"
#import "PLMotoECommerceRequest.h"
#import "PLTaxDetail.h"
#import "PLLineItemDetail.h"
#import "PLTaxDetail.h"
#import "PLCommercialRequest.h"
#import "PLCashierRequest.h"
#import "PLAvsRequest.h"
#import "PLTraceRequest.h"
#import "PLAccountRequest.h"
#import "PLAmountRequest.h"
#import "PLDoCreditRequest.h"

@interface PLDoCreditRequest : PLRequest
/**
 Transaction type. 
 */
@property (readwrite, nonatomic, assign)enum TransactionType transactionType;
/**
 Amount information. 
 */
@property (readwrite, nonatomic, strong)PLAmountRequest *amountInformation;
/**
 Account information. 
 */
@property (readwrite, nonatomic, strong)PLAccountRequest *accountInformation;
/**
 Trace information. 
 */
@property (readwrite, nonatomic, strong)PLTraceRequest *traceInformation;
/**
 AVS Information. 
 */
@property (readwrite, nonatomic, strong)PLAvsRequest *avsInformation;
/**
 Cashier information. 
 */
@property (readwrite, nonatomic, strong)PLCashierRequest *cashierInformation;
/**
 Commercial information. 
 */
@property (readwrite, nonatomic, strong)PLCommercialRequest *commercialInformation;
/**
 MOTOECommerce information. 
 */
@property (readwrite, nonatomic, strong)PLMotoECommerceRequest *motoECommerceInformation;
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
 Fleet card information. 
 */
@property (readwrite, nonatomic, strong)PLFleetCardRequest *fleetCard;
/**
 Multi merchant information. 
 */
@property (readwrite, nonatomic, strong)PLMultiMerchant *multiMerchant;
/**
 Lodging information. 
 */
@property (readwrite, nonatomic, strong)PLLodging *lodging;
/**
 Auto rental information. 
 */
@property (readwrite, nonatomic, strong)PLAutoRental *autoRental;
/**
 Host credential information. 
 */
@property (readwrite, nonatomic, strong)PLHostCredentialRequest *hostCredential;
/**
 COF Request. 
 */
@property (readwrite, nonatomic, strong)PLCofRequest *cofInformation;
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
/**
 Program data for FSA transactions. This includes program data for HSAs and HRAs, as well. 
 */
@property (readwrite, nonatomic, strong)PLFsaData *fsaData;


@end