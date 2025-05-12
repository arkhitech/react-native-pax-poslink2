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
 Request host credential information.
 */

#import <Foundation/Foundation.h>
#import "PLSemiConst.h"
#import "PLAdminConst.h"
#import "PLHostCredentialRequest.h"

@interface PLHostCredentialRequest : NSObject
/**
 Merchant's account code/identifier/access token.

 Attribute : ans...255 
 */
@property (readwrite, nonatomic, copy)NSString *mid;
/**
 User account for service/additional fees.

 Attribute : ans...150 
 */
@property (readwrite, nonatomic, copy)NSString *serviceUser;
/**
 User account password for service/additional fees.

 Attribute : ans...50 
 */
@property (readwrite, nonatomic, copy)NSString *servicePassword;
/**
 User account name for the merchant. Used to temporarily override merchant credentials for the purpose of linking the transaction to the user account.

 Attribute : ans...15 
 */
@property (readwrite, nonatomic, copy)NSString *userName;
/**
 User account password for the merchant. Used to temporarily override merchant credentials for the purpose linking the transaction to the user account.

 Attribute : ans...15 
 */
@property (readwrite, nonatomic, copy)NSString *password;

@end