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
 eWIC information request.
 */

#import <Foundation/Foundation.h>
#import "PLSemiConst.h"
#import "PLAdminConst.h"
#import "PLEwicData.h"
#import "PLEwicInformationRequest.h"

@interface PLEwicInformationRequest : NSObject
/**
 The request eWIC product details. 
 */
@property (readwrite, nonatomic, copy)NSArray<PLEwicData *> *ewicData;
/**
 The Discount for eWIC transactions.

 Attribute : n...9 
 */
@property (readwrite, nonatomic, copy)NSString *ewicDiscountAmount;

@end