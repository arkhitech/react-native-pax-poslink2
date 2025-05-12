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
 @deprecated Since V2.01.00.
 Response Report Transaction Information.
 */

#import <Foundation/Foundation.h>
#import "PLTransactionInformation.h"
#import "PLSemiConst.h"
#import "PLAdminConst.h"
#import "PLReportTransactionInformation.h"

__attribute__((deprecated("Deprecated since V2.01.00")))
@interface PLReportTransactionInformation : PLTransactionInformation
/**
 The discount amount.

 Attribute : n...9
 @deprecated Since V2.01.00. 
 */
@property (readwrite, nonatomic, copy)NSString *discountAmount __attribute__((deprecated("Deprecated since V2.01.00")));
/**
 The signature status.

 Attribute : n1
 @deprecated Since V2.01.00. 
 */
@property (readwrite, nonatomic, assign)enum SignatureResponseStatus signatureStatus __attribute__((deprecated("Deprecated since V2.01.00")));
/**
 The serial number of the device.

 Attribute : ans...32
 @deprecated Since V2.01.00. 
 */
@property (readwrite, nonatomic, copy)NSString *sn __attribute__((deprecated("Deprecated since V2.01.00")));
/**
 Transaction settlement date, the format is YYYYMMDD.

 Attribute : n8
 @deprecated Since V2.01.00. 
 */
@property (readwrite, nonatomic, copy)NSString *settlementDate __attribute__((deprecated("Deprecated since V2.01.00")));
/**
 A unique ID for each transaction.

 Attribute : ans...64
 @deprecated Since V2.01.00. 
 */
@property (readwrite, nonatomic, copy)NSString *globalUid __attribute__((deprecated("Deprecated since V2.01.00")));

@end