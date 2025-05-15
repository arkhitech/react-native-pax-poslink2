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
 Transaction behavior request.
 */

#import <Foundation/Foundation.h>
#import "PLSemiConst.h"
#import "PLAdminConst.h"
#import "PLTransactionPromptBitmap.h"
#import "PLEntryModeBitmap.h"
#import "PLProgramBitmap.h"
#import "PLCardTypeBitmap.h"
#import "PLTransactionBehaviorRequest.h"

@interface PLTransactionBehaviorRequest : NSObject
/**
 Tip request flag.

 Attribute : n1 
 */
@property (readwrite, nonatomic, assign)enum TipRequestFlag tipRequestFlag;
/**
 Whether to report status back.

 Attribute : n1 
 */
@property (readwrite, nonatomic, assign)enum StatusReportFlag statusReportFlag;
/**
 The card type bitmap.

It makes POS system available to control which card types accepted for current command. It is mandatory for fleet transactions.

 Attribute : n32 
 */
@property (readwrite, nonatomic, strong)PLCardTypeBitmap *acceptedCardType;
/**
 8 digits bitmap for disabling program prompts.

The default prompt is always on and depending on the bin file. 

 Attribute : n8 
 */
@property (readwrite, nonatomic, strong)PLProgramBitmap *programPromptsFlag;
/**
 The entry mode bitmap. It makes POS system available to control which entry mode accepted for current command.

It makes POS system available to control which entry mode accepted for current command.

 Attribute : n8 
 */
@property (readwrite, nonatomic, strong)PLEntryModeBitmap *entryMode;
/**
 Receipt printing for current command. The default value is NoReceipt.

 Attribute : n1 
 */
@property (readwrite, nonatomic, assign)enum ReceiptPrintFlag receiptPrintFlag;
/**
 Card Present mode.

 Attribute : n1 
 */
@property (readwrite, nonatomic, assign)enum CardPresentMode cardPresentMode;
/**
 Indicate which debit network to use.

 Attribute : n1 
 */
@property (readwrite, nonatomic, assign)enum DebitNetwork debitNetwork;
/**
 User language.

 Attribute : n1 
 */
@property (readwrite, nonatomic, assign)enum UserLanguage userLanguage;
/**
 Additional response data request flag.

Default is No.

 Attribute : n1 
 */
@property (readwrite, nonatomic, assign)enum AdditionalResponseDataFlag additionalResponseDataFlag;
/**
 Duplicate transaction override flag, if this flag is set to "1" the host will not check for duplicate, also it will override the local duplicate check. If the value is others, host dup check will be controlled by application parameter.

 Attribute : n1 
 */
@property (readwrite, nonatomic, copy)NSString *forceDuplicate;
/**
 The standard of accessibility to support and the status and visibility of "Accessibility Switch" on the Input Account screen.

 Attribute : n1 
 */
@property (readwrite, nonatomic, assign)enum AccessibilityPinPad accessibilityPinPad;
/**
 This is a bitmap that will disable transaction prompts per transaction basis. 

 Attribute : n32 
 */
@property (readwrite, nonatomic, strong)PLTransactionPromptBitmap *transactionPromptBitmap;
/**
 Gift card indicator indicates whether AMEX transactions contain a gift card purchase. 
 */
@property (readwrite, nonatomic, assign)enum GiftCardIndicator giftCardIndicator;
/**
 Continuous screen.

 Attribute : n1 
 */
@property (readwrite, nonatomic, assign)enum ContinuousScreen continuousScreen;

@end