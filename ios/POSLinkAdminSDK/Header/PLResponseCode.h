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
 Response Code.
 */

#import <Foundation/Foundation.h>

#import "PLAdminConst.h"
#import "PLCode104000.h"
#import "PLCode100101.h"
#import "PLCode100033.h"
#import "PLCode100023.h"
#import "PLCode100021.h"
#import "PLCode100020.h"
#import "PLCode100014.h"
#import "PLCode100010.h"
#import "PLCode100004.h"
#import "PLCode100003.h"
#import "PLResponseCode.h"

@interface PLResponseCode : NSObject
/**
 Approved online. Need to return host response message. 
 */
@property (readwrite, nonatomic, copy)NSString *ok;
/**
 The transaction has been approved offline/locally by the application. There will be no response from the host. Transaction will be uploaded to the host during batch close.

Transactions will approve offline under the following conditions.

1. Store and Forward (SAF) is enabled.

2. Fast Payment Service (FPS) is enabled.

3. Transaction Type is ForceAuthorization.

4. Transaction Amount is less than online floor limit.

5. Offline Return for some Terminal Capture applications i.e. Omaha TC, TSYS TC, Paymentech TC, Elavon TC...etc. 
 */
@property (readwrite, nonatomic, copy)NSString *approvedOffline;
/**
 Transaction is partially approved. 
 */
@property (readwrite, nonatomic, copy)NSString *partiallyApproved;
/**
 The transaction was approved, but surcharge fee was not applied. 
 */
@property (readwrite, nonatomic, copy)NSString *approvedSurchargeNotApplied;
/**
 Batch closed some transactions successfully, but some SAF transactions have declined. Check the Failed SAF database. 
 */
@property (readwrite, nonatomic, copy)NSString *partialBatchClosed;
/**
 Host error (See host specific errors). 
 */
@property (readwrite, nonatomic, copy)NSString *decline;
/**
 Payment Transaction was not processed, only the track data for the nonpayment card was returned. (Card was not processed for any form of payment). 
 */
@property (readwrite, nonatomic, copy)NSString *cardReadOk;
/**
 Batch Close successful but the batch is out of balance. The closed batch needs to be reconciled by the host. 
 */
@property (readwrite, nonatomic, copy)NSString *outOfBalance;
/**
 Batch has already been closed once but there are only offline failed records in the database. Please contact the host to reconcile failed transactions and delete failed offline records from the database. 
 */
@property (readwrite, nonatomic, copy)NSString *safFailedPleaseSettle;
/**
 Timeout. 
 */
@property (readwrite, nonatomic, copy)NSString *timeout;
/**
 User aborted. 
 */
@property (readwrite, nonatomic, copy)NSString *aborted;
/**
 Error code 100003. 
 */
@property (readwrite, nonatomic, strong)PLCode100003 *code100003;
/**
 Error code 100004. 
 */
@property (readwrite, nonatomic, strong)PLCode100004 *code100004;
/**
 Terminal unsupported the EDC type. 
 */
@property (readwrite, nonatomic, copy)NSString *unsupportEdc;
/**
 Batch fail. 
 */
@property (readwrite, nonatomic, copy)NSString *batchFailed;
/**
 Returned when the terminal is unable to parse the incoming host response. The terminal will automatically send timeout reversal (TOR). 
 */
@property (readwrite, nonatomic, copy)NSString *invalidResponseMessage;
/**
 Send message to host error. 
 */
@property (readwrite, nonatomic, copy)NSString *sendError;
/**
 Response is not returned from the host after a request has been sent to the host. 
 */
@property (readwrite, nonatomic, copy)NSString *receiveError;
/**
 Error code 100010. 
 */
@property (readwrite, nonatomic, strong)PLCode100010 *code100010;
/**
 The application has declined the transaction locally due to it being a duplicate transaction. Fields that are returned will be the transaction data from the original transaction. If the terminal returns this error code, the operator should confirm if this is a new transaction. The ECR can resend the pack with "Dup Override Flag" set. 
 */
@property (readwrite, nonatomic, copy)NSString *dupTransaction;
/**
 Get variable error. 
 */
@property (readwrite, nonatomic, copy)NSString *getVarError;
/**
 Missing variable name. 
 */
@property (readwrite, nonatomic, copy)NSString *varNameMissing;
/**
 Error code 100014. 
 */
@property (readwrite, nonatomic, strong)PLCode100014 *code100014;
/**
 CVV Error. 
 */
