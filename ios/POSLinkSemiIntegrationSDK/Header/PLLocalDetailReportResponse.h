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
 Local Detail Report Response
 */

#import <Foundation/Foundation.h>
#import "PLResponse.h"
#import "PLSemiConst.h"
#import "PLAdminConst.h"
#import "PLFpsResponse.h"
#import "PLTransactionBehaviorResponse.h"
#import "PLHostTraceResponse.h"
#import "PLTorResponse.h"
#import "PLFleetCardResponse.h"
#import "PLReportEmvTag.h"
#import "PLMultiMerchant.h"
#import "PLCardInformation.h"
#import "PLReportTransactionInformation.h"
#import "PLRestaurant.h"
#import "PLCommercialResponse.h"
#import "PLCashierResponse.h"
#import "PLTraceResponse.h"
#import "PLAccountResponse.h"
#import "PLAmountResponse.h"
#import "PLAdditionalResponseData.h"
#import "PLHostInformationResponse.h"
#import "PLLocalDetailReportResponse.h"

@interface PLLocalDetailReportResponse : PLResponse
/**
 The total number of matching records.

 Attribute : n...4 
 */
@property (readwrite, nonatomic, copy)NSString *totalRecord;
/**
 The current reporting record number.

 Attribute : n...4 
 */
@property (readwrite, nonatomic, copy)NSString *recordNumber;
/**
 Host information. 
 */
@property (readwrite, nonatomic, strong)PLHostInformationResponse *hostInformation;
/**
 EDC type. 
 */
@property (readwrite, nonatomic, assign)enum EdcType edcType;
/**
 Transaction type. 
 */
@property (readwrite, nonatomic, assign)enum TransactionType transactionType;
/**
 Original transaction type. 
 */
@property (readwrite, nonatomic, assign)enum TransactionType originalTransactionType;
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
 Cashier information. 
 */
@property (readwrite, nonatomic, strong)PLCashierResponse *cashierInformation;
/**
 Commercial information. 
 */
@property (readwrite, nonatomic, strong)PLCommercialResponse *commercialInformation;
/**
 Restaurant information. 
 */
@property (readwrite, nonatomic, strong)PLRestaurant *restaurant;
/**
 Report transaction information.
 @deprecated Since V2.01.00. 
 */
@property (readwrite, nonatomic, strong)PLReportTransactionInformation *reportTransactionInformation __attribute__((deprecated("Deprecated since V2.01.00")));
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
@property (readwrite, nonatomic, strong)PLReportEmvTag *reportEmvTag;
/**
 Fleet card information. 
 */
@property (readwrite, nonatomic, strong)PLFleetCardResponse *fleetCard;
/**
 TOR information. 
 */
@property (readwrite, nonatomic, strong)PLTorResponse *torInformation;
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