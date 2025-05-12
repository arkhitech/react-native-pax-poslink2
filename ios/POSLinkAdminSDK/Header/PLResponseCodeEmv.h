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
 Response Code for EMV.
 */

#import <Foundation/Foundation.h>

#import "PLAdminConst.h"
#import "PLEmvCode100003.h"
#import "PLResponseCodeEmv.h"

@interface PLResponseCodeEmv : NSObject
/**
 Error code 100003. 
 */
@property (readwrite, nonatomic, strong)PLEmvCode100003 *code100003;

@end