@property (readwrite, nonatomic, copy)NSString *cvvError;
/**
 Address or Zip Code error. 
 */
@property (readwrite, nonatomic, copy)NSString *avsError;
/**
 Halo exceed. 
 */
@property (readwrite, nonatomic, copy)NSString *haloExceed;
/**
 Cannot input account. 
 */
@property (readwrite, nonatomic, copy)NSString *swipeOnly;
/**
 The track data is wrong. 
 */
@property (readwrite, nonatomic, copy)NSString *trackInvalid;
/**
 Error code 100020. 
 */
@property (readwrite, nonatomic, strong)PLCode100020 *code100020;
/**
 Error code 100021. 
 */
@property (readwrite, nonatomic, strong)PLCode100021 *code100021;
/**
 PINPAD error. 
 */
@property (readwrite, nonatomic, copy)NSString *pinpadError;
/**
 Error code 100023. 
 */
@property (readwrite, nonatomic, strong)PLCode100023 *code100023;
/**
 There is no host application. 
 */
@property (readwrite, nonatomic, copy)NSString *noHostApp;
/**
 One or more local transaction record databases are full. However, the user may still run transactions of the following types:

  - Post-Auth

  - Incremental Auth

  - Void

  - Tip Adjustment

  - Return by Reference Number

Please settle or clear existing transaction records before attempting new transactions. 
 */
@property (readwrite, nonatomic, copy)NSString *pleaseSettle;
/**
 When the command is unsupported. 
 */
@property (readwrite, nonatomic, copy)NSString *unsupportCommand;
/**
 Tax amount is greater than the total amount. 
 */
@property (readwrite, nonatomic, copy)NSString *taxExceedAmount;
/**
 Printer not applicable. 
 */
@property (readwrite, nonatomic, copy)NSString *printerNotSupported;
/**
 Printer disabled. 
 */
@property (readwrite, nonatomic, copy)NSString *printerDisabled;
/**
 Out of paper. 
 */
@property (readwrite, nonatomic, copy)NSString *outOfPaper;
/**
 Error code 100033. 
 */
@property (readwrite, nonatomic, strong)PLCode100033 *code100033;
/**
 Insufficient fund user decline. 
 */
@property (readwrite, nonatomic, copy)NSString *insufficientFundUserDecline;
/**
 Data storage key is missing. 
 */
@property (readwrite, nonatomic, copy)NSString *dataStorageKeyMissing;
/**
 P2PE Key is missing. 
 */
@property (readwrite, nonatomic, copy)NSString *hostP2peKeyMissing;
/**
 Error code 100101. 
 */
@property (readwrite, nonatomic, strong)PLCode100101 *code100101;
/**
 User decline to pay online surcharge fee. 
 */
@property (readwrite, nonatomic, copy)NSString *onlineSurchargeFeeUserDecline;
/**
 PED is busy. 
 */
@property (readwrite, nonatomic, copy)NSString *pedBusy;
/**
 PED injection error. 
 */
@property (readwrite, nonatomic, copy)NSString *pedInjectionError;
/**
 The specified MAC key type mismatches the specified algorithm. 
 */
@property (readwrite, nonatomic, copy)NSString *macKeyTypeMismatch;
/**
 The encryption key type mismatches the specified algorithm. 
 */
@property (readwrite, nonatomic, copy)NSString *encryptionKeyTypeMismatch;
/**
 PED parameter error. 
 */
@property (readwrite, nonatomic, copy)NSString *pedParameterError;
/**
 PED general error. 
 */
@property (readwrite, nonatomic, copy)NSString *pedGeneralError;
/**
 PED unknown error. 
 */
@property (readwrite, nonatomic, copy)NSString *pedUnknownError;
/**
 Terminal detected WIC card while it's not for WIC. 
 */
@property (readwrite, nonatomic, copy)NSString *wicCardDetected;
/**
 Service is busy. 
 */
@property (readwrite, nonatomic, copy)NSString *serviceBusy;
/**
 Card not accepted. 
 */
@property (readwrite, nonatomic, copy)NSString *notAccepted;
/**
 User didn't agree the EULA. 
 */
@property (readwrite, nonatomic, copy)NSString *eulaDisagreed;
/**
 The creation of the bar code file fails. 
 */
@property (readwrite, nonatomic, copy)NSString *createBarcodeError;
/**
 Scanner scan error. 
 */
@property (readwrite, nonatomic, copy)NSString *scannerScanError;
/**
 The external card reader is busy and unavailable for card entry activities, such as while the application updates the reader's files/OS. 

Please wait a few minutes and try again. 
 */
