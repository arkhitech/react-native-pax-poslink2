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
 Do QR payment request.
 */

#import <Foundation/Foundation.h>
#import "PLRequest.h"
#import "PLSemiConst.h"
#import "PLAdminConst.h"
#import "PLHostTraceRequest.h"
#import "PLTaxDetail.h"
#import "PLLineItemDetail.h"
#import "PLTaxDetail.h"
#import "PLCommercialRequest.h"
#import "PLCashierRequest.h"
#import "PLTraceRequest.h"
#import "PLAmountRequest.h"
#import "PLDoQrPaymentRequest.h"

@interface PLDoQrPaymentRequest : PLRequest
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
 Commercial information. 
 */
@property (readwrite, nonatomic, strong)PLCommercialRequest *commercialInformation;
/**
 Host Trace information. 
 */
@property (readwrite, nonatomic, strong)PLHostTraceRequest *hostTraceInformation;


@end