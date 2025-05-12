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
 Merchant Total.
 */

#import <Foundation/Foundation.h>
#import "PLSemiConst.h"
#import "PLAdminConst.h"
#import "PLTotalTax.h"
#import "PLTipCount.h"
#import "PLTipAmount.h"
#import "PLTotalCount.h"
#import "PLTotalAmount.h"
#import "PLMerchantTotal.h"

@interface PLMerchantTotal : NSObject
/**
 Merchants account code/identifier/access token.

 Attribute : ans...20 
 */
@property (readwrite, nonatomic, copy)NSString *mid;
/**
 Terminal Identifier.

 Attribute : ans...20 
 */
@property (readwrite, nonatomic, copy)NSString *tid;
/**
 Total Amount. 
 */
@property (readwrite, nonatomic, strong)PLTotalAmount *totalAmount;
/**
 Total Count. 
 */
@property (readwrite, nonatomic, strong)PLTotalCount *totalCount;
/**
 Tip Amount. 
 */
@property (readwrite, nonatomic, strong)PLTipAmount *tipAmount;
/**
 Tip Count. 
 */
@property (readwrite, nonatomic, strong)PLTipCount *tipCount;
/**
 Totals amount of tax for each tax type. 
 */
@property (readwrite, nonatomic, copy)NSArray<PLTotalTax *> *totalTaxes;
/**
 Total amount for all supported EDCs excluding Credit Void and Credit Auth transactions returned in host message. The format is $$$$$$$CC.

 Attribute : n...9 
 */
@property (readwrite, nonatomic, copy)NSString *hostSettledAmount;
/**
 Total Count for all supported EDCs excluding Credit Void and Credit Auth transactions returned in host message.

 Attribute : n...4 
 */
@property (readwrite, nonatomic, copy)NSString *hostSettledCount;
/**
 Total return amount for all supported EDCs excluding Credit Void and Credit Auth transactions returned in the host message. The format is $$$$$$$CC.

 Attribute : n...9 
 */
@property (readwrite, nonatomic, copy)NSString *hostSettledReturnAmount;
/**
 Return count for all supported EDCs excluding Credit Void and Credit Auth transactions returned in the host message.

 Attribute : n...4 
 */
@property (readwrite, nonatomic, copy)NSString *hostSettledReturnCount;
/**
 Host returned the result code, if declined display host error on the ECR's screen.

 Attribute : ans...8 
 */
@property (readwrite, nonatomic, copy)NSString *hostResponseCode;
/**
 Host returned error message. If host returns error message, this field is mandatory.

 Attribute : ans...255 
 */
@property (readwrite, nonatomic, copy)NSString *hostResponseMessage;

@end