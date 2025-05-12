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
 Response AVS Information.
 */

#import <Foundation/Foundation.h>
#import "PLSemiConst.h"
#import "PLAdminConst.h"
#import "PLAvsResponse.h"

@interface PLAvsResponse : NSObject
/**
 The approval code returned by certain hosts' Address Verification Service (AVS). If the host returned this value, the response must include it.

 Attribute : ans...8 
 */
@property (readwrite, nonatomic, copy)NSString *avsApprovalCode;
/**
 The response message returned certain hosts' Address Verification Service (AVS). If the host returned this value, the response must include it.

 Attribute : ans...64 
 */
@property (readwrite, nonatomic, copy)NSString *avsMessage;
/**
 The zip code of the cardholder.

 Attribute : ans...9 
 */
@property (readwrite, nonatomic, copy)NSString *zipCode;
/**
 The primary address of the cardholder.

 Attribute : ans...32 
 */
@property (readwrite, nonatomic, copy)NSString *address1;
/**
 The secondary address of the cardholder.

 Attribute : ans...32 
 */
@property (readwrite, nonatomic, copy)NSString *address2;

@end