package com.paxposlink2

import android.content.Context
import android.os.HandlerThread
import android.util.Log
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.WritableMap
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.module.annotations.ReactModule
import com.pax.poscore.LogSetting
import com.pax.poscore.commsetting.AidlSetting
import com.pax.poscore.commsetting.BluetoothSetting
import com.pax.poscore.commsetting.CommunicationSetting
import com.pax.poscore.commsetting.TcpSetting
import com.pax.poscore.commsetting.UartSetting
import com.pax.poscore.commsetting.UsbSetting
import com.pax.poscore.internal.ExecutionCode
import com.pax.poslinkadmin.ExecutionResult
import com.pax.poslinkadmin.Response
import com.pax.poslinkadmin.constant.TransactionType
import com.pax.poslinkadmin.internal.annotation.ExtDataName
import com.pax.poslinkadmin.util.AmountRequest
import com.pax.poslinkadmin.util.MultiMerchant
import com.pax.poslinkadmin.util.VasResponse
import com.pax.poslinksemiintegration.POSLinkSemi
import com.pax.poslinksemiintegration.transaction.DoCashRequest
import com.pax.poslinksemiintegration.transaction.DoCashResponse
import com.pax.poslinksemiintegration.transaction.DoCreditRequest
import com.pax.poslinksemiintegration.transaction.DoCreditResponse
import com.pax.poslinksemiintegration.util.AccountResponse
import com.pax.poslinksemiintegration.util.AmountResponse
import com.pax.poslinksemiintegration.util.AvsResponse
import com.pax.poslinksemiintegration.util.CardInformation
import com.pax.poslinksemiintegration.util.CofResponse
import com.pax.poslinksemiintegration.util.CommercialResponse
import com.pax.poslinksemiintegration.util.FleetCardResponse
import com.pax.poslinksemiintegration.util.FpsResponse
import com.pax.poslinksemiintegration.util.HostCredentialResponse
import com.pax.poslinksemiintegration.util.HostInformationResponse
import com.pax.poslinksemiintegration.util.HostTraceResponse
import com.pax.poslinksemiintegration.util.MotoECommerceResponse
import com.pax.poslinksemiintegration.util.PaymentEmvTag
import com.pax.poslinksemiintegration.util.PaymentTransactionInformation
import com.pax.poslinksemiintegration.util.Restaurant
import com.pax.poslinksemiintegration.util.SignatureResponse
import com.pax.poslinksemiintegration.util.TaxDetail
import com.pax.poslinksemiintegration.util.TorResponse
import com.pax.poslinksemiintegration.util.TraceRequest
import com.pax.poslinksemiintegration.util.TraceResponse
import com.pax.poslinksemiintegration.util.TransactionBehaviorResponse

import com.pax.poslinkadmin.manage.InitResponse;
import com.pax.poslinksemiintegration.batch.BatchCloseRequest
import com.pax.poslinksemiintegration.batch.BatchCloseResponse

