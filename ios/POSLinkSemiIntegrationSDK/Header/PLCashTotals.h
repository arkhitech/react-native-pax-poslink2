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
 CashTotalData
 */

#import <Foundation/Foundation.h>
#import "PLSemiConst.h"
#import "PLAdminConst.h"
#import "PLCashTotals.h"

@interface PLCashTotals : NSObject
/**
 SaleCount

 Attribute : n...4 
 */
@property (readwrite, nonatomic, copy)NSString *saleCount;
/**
 SaleAmount

 Attribute : n...9 
 */
@property (readwrite, nonatomic, copy)NSString *saleAmount;
/**
 ReturnCount

 Attribute : n...4 
 */
@property (readwrite, nonatomic, copy)NSString *returnCount;
/**
 ReturnAmount

 Attribute : n...9 
 */
@property (readwrite, nonatomic, copy)NSString *returnAmount;

@end