package com.paxposlink2;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import java.util.Map;
import java.util.HashMap;
import com.pax.poscore.commsetting.AidlSetting;
// import com.pax.poslink.POSLinkAndroid; //TODO: NEED TO FIX IMPORT FOR POSLinkAndroid
import com.pax.poscore.LogSetting;
// import com.pax.poslink.PosLink;  //TODO: NEED TO FIX IMPORT FOR POSLinkAndroid
// import com.pax.poslink.PaymentRequest;  //TODO: NEED TO FIX IMPORT FOR POSLinkAndroid
// import com.pax.poslink.ProcessTransResult;  //TODO: NEED TO FIX IMPORT FOR POSLinkAndroid
// import com.pax.poslink.PaymentResponse;  //TODO: NEED TO FIX IMPORT FOR POSLinkAndroid
// import com.pax.poslink.BatchRequest;  //TODO: NEED TO FIX IMPORT FOR POSLinkAndroid
// import com.pax.poslink.BatchResponse;  //TODO: NEED TO FIX IMPORT FOR POSLinkAndroid
// import com.pax.poslink.CommSetting;  //TODO: NEED TO FIX IMPORT FOR POSLinkAndroid
import android.os.HandlerThread;
import android.os.Handler;
import android.os.Looper;
import java.lang.Runnable;
import android.util.Log;
import android.content.Context;

@ReactModule(name = PaxPoslink2Module.NAME)
public class PaxPoslink2Module extends ReactContextBaseJavaModule {
  public static final String NAME = "PaxPoslink2";
//  private PosLink poslink;
  private HandlerThread handlerThread;
  private Context context;

  public PaxPoslink2Module(ReactApplicationContext reactContext) {

    super(reactContext);
    this.context = reactContext;

  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }


  @ReactMethod
  public void initPOSLink(String type, String timeout, Boolean proxy, String ip, String port, Promise promise) {

    try {
      LogSetting logSetting = new LogSetting();

      logSetting.setLogMode(true); // Open or close log
      logSetting.setLevel(LogSetting.LOGLEVEL.DEBUG); // Set log level
      logSetting.setLogFileName("POSLinkLog"); // Set the file name of output log
      logSetting.setOutputPath("sdcard/log"); // Set path for output log
      logSetting.setLogDays("30"); // Keep log for 30 days

      // Setting connection configurations
      CommunicationSetting commSetting = new AidlSetting();

      commSetting.setType(type);

      if (ip != null) {
        commSetting.setDestIP(ip);
        commSetting.setDestPort(port);
      }

      commSetting.setTimeOut(timeout);

      if (proxy) {
        commSetting.setEnableProxy(proxy);
      }

      poslink = new PosLink(this.context);

      poslink.SetCommSetting(commSetting);

      promise.resolve(true);

    } catch (Exception e) {
      promise.reject("Exception Error", e);
    }

  }

  @ReactMethod
  private void runPayment(String amount, String tip, Promise promise) {

    PaymentRequest req = new PaymentRequest();

    req.TenderType = req.ParseTenderType("CREDIT");
    req.TransType = req.ParseTransType("SALE");
    req.ECRRefNum = "1";
    req.Amount = amount;
    req.TipAmt = tip;

    this.runTransaction(req, promise);

  }

  @ReactMethod
  private void runAuth(String amount, Promise promise) {

    PaymentRequest req = new PaymentRequest();

    req.TenderType = req.ParseTenderType("CREDIT");
    req.TransType = req.ParseTransType("AUTH");
    req.ECRRefNum = "1";
    req.Amount = amount;

    this.runTransaction(req, promise);

  }

  @ReactMethod
  private void runReturn(String amount, Promise promise) {

    PaymentRequest req = new PaymentRequest();

    req.TenderType = req.ParseTenderType("CREDIT");
    req.TransType = req.ParseTransType("RETURN");
    req.ECRRefNum = "5";
    req.Amount = amount;

    this.runTransaction(req, promise);

  }

