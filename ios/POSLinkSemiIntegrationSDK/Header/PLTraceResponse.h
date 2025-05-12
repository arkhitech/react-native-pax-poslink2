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
 Response Trace Information.
 */

#import <Foundation/Foundation.h>
#import "PLSemiConst.h"
#import "PLAdminConst.h"
#import "PLTraceResponse.h"

@interface PLTraceResponse : NSObject
/**
 The transaction record number in the terminal, suggest printing this on receipt.

0 < Index <= 5000, normal data base.

5000 < Index <= 9000, stored data base for SAF.

9000 < Index <= 9999, failed data base for SAF.

 Attribute : n...4 
 */
@property (readwrite, nonatomic, copy)NSString *referenceNumber;
/**
 The ECR reference number, echo back.

 Attribute : ans...32 
 */
@property (readwrite, nonatomic, copy)NSString *ecrReferenceNumber;
/**
 The date time, YYYYMMDDhhmmss.

 Attribute : n14 
 */
@property (readwrite, nonatomic, copy)NSString *timeStamp;
/**
 POS system invoice/tracking number.

 Attribute : ans...50 
 */
@property (readwrite, nonatomic, copy)NSString *invoiceNumber;
/**
 The serial number of the device.

 Attribute : ans...32 
 */
@property (readwrite, nonatomic, copy)NSString *sn;
/**
 Transaction settlement date, the format is YYYYMMDD.

 Attribute : n8 
 */
@property (readwrite, nonatomic, copy)NSString *settlementDate;
/**
 A unique ID for each transaction.

 Attribute : ans...64 
 */
@property (readwrite, nonatomic, copy)NSString *globalUid;

@end