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
 Signature response information.
 */

#import <Foundation/Foundation.h>
#import "PLSemiConst.h"
#import "PLAdminConst.h"
#import "PLSignatureResponse.h"

@interface PLSignatureResponse : NSObject
/**
 The signature status.

 Attribute : n1 
 */
@property (readwrite, nonatomic, assign)enum SignatureResponseStatus signatureStatus;
/**
 Signature data captured by terminal for current.

 Attribute : ans...3072 
 */
@property (readwrite, nonatomic, copy)NSString *signatureData;

@end