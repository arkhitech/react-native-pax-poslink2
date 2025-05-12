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
 COF request information.
 */

#import <Foundation/Foundation.h>
#import "PLSemiConst.h"
#import "PLAdminConst.h"
#import "PLCofRequest.h"

@interface PLCofRequest : NSObject
/**
 Send token request indicator to host to request token. By default, the terminal does not request for a token.

Note: The terminal returns a token only when the host returns it. It is possible to receive a token in the response message without setting this flag depending on the host and vice versa.

 Attribute : ans...32 
 */
@property (readwrite, nonatomic, assign)enum TokenRequestFlag tokenRequestFlag;
/**
 Token value.

 Attribute : ans...128 
 */
@property (readwrite, nonatomic, copy)NSString *token;
/**
 It is used to specify the serial number for the account where the card token is stored.

 Attribute : ans...10 
 */
@property (readwrite, nonatomic, copy)NSString *tokenSerialNumber;
/**
 Indicates if the transaction is Credential/Card on File (CoF) transaction. Flag will be ignored if the transaction doesn't support CoF feature. 
 */
@property (readwrite, nonatomic, assign)enum CofIndicator cofIndicator;
/**
 Indicates if the credential/card on file (CoF) transaction is customer initiated or merchant initiated. Utilized for subsequent CoF transactions (a token is used to process the transaction), otherwise it will be ignored for other transactions. 
 */
@property (readwrite, nonatomic, assign)enum CofInitiator cofInitiator;
/**
 Transaction Identifier of the previous or original transaction. It's used for Credential/Card on File (CoF) transactions.

 Attribute : ans...16 
 */
@property (readwrite, nonatomic, copy)NSString *originalTransactionIdentifier;
/**
 Original Payment Service 2000.

Data returned as part of the original authorization response from the issuer, used in follow up transactions (token/card-on-file, reversals, incremental). Format varies by card scheme.

 Attribute : an...22 
 */
@property (readwrite, nonatomic, copy)NSString *originalPaymentService2000;
/**
 Original authorization data used in follow up transactions.

 Attribute : an...66 
 */
@property (readwrite, nonatomic, copy)NSString *originalAuthorizationResponse;

@end