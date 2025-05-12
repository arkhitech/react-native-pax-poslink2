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
 Request AVS information.
 */

#import <Foundation/Foundation.h>
#import "PLSemiConst.h"
#import "PLAdminConst.h"
#import "PLAvsRequest.h"

@interface PLAvsRequest : NSObject
/**
 The zip code of the cardholder. The application supports 5 to 9 characters including number, letters, and space.

 Attribute : ans...9 
 */
@property (readwrite, nonatomic, copy)NSString *zipCode;
/**
 The primary address of the cardholder. The actual maximum length is host-dependent but may not exceed 32 characters.

 Attribute : ans...32 
 */
@property (readwrite, nonatomic, copy)NSString *address;
/**
 The secondary address of the cardholder. The actual maximum length is host-dependent but may not exceed 32 characters.

 Attribute : ans...32 
 */
@property (readwrite, nonatomic, copy)NSString *address2;

@end