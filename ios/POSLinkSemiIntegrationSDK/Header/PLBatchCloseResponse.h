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
 Batch Close Response
 */

#import <Foundation/Foundation.h>
#import "PLResponse.h"
#import "PLSemiConst.h"
#import "PLAdminConst.h"
#import "PLTotalTax.h"
#import "PLTipCount.h"
#import "PLTipAmount.h"
#import "PLTotalCount.h"
#import "PLTotalAmount.h"
#import "PLMerchantTotal.h"
#import "PLTipCount.h"
#import "PLTipAmount.h"
#import "PLTotalTax.h"
#import "PLTorResponse.h"
#import "PLMultiMerchant.h"
#import "PLTotalAmount.h"
#import "PLTotalCount.h"
#import "PLAdditionalResponseData.h"
#import "PLHostInformationResponse.h"
#import "PLBatchCloseResponse.h"

@interface PLBatchCloseResponse : PLResponse
/**
 Host information. 
 */
@property (readwrite, nonatomic, strong)PLHostInformationResponse *hostInformation;
/**
 Total Count.

 Attribute : var 
 */
@property (readwrite, nonatomic, strong)PLTotalCount *totalCount;
/**
 Total Amount.

 Attribute : var 
 */
@property (readwrite, nonatomic, strong)PLTotalAmount *totalAmount;
/**
 The date time, YYYYMMDDhhmmss,	If ECR doesn't send time stamp to terminal, this field is mandatory.

 Attribute : n14 
 */
@property (readwrite, nonatomic, copy)NSString *timeStamp;
/**
 Terminal ID, If terminal id exists, this field is mandatory.

 Attribute : ans...20 
 */
@property (readwrite, nonatomic, copy)NSString *tid;
/**
 Merchant ID, if merchant id exists, this field is mandatory.

 Attribute : ans...20 
 */
@property (readwrite, nonatomic, copy)NSString *mid;
/**
 Multiple merchant information. 
 */
@property (readwrite, nonatomic, strong)PLMultiMerchant *multiMerchant;
/**
 Transaction number for failed transaction in terminal database during the BATCH uploading process for NON SAF transaction.

Batch process is blocked and failed transaction is still in normal database.

 Attribute : n...4 
 */
@property (readwrite, nonatomic, copy)NSString *failedTransactionNumber;
/**
 Number of failed records during the BATCH uploading process for NON SAF transaction.

Batch process has not been blocked for failed uploading while failed transaction has been moved to failed database.

 Attribute : n...4 
 */
@property (readwrite, nonatomic, copy)NSString *failedCount;
/**
 Number of failed records during the SAF uploading process.

 Attribute : n...4 
 */
@property (readwrite, nonatomic, copy)NSString *safFailedCount;
/**
 Number of total records in SAF failed Database after the SAF uploading process.

 Attribute : n...4 
 */
@property (readwrite, nonatomic, copy)NSString *safFailedTotal;
/**
 TOR Information. 
 */
@property (readwrite, nonatomic, strong)PLTorResponse *torInformation;
/**
 Total amount for all supported EDCs excluding Credit Void and Credit Auth transactions returned in host message.

 Attribute : n...9 
 */
@property (readwrite, nonatomic, copy)NSString *hostSettledAmount;
/**
 Total count for all supported EDCs excluding Credit Void and Credit Auth transactions returned in host message.

 Attribute : n...4 
 */
@property (readwrite, nonatomic, copy)NSString *hostSettledCount;
/**
 Total return amount for all supported EDCs excluding Credit Void and Credit Auth transactions returned in the host message.

 Attribute : n...9 
 */
@property (readwrite, nonatomic, copy)NSString *hostSettledReturnAmount;
/**
 Return count for all supported EDCs excluding Credit Void and Credit Auth transactions returned in the host message.

 Attribute : n...4 
 */
@property (readwrite, nonatomic, copy)NSString *hostSettledReturnCount;
/**
 Total Taxes. 
 */
@property (readwrite, nonatomic, copy)NSArray<PLTotalTax *> *totalTaxes;
/**
 Tip Amount.

 Attribute : var 
 */
@property (readwrite, nonatomic, strong)PLTipAmount *tipAmount;
/**
 Total Count.

 Attribute : var 
 */
@property (readwrite, nonatomic, strong)PLTipCount *tipCount;
/**
 Merchant Totals. 
 */
@property (readwrite, nonatomic, copy)NSArray<PLMerchantTotal *> *merchantTotals;


@end