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
 Total Tax.
 */

#import <Foundation/Foundation.h>
#import "PLSemiConst.h"
#import "PLAdminConst.h"
#import "PLTotalTax.h"

@interface PLTotalTax : NSObject
/**
 Tax type. 
 */
@property (readwrite, nonatomic, assign)enum TaxType taxType;
/**
 Total tax amount applied to Credit transactions. The format is $$$$$$$CC.

 Attribute : n...9 
 */
@property (readwrite, nonatomic, copy)NSString *creditTotalTaxAmount;
/**
 Total tax amount applied to Debit transactions. The format is $$$$$$$CC.

 Attribute : n...9 
 */
@property (readwrite, nonatomic, copy)NSString *debitTotalTaxAmount;
/**
 Total tax amount applied to EBT transactions. The format is $$$$$$$CC.

 Attribute : n...9 
 */
@property (readwrite, nonatomic, copy)NSString *ebtTotalTaxAmount;
/**
 Total tax amount applied to Gift transactions. The format is $$$$$$$CC.

 Attribute : n...9 
 */
@property (readwrite, nonatomic, copy)NSString *giftTotalTaxAmount;
/**
 Total tax amount applied to Loyalty transactions. The format is $$$$$$$CC.

 Attribute : n...9 
 */
@property (readwrite, nonatomic, copy)NSString *loyaltyTotalTaxAmount;
/**
 Total tax amount applied to QR Code transactions. The format is $$$$$$$CC.

 Attribute : n...9 
 */
@property (readwrite, nonatomic, copy)NSString *qrCodeTotalTaxAmount;
/**
 Total tax amount applied to Cash transactions. The format is $$$$$$$CC.

 Attribute : n...9 
 */
@property (readwrite, nonatomic, copy)NSString *cashTotalTaxAmount;

@end