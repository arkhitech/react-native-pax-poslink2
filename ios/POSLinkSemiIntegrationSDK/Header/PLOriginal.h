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
 @deprecated Since V2.01.00.
 Response Original Information.
 */

#import <Foundation/Foundation.h>
#import "PLSemiConst.h"
#import "PLAdminConst.h"
#import "PLOriginal.h"

__attribute__((deprecated("Deprecated since V2.01.00")))
@interface PLOriginal : NSObject
/**
 Original Transaction Date in YYYYMMDD format.

Conditional for some hosts for subsequent transactions after Sale, i.e. Return…

If the Host requires this field, the current date on the terminal will be sent.

 Attribute : n8
 @deprecated Since V2.01.00. 
 */
@property (readwrite, nonatomic, copy)NSString *transactionDate __attribute__((deprecated("Deprecated since V2.01.00")));
/**
 Last 4 digits of account number for original transaction.

Conditional for matching card on terminal in subsequent transactions after Sale, i.e. Return…

 Attribute : n4
 @deprecated Since V2.01.00. 
 */
@property (readwrite, nonatomic, copy)NSString *pan __attribute__((deprecated("Deprecated since V2.01.00")));
/**
 Expiry date in MMYY of account number for original transaction.

Conditional for matching card on terminal in subsequent transactions after Sale, i.e. Return…

 Attribute : n4
 @deprecated Since V2.01.00. 
 */
@property (readwrite, nonatomic, copy)NSString *expiryDate __attribute__((deprecated("Deprecated since V2.01.00")));
/**
 Original Transaction Time in HHMMSS format.

Conditional for some hosts for subsequent transactions after Sale, i.e. Return…

If the Host requires this field, the current date on the terminal will be sent.

 Attribute : n6
 @deprecated Since V2.01.00. 
 */
@property (readwrite, nonatomic, copy)NSString *transactionTime __attribute__((deprecated("Deprecated since V2.01.00")));
/**
 Original transaction type. Used for follow up transactions.
 @deprecated Since V2.01.00. 
 */
@property (readwrite, nonatomic, assign)enum TransactionType transactionType __attribute__((deprecated("Deprecated since V2.01.00")));
/**
 The Original Amount, $$$$$$$CC.

 Attribute : n...9
 @deprecated Since V2.01.00. 
 */
@property (readwrite, nonatomic, copy)NSString *amount __attribute__((deprecated("Deprecated since V2.01.00")));

@end