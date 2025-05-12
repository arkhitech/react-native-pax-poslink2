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
 COF Response.
 */

#import <Foundation/Foundation.h>
#import "PLSemiConst.h"
#import "PLAdminConst.h"
#import "PLCofResponse.h"

@interface PLCofResponse : NSObject
/**
 Token returned from the host to use for future transactions.

 Attribute : ans...128 
 */
@property (readwrite, nonatomic, copy)NSString *token;
/**
 Returned by the host, it is used to specify the serial number for the account where the card token is stored.

 Attribute : ans...10 
 */
@property (readwrite, nonatomic, copy)NSString *tokenSerialNumber;
/**
 A unique transaction identifier from bank/card brand. Use this for follow up COF transactions.

 Attribute : ans...16 
 */
@property (readwrite, nonatomic, copy)NSString *transactionIdentifier;
/**
 Payment Service 2000.

Data returned as part of the original authorization response from the issuer, used in follow up transactions (token/card-on-file, reversals, incremental). Format varies by card scheme.

 Attribute : an...22 
 */
@property (readwrite, nonatomic, copy)NSString *paymentService2000;
/**
 Authorization data used in follow up transactions.

 Attribute : an...66 
 */
@property (readwrite, nonatomic, copy)NSString *authorizationResponse;

@end