  @ReactMethod
  private void closeBatch(Promise promise) {

    handlerThread = new HandlerThread("MyHandlerThread");
    handlerThread.start();
    Looper looper = handlerThread.getLooper();
    Handler handler = new Handler(looper);

    handler.post(new Runnable() {

      @Override
      public void run() {
        BatchRequest batchRequest = new BatchRequest();
        batchRequest.TransType = batchRequest.ParseTransType("BATCHCLOSE");
        poslink.BatchRequest = batchRequest;

        ProcessTransResult ptr = poslink.ProcessTrans();

        if (ptr.Code == ProcessTransResult.ProcessTransResultCode.OK) {
          BatchResponse response = poslink.BatchResponse;

          WritableMap map = Arguments.createMap();
          map.putString("AuthCode", response.AuthCode);
          map.putString("BatchFailedCount", response.BatchFailedCount);
          map.putString("BatchFailedRefNum", response.BatchFailedRefNum);
          map.putString("BatchNum", response.BatchNum);
          map.putString("CashAmount", response.CashAmount);
          map.putString("CashCount", response.CashCount);
          map.putString("CHECKAmount", response.CHECKAmount);
          map.putString("CreditAmount", response.CreditAmount);
          map.putString("CreditCount", response.CreditCount);
          map.putString("DebitAmount", response.DebitAmount);
          map.putString("DebitCount", response.DebitCount);
          map.putString("EBTAmount", response.EBTAmount);
          map.putString("EBTCount", response.EBTCount);
          map.putString("ExtData", response.ExtData);
          map.putString("GiftAmount", response.GiftAmount);
          map.putString("GiftCount", response.GiftCount);
          map.putString("HostCode", response.HostCode);
          map.putString("HostResponse", response.HostResponse);
          map.putString("HostTraceNum", response.HostTraceNum);
          map.putString("LoyaltyAmount", response.LoyaltyAmount);
          map.putString("LoyaltyCount", response.LoyaltyCount);
          map.putString("Message", response.Message);
          map.putString("ResultCode", response.ResultCode);
          map.putString("ResultTxt", response.ResultTxt);
          map.putString("SAFDeletedCount", response.SAFDeletedCount);
          map.putString("SAFFailedCount", response.SAFFailedCount);
          map.putString("SAFFailedTotal", response.SAFFailedTotal);
          map.putString("SAFTotalAmount", response.SAFTotalAmount);
          map.putString("SAFTotalCount", response.SAFTotalCount);
          map.putString("SAFUploadedAmount", response.SAFUploadedAmount);
          map.putString("SAFUploadedCount", response.SAFUploadedCount);
          map.putString("TID", response.TID);
          map.putString("Timestamp", response.Timestamp);

          promise.resolve(map);

        } else if (ptr.Code == ProcessTransResult.ProcessTransResultCode.TimeOut) {
          String errorMsg = ptr.Msg;
          String errorCode = String.valueOf(ptr.Code);
          promise.reject(ptr.Msg);

        } else {
          String errorMsg = ptr.Msg;
          String errorCode = String.valueOf(ptr.Code);
          promise.reject(ptr.Msg);
        }
      }
    });
  }

  @ReactMethod
  private void runPostAuth(String amount, String refNum, String authCode, Promise promise) {
    PaymentRequest req = new PaymentRequest();

    req.TenderType = req.ParseTenderType("CREDIT");
    req.TransType = req.ParseTransType("POSTAUTH");
    req.ECRRefNum = "1";
    req.OrigRefNum = refNum;
    req.AuthCode = authCode;
    req.Amount = amount;

    this.runTransaction(req, promise);
  }

  @ReactMethod
  private void runVoid(String refNum, String authCode, Promise promise) {
    PaymentRequest req = new PaymentRequest();

    req.TenderType = req.ParseTenderType("CREDIT");
    req.TransType = req.ParseTransType("VOID");
    req.ECRRefNum = "1";
    req.OrigRefNum = refNum;
    req.AuthCode = authCode;

    this.runTransaction(req, promise);
  }

  @ReactMethod
  private void runAdjustment(String amount, String refNum, Promise promise) {
    PaymentRequest req = new PaymentRequest();

    req.TenderType = req.ParseTenderType("CREDIT");
    req.TransType = req.ParseTransType("ADJUST");
    req.OrigTraceNum = refNum;
    req.ECRRefNum = "1";
    req.OrigRefNum = refNum;
    req.Amount = amount;

    this.runTransaction(req, promise);
  }

