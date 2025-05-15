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
 Card Insert Detection Response
 */

#import <Foundation/Foundation.h>
#import "PLResponse.h"

#import "PLAdminConst.h"
#import "PLCardInsertDetectionResponse.h"

@interface PLCardInsertDetectionResponse : PLResponse
/**
 Card Insert Status.

 Attribute : n1 
 */
@property (readwrite, nonatomic, assign)enum CardInsertStatus cardInsertStatus;


@end