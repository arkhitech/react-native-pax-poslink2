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
 Ewic Information Response.
 */

#import <Foundation/Foundation.h>
#import "PLSemiConst.h"
#import "PLAdminConst.h"
#import "PLEwicDetail.h"
#import "PLEwicBalance.h"
#import "PLEwicInformationResponse.h"

@interface PLEwicInformationResponse : NSObject
/**
 eWIC Balance information group.

 Attribute : ans...256 
 */
@property (readwrite, nonatomic, copy)NSArray<PLEwicBalance *> *ewicBalance;
/**
 eWIC Detail information group.

 Attribute : ans...256 
 */
@property (readwrite, nonatomic, copy)NSArray<PLEwicDetail *> *ewicDetail;
/**
 The earliest expiration date for the benefit among the items returned in ewicBalance. Format is YYYYMMDD.

 Attribute : n8 
 */
@property (readwrite, nonatomic, copy)NSString *earliestBenefitExpiryDate;

@end