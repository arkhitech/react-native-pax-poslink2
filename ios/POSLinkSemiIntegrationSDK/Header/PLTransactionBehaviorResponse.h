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
 Transaction Behavior Response.
 */

#import <Foundation/Foundation.h>
#import "PLSemiConst.h"
#import "PLAdminConst.h"
#import "PLTransactionBehaviorResponse.h"

@interface PLTransactionBehaviorResponse : NSObject
/**
 PIN entry status.

 Attribute : n1 
 */
@property (readwrite, nonatomic, assign)enum PinEntryStatus pinStatusNumber;
/**
 Get the user language status.

 Attribute : n1 
 */
@property (readwrite, nonatomic, assign)enum UserLanguage userLanguageStatus;

@end