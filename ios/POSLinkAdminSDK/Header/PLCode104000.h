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
 Error code 104000
 */

#import <Foundation/Foundation.h>

#import "PLAdminConst.h"
#import "PLCode104000.h"

@interface PLCode104000 : NSObject
/**
 Location access denied, unable to initialize Dual SIM failover function. 
 */
@property (readwrite, nonatomic, copy)NSString *locationAccessDenied;
/**
 Device Call Access Denied, unable to initialize Dual SIM failover function. 
 */
@property (readwrite, nonatomic, copy)NSString *deviceCallAccessDenied;

@end