@ReactModule(name = PaxPoslink2PaymentModule.NAME)
class PaxPoslink2PaymentModule(reactContext: ReactApplicationContext) :
        NativePaxPoslink2PaymentSpec(reactContext) {
  private var poslink: POSLinkSemi? = null
  private val handlerThread: HandlerThread? = null
  private var communicationSetting: CommunicationSetting? = null
  private val context: Context = reactContext

  override fun getName(): String {
    return NAME
  }

  override fun initPOSLink(
          type: String,
          timeout: Double,
          nameOrMac: String?,
          ipOrSerial: String?,
          portOrBaud: String?,
          promise: Promise
  ) {
    try {
      val logSetting = LogSetting()
      logSetting.isEnable = true
      logSetting.level = LogSetting.LogLevel.DEBUG // Set log level
      logSetting.fileName = "POSLinkLog" // Set the file name of output log
      logSetting.filePath = "sdcard/log" // Set path for output log
      logSetting.setDays(30) // Keep log for 30 days

      poslink = POSLinkSemi.getInstance()
      poslink!!.setLogSetting(logSetting)
      initPaymentCommunication(type, timeout, nameOrMac, ipOrSerial, portOrBaud, promise)
    } catch (e: Exception) {
      promise.reject("Exception Error", e)
    }
  }

  override fun initPaymentCommunication(
          type: String,
          timeout: Double,
          nameOrMac: String?,
          ipOrSerial: String?,
          portOrBaud: String?,
          promise: Promise
  ) {
    // Setting connection configurations
    when (type) {
      "TCP" -> {
        val tcpSetting = TcpSetting(ipOrSerial, portOrBaud, timeout.toInt())
        this.communicationSetting = tcpSetting
      }
      "USB" -> {
        val usbSetting = UsbSetting()
        usbSetting.deviceName = nameOrMac
        this.communicationSetting = usbSetting
      }
      "BLUETOOTH" -> {
        val bluetoothSetting = BluetoothSetting()
        bluetoothSetting.macAddr = nameOrMac
        this.communicationSetting = bluetoothSetting
      }
      "AIDL" -> {
        val aidlSetting = AidlSetting()
        this.communicationSetting = aidlSetting
      }
      "UART" -> {
        val uartSetting = UartSetting(ipOrSerial, portOrBaud, timeout.toInt())
        this.communicationSetting = uartSetting
      }
      else -> {
        promise.reject("Invalid Type", type)
        return
      }
    }
    promise.resolve("success")

    //      if (proxy) {
    //        commSetting.setEnableProxy(proxy);
    //      }
  }

  override fun verifyPOSTerminal(promise: Promise) {
    val terminal = poslink!!.getTerminal(this.context, this.communicationSetting)
    if (terminal == null) {
      promise.reject("Exception", "Terminal not found")
    } else {
      promise.resolve("success")
      // ExecutionResult<InitResponse> result = terminal.getManage().init();
      // if (result.isSuccessful()) {
      //     StringBuilder messageBuilder = StringBuilder("Init Success!\n");
      //     InitResponse response = result.response();
      //     messageBuilder.append("AppName: ").append(response.appName()).append("\n")
      //             .append("AppVersion: ").append(response.appVersion()).append("\n")
      //             .append("SN: ").append(response.sn()).append("\n")
      //             .append("ModelName: ").append(response.modelName()).append("\n")
      //             .append("OSVersion: ").append(response.osVersion());
      //     promise.resolve(messageBuilder.toString());
      // } else {
      //     promise.reject("Trans Failed!", "Error Message:" + result.message());
      // }

    }
  }

  fun processCreditRequest(
    doCreditRequest: DoCreditRequest,
    testMode: Boolean?,
    promise: Promise
  ) {
    if(testMode != null && testMode) {
      val amountRequest = doCreditRequest.amountInformation
      val traceRequest = doCreditRequest.traceInformation
      if(amountRequest.transactionAmount == "-1") {
        generateMockCreditErrorResponse(amountRequest.transactionAmount, amountRequest.tipAmount, traceRequest.ecrReferenceNumber, promise)
      } else {
        generateMockCreditSuccessResponse(amountRequest.transactionAmount, amountRequest.tipAmount, traceRequest.ecrReferenceNumber, promise)
      }
      return
    }

    val terminal = poslink!!.getTerminal(this.context, this.communicationSetting)
    if (terminal == null) {
      promise.reject("Exception", "Terminal not found")

      return
    }
    var executionResult: ExecutionResult<DoCreditResponse> =
            terminal.transaction.doCredit(doCreditRequest)

    handleCreditExecutionResult(executionResult, promise)
  }

  fun processCashRequest(
    doCashRequest: DoCashRequest,
    testMode: Boolean?,
    promise: Promise
  ) {
    if(testMode != null && testMode) {
      val amountRequest = doCashRequest.amountInformation
      val traceRequest = doCashRequest.traceInformation
      if(amountRequest.transactionAmount == "-1") {
        generateMockCashErrorResponse(amountRequest.transactionAmount, amountRequest.tipAmount, traceRequest.ecrReferenceNumber, promise)

      } else {
        generateMockCashSuccessResponse(amountRequest.transactionAmount, amountRequest.tipAmount, traceRequest.ecrReferenceNumber, promise)
      }
      return
    }

    val terminal = poslink!!.getTerminal(this.context, this.communicationSetting)
    if (terminal == null) {
      promise.reject("Exception", "Terminal not found")
      return
    }
    val executionResult: ExecutionResult<DoCashResponse> =
            terminal.transaction.doCash(doCashRequest)

    handleCashExecutionResult(executionResult, promise)
  }

  override fun makeCreditPaymentWithAmountRequestMap(
    amountRequestMap: ReadableMap,
    referenceNumber: String?,
    testMode: Boolean?,
    promise: Promise
  ) {
    val amountRequest = this.getAmountRequestFromMap(amountRequestMap)
    makeCreditPaymentWithAmountRequest(amountRequest, referenceNumber, testMode, promise)
  }

  override fun makeCreditPayment(
    amount: String?,
    tip: String?,
    referenceNumber: String?,
    testMode: Boolean?,
    promise: Promise
  ) {

    val amountRequest = AmountRequest()
    amountRequest.transactionAmount = amount
    amountRequest.tipAmount = tip
    makeCreditPaymentWithAmountRequest(amountRequest, referenceNumber, testMode, promise)
  }

  override fun makeCashPaymentWithAmountRequestMap(
    amountRequestMap: ReadableMap,
    referenceNumber: String?,
    testMode: Boolean?,
    promise: Promise
  ) {
    val amountRequest = getAmountRequestFromMap(amountRequestMap)
    makeCashPaymentWithAmountRequest(amountRequest, referenceNumber, testMode, promise)
  }

  override fun makeCashPayment(
    amount: String?,
    tip: String?,
    referenceNumber: String?,
    testMode: Boolean?,
    promise: Promise
  ) {
    val amountRequest = AmountRequest()
    amountRequest.transactionAmount = amount
    amountRequest.tipAmount = tip
    makeCashPaymentWithAmountRequest(amountRequest, referenceNumber, testMode, promise)
  }

  override fun voidCreditPaymentWithAmountRequestMap(
    amountRequestMap: ReadableMap,
    referenceNumber: String?,
    testMode: Boolean?,
    promise: Promise
  ) {
    val amountRequest = getAmountRequestFromMap(amountRequestMap)

    val traceRequest = TraceRequest()
    traceRequest.ecrReferenceNumber = referenceNumber //if (transType in listOf(TransactionType.SALE, TransactionType.VOID_SALE, TransactionType.RETURN)) it else null
    traceRequest.originalEcrReferenceNumber = referenceNumber //if (transType == TransactionType.VOID_SALE) it else null

    val doCreditRequest = DoCreditRequest()
    doCreditRequest.transactionType = TransactionType.VOID_SALE
    doCreditRequest.amountInformation = amountRequest
    doCreditRequest.traceInformation = traceRequest
    processCreditRequest(doCreditRequest, testMode, promise)
  }

  override fun voidCreditPayment(
    amount: String?,
    tip: String?,
    referenceNumber: String?,
    testMode: Boolean?,
    promise: Promise
  ) {
    val amountRequest = AmountRequest()
    amountRequest.transactionAmount = amount
    amountRequest.tipAmount = tip

    val traceRequest = TraceRequest()
    traceRequest.ecrReferenceNumber = referenceNumber //if (transType in listOf(TransactionType.SALE, TransactionType.VOID_SALE, TransactionType.RETURN)) it else null
    traceRequest.originalEcrReferenceNumber = referenceNumber //if (transType == TransactionType.VOID_SALE) it else null

    val doCreditRequest = DoCreditRequest()
    doCreditRequest.transactionType = TransactionType.VOID_SALE
    doCreditRequest.amountInformation = amountRequest
    doCreditRequest.traceInformation = traceRequest

    processCreditRequest(doCreditRequest, testMode, promise)
  }

  override fun returnCreditPaymentWithAmountRequestMap(
    amountRequestMap: ReadableMap,
    referenceNumber: String?,
    testMode: Boolean?,
    promise: Promise
  ) {
    val amountRequest = getAmountRequestFromMap(amountRequestMap)

    val traceRequest = TraceRequest()
    traceRequest.ecrReferenceNumber = referenceNumber //if (transType in listOf(TransactionType.SALE, TransactionType.VOID_SALE, TransactionType.RETURN)) it else null
    // traceRequest.originalEcrReferenceNumber = referenceNumber //if (transType == TransactionType.VOID_SALE) it else null

    val doCreditRequest = DoCreditRequest()
    doCreditRequest.transactionType = TransactionType.RETURN
    doCreditRequest.amountInformation = amountRequest
    doCreditRequest.traceInformation = traceRequest

    processCreditRequest(doCreditRequest, testMode, promise)
  }

  override fun returnCreditPayment(
    amount: String?,
    tip: String?,
    referenceNumber: String?,
    testMode: Boolean?,
    promise: Promise
  ) {
    val amountRequest = AmountRequest()
    amountRequest.transactionAmount = amount
    amountRequest.tipAmount = tip

    val traceRequest = TraceRequest()
    traceRequest.ecrReferenceNumber = referenceNumber //if (transType in listOf(TransactionType.SALE, TransactionType.VOID_SALE, TransactionType.RETURN)) it else null
    // traceRequest.originalEcrReferenceNumber = referenceNumber //if (transType == TransactionType.VOID_SALE) it else null

    val doCreditRequest = DoCreditRequest()
    doCreditRequest.transactionType = TransactionType.RETURN
    doCreditRequest.amountInformation = amountRequest
    doCreditRequest.traceInformation = traceRequest

    processCreditRequest(doCreditRequest, testMode, promise)
  }


  override fun closeBatch(promise: Promise) {
    val terminal = poslink!!.getTerminal(this.context, this.communicationSetting)
    if (terminal == null) {
      promise.reject("Exception", "Terminal not found")
      // if(amount == "-1") {
      //     generateMockCashErrorResponse(amount, tip, referenceNumber, promise)

      // } else {
      //     generateMockCashSuccessResponse(amount, tip, referenceNumber, promise)
      // }
      return
    }
    val batchCloseReq = BatchCloseRequest()
    // val batchCloseRsp = BatchCloseResponse()
    val result = terminal.batch?.batchClose(batchCloseReq)
    if (result?.code() == ExecutionCode.OK) {
        promise.resolve("Batch close successfully")
        return
    }
    promise.reject("Failed", "Batch close error")
  }

  private fun makeCreditPaymentWithAmountRequest(
    amountRequest: AmountRequest,
    referenceNumber: String?,
    testMode: Boolean?,
    promise: Promise
  ) {

    val traceRequest = TraceRequest()
    traceRequest.ecrReferenceNumber = referenceNumber

    val doCreditRequest = DoCreditRequest()
    doCreditRequest.transactionType = TransactionType.SALE
    doCreditRequest.amountInformation = amountRequest
    doCreditRequest.traceInformation = traceRequest

    processCreditRequest(doCreditRequest, testMode, promise)
  }

  private fun makeCashPaymentWithAmountRequest(
    amountRequest: AmountRequest,
    referenceNumber: String?,
    testMode: Boolean?,
    promise: Promise
  ) {
    val traceRequest = TraceRequest()
    traceRequest.ecrReferenceNumber = referenceNumber

    val doCashRequest = DoCashRequest()
    doCashRequest.transactionType = TransactionType.SALE
    doCashRequest.amountInformation = amountRequest
    doCashRequest.traceInformation = traceRequest

    processCashRequest(doCashRequest, testMode, promise)
  }

  private fun getAmountRequestFromMap(amountRequestMap: ReadableMap): AmountRequest {
    val amountRequest = AmountRequest()


    if(amountRequestMap.hasKey("transactionAmount")) {
      amountRequest.transactionAmount = amountRequestMap.getString("transactionAmount")
    }
    if(amountRequestMap.hasKey("tipAmount")) {
      amountRequest.tipAmount = amountRequestMap.getString("tipAmount")
    }
    if(amountRequestMap.hasKey("cashBackAmount")) {
      amountRequest.cashBackAmount = amountRequestMap.getString("cashBackAmount")
    }
    if(amountRequestMap.hasKey("merchantFee")) {
      amountRequest.merchantFee = amountRequestMap.getString("merchantFee")
    }
    if(amountRequestMap.hasKey("taxAmount")) {
      amountRequest.taxAmount = amountRequestMap.getString("taxAmount")
    }
    if(amountRequestMap.hasKey("serviceFee")) {
      amountRequest.serviceFee = amountRequestMap.getString("serviceFee")
    }
    if(amountRequestMap.hasKey("fuelAmount")) {
      amountRequest.fuelAmount = amountRequestMap.getString("fuelAmount")
    }
    if(amountRequestMap.hasKey("originalAmount")) {
      amountRequest.originalAmount = amountRequestMap.getString("originalAmount")
    }
    return amountRequest;
  }

  private fun generateMockCreditSuccessResponse(
          amount: String?,
          tip: String?,
          referenceNumber: String?,
          promise: Promise
  ) {
    val hostInformation = HostInformationResponse()
    val transactionType = TransactionType.SALE;

    val approvedCashbackAmount = "200"; // 2 dollars
    val approvedMerchantFee = "500"; // 5 dollars

    val amountInformation = AmountResponse(amount, "", tip, "0", "0", "0", "0", "0", "0", "0", tip, approvedCashbackAmount, approvedMerchantFee, "0", "0", "0", tip)
    val accountInformation = AccountResponse()
    val traceInformation = TraceResponse()
    val avsInformation = AvsResponse()
    val commercialInformation = CommercialResponse()
    val motoECommerceInformation = MotoECommerceResponse()
    val restaurant = Restaurant()
    val paymentTransactionInformation = PaymentTransactionInformation()
    val cardInformation = CardInformation()
    val multiMerchant = MultiMerchant()
    val paymentEmvTag = PaymentEmvTag()
    val fleetCard = FleetCardResponse()
    val signatureInformation = SignatureResponse()
    val vasInformation = VasResponse()
    val torInformation = TorResponse()
    val payloadData = ""
    val hostCredentialInformation = HostCredentialResponse()
    val taxDetails =  ArrayList<TaxDetail?>()
    val cofInformation = CofResponse()
    val hostTraceInformation = HostTraceResponse(referenceNumber, "", "", "")

    val edcType = "test"
    val transactionBehavior = TransactionBehaviorResponse()
    val fpsInformation = FpsResponse()

    val doCreditResponse = DoCreditResponse(hostInformation, transactionType, amountInformation,
      accountInformation, traceInformation, avsInformation,  commercialInformation,
      motoECommerceInformation, restaurant, paymentTransactionInformation, cardInformation,
      multiMerchant, paymentEmvTag, fleetCard, signatureInformation, vasInformation, torInformation,
      payloadData, hostCredentialInformation, taxDetails, cofInformation, hostTraceInformation,
      edcType, transactionBehavior, fpsInformation
    )
    val map = convertDoCreditResponseToMap(doCreditResponse)


    //      map.putString("ExtData", doCreditResponse.ExtData);
    promise.resolve(map)
  }

  private fun handleCreditExecutionResult(
          executionResult: ExecutionResult<DoCreditResponse>,
          promise: Promise
  ) {
    val doCreditResponse = executionResult.response()
    if (executionResult.isSuccessful && executionResult?.code() == ExecutionCode.OK) {
      val map = convertDoCreditResponseToMap(doCreditResponse)
      promise.resolve(map)
    } else {
      val errorMsg = executionResult.message()
      val errorCode = executionResult.code().toString()
      Log.d("PAYMENT_RESPONSE_DEBUG", errorMsg)
      Log.d("PAYMENT_RESPONSE_DEBUG", errorCode)

      promise.reject(errorCode, errorMsg)
    }
  }

  private fun generateMockCashErrorResponse(
          amount: String?,
          tip: String?,
          referenceNumber: String?,
          promise: Promise
  ) {
    promise.reject("TESTCASHERROR", "TEST Cash Error")
  }

  private fun generateMockCreditErrorResponse(
          amount: String?,
          tip: String?,
          referenceNumber: String?,
          promise: Promise
  ) {
    promise.reject("TESTCReditERROR", "TEST Credit Error")
  }

  private fun generateMockCashSuccessResponse(
          amount: String?,
          tip: String?,
          referenceNumber: String?,
          promise: Promise
  ) {
    val hostInformation = HostInformationResponse()
    val transactionType = TransactionType.SALE;
    val amountInformation = AmountResponse(amount, "", tip, "0", "0", "0", "0", "0", "0", "0", tip, "0", "0", "0", "0", "0", tip)
    val traceInformation = TraceResponse()
    val restaurant = Restaurant()
    val paymentTransactionInformation = PaymentTransactionInformation()
    val multiMerchant = MultiMerchant()
    val signatureInformation = SignatureResponse()
    val torInformation = TorResponse()
    val hostCredentialInformation = HostCredentialResponse()
    val taxDetails = ArrayList<TaxDetail?>()
    val hostTraceInformation = HostTraceResponse(referenceNumber, "", "", "")
    val transactionBehavior = TransactionBehaviorResponse()

    val doCashResponse = DoCashResponse(hostInformation, transactionType, amountInformation,
      traceInformation, restaurant, paymentTransactionInformation, multiMerchant,
      signatureInformation, torInformation, hostCredentialInformation, taxDetails,
      hostTraceInformation, transactionBehavior
    )
    val map =convertDoCashResponseToMap(doCashResponse)

    promise.resolve(map)
  }

  private fun convertAmountInformationToMap(amountInformation: AmountResponse?): WritableMap {
    val map = Arguments.createMap()
    if (amountInformation == null) return map
    // transactionAmount
    map.putString("transactionRemainingAmount", amountInformation.transactionRemainingAmount())
    map.putString("tipAmount", amountInformation.tipAmount())
    map.putString("merchantFee", amountInformation.merchantFee())

    map.putString("approvedAmount", amountInformation.approvedAmount())
    map.putString("approvedTipAmount", amountInformation.approvedTipAmount())
    map.putString("approvedCashBackAmount", amountInformation.approvedCashBackAmount())
    map.putString("approvedMerchantFee", amountInformation.approvedMerchantFee())
    map.putString("approvedTaxAmount", amountInformation.approvedTaxAmount())
    map.putString("approvedTipAmount", amountInformation.approvedTipAmount())

    return map
  }

  private fun convertAvsInformationToMap(avsInformation: AvsResponse?): WritableMap {
    val map = Arguments.createMap()
    if (avsInformation == null) return map

    map.putString("avsMessage", avsInformation.avsMessage())
    map.putString("zipCode", avsInformation.zipCode())
    map.putString("address", avsInformation.address1())

    return map
  }
  private fun convertTraceInformationToMap(traceInformation: TraceResponse?): WritableMap {
    val map = Arguments.createMap()
    if (traceInformation == null) return map

    map.putString("referenceNumber", traceInformation.referenceNumber())
    map.putString("timeStamp", traceInformation.timeStamp())
    // map.putString("authorizationResponse", traceInformation?.authorizationResponse())

    return map
  }

  private fun convertTorInformationToMap(torInformation: TorResponse?): WritableMap {
    val map = Arguments.createMap()
    if (torInformation == null) return map

    map.putString("hostResponseMessage", torInformation.hostResponseMessage())
    map.putString("originalAmount", torInformation.originalAmount())
    map.putString("maskedPan", torInformation.maskedPan())
    map.putString("originalAmount", torInformation.originalAmount())

    return map
  }

  private fun convertAccountInformationToMap(accountInformation: AccountResponse?): WritableMap {
    val map = Arguments.createMap()
    if (accountInformation == null) return map

    map.putString("account", accountInformation.account())
    map.putString("currentAccountNumber", accountInformation.currentAccountNumber())
    map.putString("cardHolder", accountInformation.cardHolder())
    map.putString("cardExpireDate", accountInformation.expireDate())
    map.putString("cvdApprovalCode", accountInformation.cvdApprovalCode())
    map.putMap("debitAccountType", Arguments.createMap().apply {
      putString("getName", accountInformation.debitAccountType()?.getName())
    })
    map.putMap("giftCardType", Arguments.createMap().apply {
      putString("getName", accountInformation.giftCardType()?.getName())
    })
    map.putString("hostCardType", accountInformation.hostCardType())
    map.putMap("cardType", Arguments.createMap().apply {
      putString("getName", accountInformation.cardType()?.getName())
    })
    map.putString("track1Data", accountInformation.track1Data())
    map.putString("track2Data", accountInformation.track2Data())
    map.putString("track3Data", accountInformation.track3Data())

    return map
  }

  private fun convertHostTraceInformationToMap(hostTraceInformation: HostTraceResponse?): WritableMap {
    val map = Arguments.createMap()
    if (hostTraceInformation == null) return map

    map.putString("ecrTransactionId", hostTraceInformation.ecrTransactionId())
    map.putString("hostReferenceNumber", hostTraceInformation.hostReferenceNumber())
    map.putString("hostTimeStamp", hostTraceInformation.hostTimeStamp())

    return map
  }

  private fun convertHostInformationToMap(hostInformation: HostInformationResponse?): WritableMap {
    val map = Arguments.createMap()
    if (hostInformation == null) return map

    map.putString("gatewayTransactionId", hostInformation.gatewayTransactionId())
    map.putString("hostResponseCode", hostInformation.hostResponseCode())
    map.putString("hostDetailedMessage", hostInformation.hostDetailedMessage())
    map.putString("paymentAccountReferenceId", hostInformation.paymentAccountReferenceId())
    map.putString("issuerResponseCode", hostInformation.issuerResponseCode())
    map.putString("retrievalReferenceNumber", hostInformation.retrievalReferenceNumber())
    map.putString("transactionIntegrityClass", hostInformation.transactionIntegrityClass())

    return map
  }

  private fun convertRestaurantToMap(restaurant: Restaurant?): WritableMap {
    val map = Arguments.createMap()
    if (restaurant == null) return map

    map.putString("tableNumber", restaurant.tableNumber)
    map.putString("ticketNumber", restaurant.ticketNumber)

    return map
  }

  private fun convertMultiMerchantToMap(multiMerchant: MultiMerchant?): WritableMap {
    val map = Arguments.createMap()
    if (multiMerchant == null) return map

    map.putString("multiMerchantId", multiMerchant.multiMerchantId)
    map.putString("multiMerchantName", multiMerchant.multiMerchantName)

    return map
  }

  private fun addResponseToMap(map: WritableMap, response: Response): WritableMap {
    map.putString("responseCode", response.responseCode())
    map.putString("responseMessage", response.responseMessage())

    return map
  }

  // reference:
  // https://github.com/phattran1201/react-native-pax/blob/main/android/src/main/java/com/paxposlink/PaxPosLinkModule.kt
  private fun convertDoCreditResponseToMap(doCreditResponse: DoCreditResponse?): WritableMap {
    val map = Arguments.createMap()
    if (doCreditResponse == null) return map

    addResponseToMap(map, doCreditResponse)

    map.putString("payloadData", doCreditResponse.payloadData())
    map.putString("edcType", doCreditResponse.edcType())
    //      map.putString("RawResponse", doCreditResponse.RawResponse);
    //      map.putString("RemainingBalance", doCreditResponse.RemainingBalance);
    //      map.putString("ResultCode", doCreditResponse.ResultCode);
    //      map.putString("ResultTxt", doCreditResponse.ResultTxt);
    //      map.putString("SigFileName", doCreditResponse.SigFileName);

    //      map.putString("ExtData", doCreditResponse.ExtData);

    // map.putString(
    //   "entryMethod",
    //   doCreditResponse.paymentEmvTag()?.let { emv ->
    //       doCreditResponse.accountInformation()?.let { getEntryMethod(it, emv) }
    //   },
    // )

    // accountInformation
    val accountInformation = doCreditResponse.accountInformation()
    map.putMap("accountInformation", convertAccountInformationToMap(accountInformation))

    // cardInformation
    // val card = doCreditResponse.cardInformation()
    // val cardMap =
    //     Arguments.createMap().apply {
    //         putString("cardType", card?.cardBin())
    //     }
    // map.putMap("cardInformation", cardMap)

    // traceInformation
    val traceInformation = doCreditResponse.traceInformation()
    map.putMap("traceInformation", convertTraceInformationToMap(traceInformation))

    // amountInformation
    val amountInformation = doCreditResponse.amountInformation()
    map.putMap("amountInformation", convertAmountInformationToMap(amountInformation))

    // transactionId
    // val trans = doCreditResponse.paymentTransactionInformation()
    // map.putString("transactionId", trans?.globalUid())

    // avsInformation
    val avsInformation = doCreditResponse.avsInformation()
    map.putMap("avsInformation", convertAvsInformationToMap(avsInformation))

    val hostTraceInformation = doCreditResponse.hostTraceInformation()
    map.putMap("hostTraceInformation", convertHostTraceInformationToMap(hostTraceInformation))

    val hostInformation = doCreditResponse.hostInformation()
    map.putMap("hostInformation", convertHostInformationToMap(hostInformation))

    // commercialInformation
    val commercial = doCreditResponse.commercialInformation()
    val commMap = Arguments.createMap().apply { putString("poNumber", commercial?.poNumber()) }
    map.putMap("commercialInformation", commMap)

    // fleetCard
    val fleet = doCreditResponse.fleetCard()
    val fleetMap = Arguments.createMap().apply { putString("vehicleNumber", fleet?.vehicleNumber) }
    map.putMap("fleetCard", fleetMap)

    // multiMerchant
    val multiMerchant = doCreditResponse.multiMerchant()
    map.putMap("multiMerchant", convertMultiMerchantToMap(multiMerchant))

    // restaurant
    val restaurant = doCreditResponse.restaurant()
    map.putMap("restaurant", convertRestaurantToMap(restaurant))

    val torInformation = doCreditResponse.torInformation()
    map.putMap("torInformation", convertTorInformationToMap(torInformation))

    // emv
    val paymentEmvTag = doCreditResponse.paymentEmvTag()
    val paymentEmvTagMap =
            Arguments.createMap().apply {
              putString("emvLabel", paymentEmvTag.appLabel())
              putString("emvPreferName", paymentEmvTag.appPreferName())
            }
    map.putMap("paymentEmvTag", paymentEmvTagMap)

    val signatureInformation = doCreditResponse.signatureInformation()
    val signatureInformationMap =
            Arguments.createMap().apply {
              putString("signatureData", signatureInformation?.signatureData())
            }
    map.putMap("signatureInformation", signatureInformationMap)

    return map
  }

  private fun convertDoCashResponseToMap(doCashResponse: DoCashResponse?): WritableMap {
    val map = Arguments.createMap()
    if (doCashResponse == null) return map
    Log.d("PAYMENT_RESPONSE_DEBUG AuthCode", doCashResponse.responseCode())

    addResponseToMap(map, doCashResponse)

    val amountInformation = doCashResponse.amountInformation()
    map.putMap("amountInformation", convertAmountInformationToMap(amountInformation))

    //      map.putString("AvsResponse", doCashResponse.avsInformation().avsMessage());
    //      map.putString("CurrentAccountNumber",
    // doCashResponse.accountInformation().currentAccountNumber());
    //      map.putString("CardType", doCashResponse.accountInformation().cardType());
    //      map.putString("CvdApprovalCode", doCashResponse.accountInformation().cvdApprovalCode());
    //      map.putString("DebitAccountType",
    // doCashResponse.accountInformation().debitAccountType());
    val hostTraceInformation = doCashResponse.hostTraceInformation()
    map.putMap("hostTraceInformation", convertHostTraceInformationToMap(hostTraceInformation))

    val hostInformation = doCashResponse.hostInformation()
    map.putMap("hostInformation", convertHostInformationToMap(hostInformation))
    //            map.putString("EDCType", doCashResponse.edcType())
    //      map.putString("ExtraBalance", doCreditResponse.amountInformation().ExtraBalance);
    //      map.putString("GiftCardType", doCashResponse.accountInformation().giftCardType());
    val traceInformation = doCashResponse.traceInformation()
    map.putMap("traceInformation", convertTraceInformationToMap(traceInformation))
    //      map.putString("HostCardType", doCashResponse.accountInformation().hostCardType());
    val torInformation = doCashResponse.torInformation()
    map.putMap("torInformation", convertTorInformationToMap(torInformation))
    //      map.putString("PayloadData", doCashResponse.payloadData());
    //      map.putString("RawResponse", doCashResponse.RawResponse);
    //      map.putString("RemainingBalance", doCashResponse.RemainingBalance);
    //      map.putString("ResultCode", doCashResponse.ResultCode);
    //      map.putString("ResultTxt", doCashResponse.ResultTxt);
    //      map.putString("SigFileName", doCashResponse.SigFileName);
    val signatureInformation = doCashResponse.signatureInformation()
    val signatureInformationMap =
            Arguments.createMap().apply {
              putString("signatureData", signatureInformation?.signatureData())
            }
    map.putMap("signatureInformation", signatureInformationMap)
    //      map.putString("Track1Data", doCashResponse.accountInformation().track1Data());
    //      map.putString("Track2Data", doCashResponse.accountInformation().track2Data());
    //      map.putString("Track3Data", doCashResponse.accountInformation().track3Data());

    return map
  }

  private fun handleCashExecutionResult(
          executionResult: ExecutionResult<DoCashResponse>,
          promise: Promise
  ) {
    val doCashResponse = executionResult.response()
    if (executionResult.isSuccessful && executionResult.code() == ExecutionCode.OK) {
      val map = convertDoCashResponseToMap(
                      doCashResponse
              ) //      map.putString("ExtData", doCashResponse.ExtData);
      promise.resolve(map)
    } else {
      val errorMsg = executionResult.message()
      val errorCode = executionResult.code().toString()
      Log.d("PAYMENT_RESPONSE_DEBUG", errorMsg)
      Log.d("PAYMENT_RESPONSE_DEBUG", errorCode)

      promise.reject(errorCode, errorMsg)
    }
  }

  companion object {
    const val NAME: String = "PaxPoslink2Payment"
  }
}