@property (readwrite, nonatomic, copy)NSString *externalCardReaderIsBusy;
/**
 An error occurred while the application was updating the external card reader's files or OS. The device may not be used.

Please open the application to retry the update. 
 */
@property (readwrite, nonatomic, copy)NSString *externalCardReaderUpdateFailed;
/**
 Source key level lower than destination. 
 */
@property (readwrite, nonatomic, copy)NSString *sourceKeyLevelLowerThanDestination;
/**
 BroadPOS is downloading parameters from cloud server. 
 */
@property (readwrite, nonatomic, copy)NSString *downloadingParameters;
/**
 BroadPOS is processing reversal message. 
 */
@property (readwrite, nonatomic, copy)NSString *processingReversal;
/**
 BroadPOS is processing advice message. 
 */
@property (readwrite, nonatomic, copy)NSString *processingAdvice;
/**
 Terminal internal error. 
 */
@property (readwrite, nonatomic, copy)NSString *terminalError;
/**
 Apple or Google pay use VAS function. For VAS OK, but payment failed. 
 */
@property (readwrite, nonatomic, copy)NSString *vasOkPaymentError;
/**
 Apple or Google pay use VAS function. For VAS failed, but payment OK. 
 */
@property (readwrite, nonatomic, copy)NSString *vasErrorPaymentOk;
/**
 Apple or Google pay use VAS function. For VAS ONLY mode, return error. 
 */
@property (readwrite, nonatomic, copy)NSString *vasError;
/**
 Apple or Google pay use VAS function. For VAS and Payment both failed. 
 */
@property (readwrite, nonatomic, copy)NSString *vasErrorPaymentError;
/**
 Google Smart Tap failed, and not perform payment. 
 */
@property (readwrite, nonatomic, copy)NSString *vasErrorPaymentNotPerformed;
/**
 Google Smart Tap OK, but not perform payment. 
 */
@property (readwrite, nonatomic, copy)NSString *vasOkPaymentNotPerformed;
/**
 Interface chip does not exist or abnormal. 
 */
@property (readwrite, nonatomic, copy)NSString *interfaceChipDoesNotExistOrAbnormal;
/**
 Parameter error. 
 */
@property (readwrite, nonatomic, copy)NSString *parameterError;
/**
 RF module closed. 
 */
@property (readwrite, nonatomic, copy)NSString *rfModuleClose;
/**
 No specific card in sensing area, or the card is not activated. 
 */
@property (readwrite, nonatomic, copy)NSString *notDetectCard;
/**
 Too much card in sensing area, communication conflict. 
 */
@property (readwrite, nonatomic, copy)NSString *communicationConflict;
/**
 Protocol error, The data response from card breaches the agreement. 
 */
@property (readwrite, nonatomic, copy)NSString *cardDataProtocolError;
/**
 Card not activated. 
 */
@property (readwrite, nonatomic, copy)NSString *cardNotActivated;
/**
 Multi-card conflict. 
 */
@property (readwrite, nonatomic, copy)NSString *multiCardConflict;
/**
 No response timeout. 
 */
@property (readwrite, nonatomic, copy)NSString *noResponseTimeout;
/**
 Protocol error. 
 */
@property (readwrite, nonatomic, copy)NSString *protocolError;
/**
 Communication transmission error. 
 */
@property (readwrite, nonatomic, copy)NSString *communicationTransmissionError;
/**
 M1 Card authentication failure. 
 */
@property (readwrite, nonatomic, copy)NSString *m1CardAuthenticationFailure;
/**
 Sector is not certified. 
 */
@property (readwrite, nonatomic, copy)NSString *sectorIsNotCertified;
/**
 The data format of value block is incorrect. 
 */
@property (readwrite, nonatomic, copy)NSString *valueBlockFormatError;
/**
 Card is still in sensing area. 
 */
@property (readwrite, nonatomic, copy)NSString *cardIsStillInSensingArea;
/**
 Card status error. 
 */
@property (readwrite, nonatomic, copy)NSString *cardStatusError;
/**
 MIFARE card operation Unknown error. 
 */
@property (readwrite, nonatomic, copy)NSString *unknownError;
/**
 Parameter error. 
 */
@property (readwrite, nonatomic, copy)NSString *parameterInvalid;
/**
 RPC I/O error. 
 */
@property (readwrite, nonatomic, copy)NSString *rpcIoError;
/**
 Not Support for this device. 
 */