  @ReactMethod
  private void runTransaction(PaymentRequest request, Promise promise) {

    handlerThread = new HandlerThread("MyHandlerThread");
    handlerThread.start();
    Looper looper = handlerThread.getLooper();
    Handler handler = new Handler(looper);

    handler.post(new Runnable() {

      @Override
      public void run() {
        Log.d("DEBUG_POSLINK_INFO", String.valueOf(poslink.GetCommSetting().getDestIP()));

        poslink.PaymentRequest = request;

        ProcessTransResult ptr = poslink.ProcessTrans();

        Log.d("DEBUG_PAYMENT_REQUEST", String.valueOf(ptr.Msg));

        // Check transaction result
        if (ptr.Code == ProcessTransResult.ProcessTransResultCode.OK) {
          /**
           * When the transResult.Code is OK, then the response has already been
           * assigned to poslink instance's PaymentResponse field automatically,
           * what you only need to do is get the response from the field
           */
          PaymentResponse response = poslink.PaymentResponse;

          Log.d("PAYMENT_RESPONSE_DEBUG AuthCode", response.AuthCode);
          WritableMap map = Arguments.createMap();
          map.putString("ApprovedAmount", response.ApprovedAmount);
          map.putString("ApprovedCashBackAmount", response.ApprovedCashBackAmount);
          map.putString("ApprovedMerchantFee", response.ApprovedMerchantFee);
          map.putString("ApprovedTaxAmount", response.ApprovedTaxAmount);
          map.putString("ApprovedTipAmount", response.ApprovedTipAmount);
          map.putString("AuthCode", response.AuthCode);
          map.putString("AuthorizationResponse", response.AuthorizationResponse);
          map.putString("AvsResponse", response.AvsResponse);
          map.putString("BogusAccountNum", response.BogusAccountNum);
          map.putString("CardType", response.CardType);
          map.putString("CvResponse", response.CvResponse);
          map.putString("DebitAccountType", response.DebitAccountType);
          map.putString("ECRTransID", response.ECRTransID);
          map.putString("EDCType", response.EDCType);
          map.putString("ExtraBalance", response.ExtraBalance);
          map.putString("GatewayTransactionID", response.GatewayTransactionID);
          map.putString("GiftCardType", response.GiftCardType);
          map.putString("HostAccount", response.HostAccount);
          map.putString("HostCardType", response.HostCardType);
          map.putString("HostCode", response.HostCode);
          map.putString("HostDetailedMessage", response.HostDetailedMessage);
          map.putString("HostResponse", response.HostResponse);
          map.putString("HostTimeStamp", response.HostTimeStamp);
          map.putString("IssuerResponseCode", response.IssuerResponseCode);
          map.putString("MaskedPAN", response.MaskedPAN);
          map.putString("Message", response.Message);
          map.putString("PayloadData", response.PayloadData);
          map.putString("PaymentAccountReferenceID", response.PaymentAccountReferenceID);
          map.putString("RawResponse", response.RawResponse);
          map.putString("RefNum", response.RefNum);
          map.putString("RemainingBalance", response.RemainingBalance);
          map.putString("RequestedAmount", response.RequestedAmount);
          map.putString("ResultCode", response.ResultCode);
          map.putString("ResultTxt", response.ResultTxt);
          map.putString("RetrievalReferenceNumber", response.RetrievalReferenceNumber);
          map.putString("SigFileName", response.SigFileName);
          map.putString("SignData", response.SignData);
          map.putString("Timestamp", response.Timestamp);
          map.putString("Track1Data", response.Track1Data);
          map.putString("Track2Data", response.Track2Data);
          map.putString("Track3Data", response.Track3Data);
          map.putString("TransactionIntegrityClass", response.TransactionIntegrityClass);
          map.putString("TransactionRemainingAmount", response.TransactionRemainingAmount);
          map.putString("ExtData", response.ExtData);

          promise.resolve(map);

        } else if (ptr.Code == ProcessTransResult.ProcessTransResultCode.TimeOut) {
          String errorMsg = ptr.Msg;
          String errorCode = String.valueOf(ptr.Code);
          Log.d("PAYMENT_RESPONSE_DEBUG", errorMsg);
          Log.d("PAYMENT_RESPONSE_DEBUG", errorCode);
          handler.getLooper().quit();
          promise.reject(ptr.Msg);

        } else {
          String errorMsg = ptr.Msg;
          String errorCode = String.valueOf(ptr.Code);
          Log.d("PAYMENT_RESPONSE_DEBUG", errorCode);
          Log.d("PAYMENT_RESPONSE_DEBUG", errorMsg);
          promise.reject(ptr.Msg);

          handler.getLooper().quit();

        }
      }
    });

  }

}
