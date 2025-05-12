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
 Response Payment Transaction Information.
 */

#import <Foundation/Foundation.h>
#import "PLTransactionInformation.h"
#import "PLSemiConst.h"
#import "PLAdminConst.h"
#import "PLPaymentTransactionInformation.h"

__attribute__((deprecated("Deprecated since V2.01.00")))
@interface PLPaymentTransactionInformation : PLTransactionInformation
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
/**
 The original tip amount.

 Attribute : n...9
 @deprecated Since V2.01.00. 
 */
@property (readwrite, nonatomic, copy)NSString *originalTipAmount __attribute__((deprecated("Deprecated since V2.01.00")));
/**
 The EDC type.

The EDC type returned for CREDIT/DEBIT prompt is on. Once DEBIT selected on terminal for Do Credit command, "DEBIT" as this field will be returned.

 Attribute : ans...16
 @deprecated Since V2.01.00. 
 */
@property (readwrite, nonatomic, copy)NSString *edcType __attribute__((deprecated("Deprecated since V2.01.00")));
/**
 Signature data captured by terminal for current.

 Attribute : ans...3072
 @deprecated Since V2.01.00. 
 */
@property (readwrite, nonatomic, copy)NSString *signatureData __attribute__((deprecated("Deprecated since V2.01.00")));
/**
 Additional lines for printing from host.

 Attribute : ans...32
 @deprecated Since V2.01.00. 
 */
@property (readwrite, nonatomic, copy)NSString *printLine1 __attribute__((deprecated("Deprecated since V2.01.00")));
/**
 Additional lines for printing from host.

 Attribute : ans...32
 @deprecated Since V2.01.00. 
 */
@property (readwrite, nonatomic, copy)NSString *printLine2 __attribute__((deprecated("Deprecated since V2.01.00")));
/**
 Additional lines for printing from host.

 Attribute : ans...32
 @deprecated Since V2.01.00. 
 */
@property (readwrite, nonatomic, copy)NSString *printLine3 __attribute__((deprecated("Deprecated since V2.01.00")));
/**
 Additional lines for printing from host.

 Attribute : ans...32
 @deprecated Since V2.01.00. 
 */
@property (readwrite, nonatomic, copy)NSString *printLine4 __attribute__((deprecated("Deprecated since V2.01.00")));
/**
 Additional lines for printing from host.

 Attribute : ans...32
 @deprecated Since V2.01.00. 
 */
@property (readwrite, nonatomic, copy)NSString *printLine5 __attribute__((deprecated("Deprecated since V2.01.00")));

@end