@property (readwrite, nonatomic, copy)NSString *notSupportForThisDevice;
/**
 Not Permission for picc. 
 */
@property (readwrite, nonatomic, copy)NSString *notPermissionForPicc;
/**
 RPC Busy. 
 */
@property (readwrite, nonatomic, copy)NSString *rpcBusy;
/**
 Tip form response an error index. 
 */
@property (readwrite, nonatomic, copy)NSString *customUiTipIndexError;
/**
 Cashback form response an error index. 
 */
@property (readwrite, nonatomic, copy)NSString *customUiCashbackIndexError;
/**
 EBT type form response an error index. 
 */
@property (readwrite, nonatomic, copy)NSString *customUiEbtTypeIndexError;
/**
 Pass reason form response an error index. 
 */
@property (readwrite, nonatomic, copy)NSString *customUiPassReasonIndexError;
/**
 AID form response an error index. 
 */
@property (readwrite, nonatomic, copy)NSString *customUiAidIndexError;
/**
 Tax reason form response an error index. 
 */
@property (readwrite, nonatomic, copy)NSString *customUiTaxReasonIndexError;
/**
 Card type form response an error index. 
 */
@property (readwrite, nonatomic, copy)NSString *customUiCardTypeIndexError;
/**
 Transaction type form response an error index. 
 */
@property (readwrite, nonatomic, copy)NSString *customUiTransactionTypeIndexError;
/**
 EDC type form response an error index. 
 */
@property (readwrite, nonatomic, copy)NSString *customUiEdcTypeIndexError;
/**
 Search criteria form response an error index. 
 */
@property (readwrite, nonatomic, copy)NSString *customUiSearchCriteriaIndexError;
/**
 Surcharge fee form response an error index. 
 */
@property (readwrite, nonatomic, copy)NSString *customUiSurchargeFeeIndexError;
/**
 The "Card present confirm" UI entry action must return a boolean value. 
 */
@property (readwrite, nonatomic, copy)NSString *customUiCardPresentConfirmError;
/**
 Dulicate transaction confirm ui entry action must return boolean. 
 */
@property (readwrite, nonatomic, copy)NSString *customUiDuplicateTransactionConfirmError;
/**
 Card deactivated warn confirm ui entry action must return boolean. 
 */
@property (readwrite, nonatomic, copy)NSString *customUiDeactivateWarnConfirmError;
/**
 Surcharge fee confirm ui entry action must return boolean. 
 */
@property (readwrite, nonatomic, copy)NSString *customUiSurchargeFeeConfirmError;
/**
 Delete SAF transaction confirm ui entry action must return boolean. 
 */
@property (readwrite, nonatomic, copy)NSString *customUiDelSafConfirmError;
/**
 Print customer copy confirm ui entry action must return boolean. 
 */
@property (readwrite, nonatomic, copy)NSString *customUiPrintCustomerCopyConfirmError;
/**
 Print failed transaction confirm ui entry action must return boolean. 
 */
@property (readwrite, nonatomic, copy)NSString *customUiPrintFailedTransactionConfirmError;
/**
 Print FPS confirm ui entry action must return boolean. 
 */
@property (readwrite, nonatomic, copy)NSString *customUiPrintFpsConfirmError;
/**
 Print status confirm ui entry action must return boolean 
 */
@property (readwrite, nonatomic, copy)NSString *customUiPrintStatusConfirmError;
/**
 Amount confirm ui entry action must return boolean. 
 */
@property (readwrite, nonatomic, copy)NSString *customUiAmountConfirmError;
/**
 Partial approval confirm ui entry action must return boolean. 
 */
@property (readwrite, nonatomic, copy)NSString *customUiPartialApprovalConfirmError;
/**
 Contactless EMV card or device could not be read or was removed too quickly. 
 */
@property (readwrite, nonatomic, copy)NSString *tapReadFailed;
/**
 Contact EMV card or device could not be read or was removed too quickly. 
 */
@property (readwrite, nonatomic, copy)NSString *chipReadFailed;
/**
 The host has declined the transaction due to it being a duplicate transaction. When this error is thrown, the transaction response will include the original transaction data from the host such as Host Account, Host Card Type, and Approved Amount, if the host returns it. 
 */
@property (readwrite, nonatomic, copy)NSString *hostDeclineDuplicateTransaction;
/**
 Error code 104000. 
 */
@property (readwrite, nonatomic, strong)PLCode104000 *code104000;

@end