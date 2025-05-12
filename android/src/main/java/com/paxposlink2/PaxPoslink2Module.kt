package com.paxposlink2

import android.content.Context
import android.os.HandlerThread
import android.util.Log
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.module.annotations.ReactModule
import com.pax.poscore.LogSetting
import com.pax.poscore.commsetting.AidlSetting
import com.pax.poscore.commsetting.BluetoothSetting
import com.pax.poscore.commsetting.CommunicationSetting
import com.pax.poscore.commsetting.TcpSetting
import com.pax.poscore.commsetting.UartSetting
import com.pax.poscore.commsetting.UsbSetting
import com.pax.poslinkadmin.ExecutionResult
import com.pax.poslinkadmin.constant.TransactionType
import com.pax.poslinkadmin.util.AmountRequest
import com.pax.poslinksemiintegration.POSLinkSemi
import com.pax.poslinksemiintegration.transaction.DoCashRequest
import com.pax.poslinksemiintegration.transaction.DoCashResponse
import com.pax.poslinksemiintegration.transaction.DoCreditRequest
import com.pax.poslinksemiintegration.transaction.DoCreditResponse
import com.pax.poslinksemiintegration.util.TraceRequest
import com.paxposlink2.PaxPoslink2Module

@ReactModule(name = PaxPoslink2Module.NAME)
class PaxPoslink2Module(reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {
    private var poslink: POSLinkSemi? = null
    private val handlerThread: HandlerThread? = null
    private var communicationSetting: CommunicationSetting? = null
    private val context: Context = reactContext

    override fun getName(): String {
        return NAME
    }


    @ReactMethod
    fun initPOSLink(
        type: String,
        timeout: Int,
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
            initPaymentCommunication(type!!, timeout, nameOrMac, ipOrSerial, portOrBaud, promise)
        } catch (e: Exception) {
            promise.reject("Exception Error", e)
        }
    }

    @ReactMethod
    fun initPaymentCommunication(
        type: String,
        timeout: Int,
        nameOrMac: String?,
        ipOrSerial: String?,
        portOrBaud: String?,
        promise: Promise
    ) {
        // Setting connection configurations
        when (type) {
            "AIDL" -> {}
            "TCP" -> {
                val tcpSetting = TcpSetting(ipOrSerial, portOrBaud, timeout)
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
                val uartSetting = UartSetting(ipOrSerial, portOrBaud, timeout)
                this.communicationSetting = uartSetting
            }

            else -> {
                promise.reject("Invalid Type", type)
                return
            }
        }

        //      if (proxy) {
//        commSetting.setEnableProxy(proxy);
//      }
    }

    @ReactMethod
    fun makeCreditPayment(
        amount: String?,
        tip: String?,
        referenceNumber: String?,
        promise: Promise
    ) {
        val terminal = poslink!!.getTerminal(this.context, this.communicationSetting)

        val amountRequest = AmountRequest()
        amountRequest.transactionAmount = amount
        amountRequest.tipAmount = tip

        val traceRequest = TraceRequest()
        traceRequest.ecrReferenceNumber = referenceNumber

        val doCreditRequest = DoCreditRequest()
        doCreditRequest.transactionType = TransactionType.SALE
        doCreditRequest.amountInformation = amountRequest
        doCreditRequest.traceInformation = traceRequest

        var executionResult: ExecutionResult<DoCreditResponse>? = null

        executionResult = terminal.transaction.doCredit(doCreditRequest)

        this.handleCreditExecutionResult(executionResult, promise)
    }

    @ReactMethod
    fun makeCashPayment(amount: String?, tip: String?, referenceNumber: String?, promise: Promise) {
        val terminal = poslink!!.getTerminal(this.context, this.communicationSetting)

        val amountRequest = AmountRequest()
        amountRequest.transactionAmount = amount
        amountRequest.tipAmount = tip

        val traceRequest = TraceRequest()
        traceRequest.ecrReferenceNumber = referenceNumber

        val doCashRequest = DoCashRequest()
        doCashRequest.transactionType = TransactionType.SALE
        doCashRequest.amountInformation = amountRequest
        doCashRequest.traceInformation = traceRequest

        val executionResult: ExecutionResult<DoCashResponse> =
            terminal.transaction.doCash(doCashRequest)

        this.handleCashExecutionResult(executionResult, promise)
    }

//    fun makeReturnPayment(
//        amount: String?,
//        tip: String?,
//        referenceNumber: String?,
//        promise: Promise
//    ) {
//        val terminal = poslink!!.getTerminal(this.context, this.communicationSetting)
//
//        val amountRequest = AmountRequest()
//        amountRequest.transactionAmount = amount
//        amountRequest.tipAmount = tip
//
//        val traceRequest = TraceRequest()
//        traceRequest.ecrReferenceNumber = referenceNumber
//
//        val doCashRequest = DoCashRequest()
//        doCashRequest.transactionType = TransactionType.SALE
//        doCashRequest.amountInformation = amountRequest
//        doCashRequest.traceInformation = traceRequest
//
//        val executionResult: ExecutionResult<DoCashResponse> =
//            terminal.transaction.doCash(doCashRequest)
//
//        this.handleCashExecutionResult(executionResult, promise)
//    }

    private fun handleCreditExecutionResult(
        executionResult: ExecutionResult<DoCreditResponse>,
        promise: Promise
    ) {
        if (executionResult.isSuccessful) {
            val doCreditResponse = executionResult.response()
            Log.d("PAYMENT_RESPONSE_DEBUG AuthCode", doCreditResponse.responseCode())
            val map = Arguments.createMap()
            map.putString("ApprovedAmount", doCreditResponse.amountInformation().approvedAmount())
            map.putString(
                "ApprovedCashBackAmount",
                doCreditResponse.amountInformation().approvedCashBackAmount()
            )
            map.putString(
                "ApprovedMerchantFee",
                doCreditResponse.amountInformation().approvedMerchantFee()
            )
            map.putString(
                "ApprovedTaxAmount",
                doCreditResponse.amountInformation().approvedTaxAmount()
            )
            map.putString(
                "ApprovedTipAmount",
                doCreditResponse.amountInformation().approvedTipAmount()
            )
            map.putString("AuthCode", doCreditResponse.responseCode())
            map.putString("AuthorizationResponse", doCreditResponse.responseMessage())
            map.putString("AvsResponse", doCreditResponse.avsInformation().avsMessage())
            map.putString(
                "CurrentAccountNumber",
                doCreditResponse.accountInformation().currentAccountNumber()
            )
            map.putString("CardType", doCreditResponse.accountInformation().cardType().getName())
            map.putString(
                "CvdApprovalCode",
                doCreditResponse.accountInformation().cvdApprovalCode()
            )
            map.putString(
                "DebitAccountType",
                doCreditResponse.accountInformation().debitAccountType().getName()
            )
            map.putString("ECRTransID", doCreditResponse.hostTraceInformation().ecrTransactionId())
            map.putString("EDCType", doCreditResponse.edcType())
            //      map.putString("ExtraBalance", doCreditResponse.amountInformation().ExtraBalance);
            map.putString(
                "GatewayTransactionID",
                doCreditResponse.hostInformation().gatewayTransactionId()
            )
            map.putString("GiftCardType", doCreditResponse.accountInformation().giftCardType().getName())
            map.putString("ReferenceNumber", doCreditResponse.traceInformation().referenceNumber())
            map.putString("HostCardType", doCreditResponse.accountInformation().hostCardType())
            map.putString("HostResponseCode", doCreditResponse.hostInformation().hostResponseCode())
            map.putString(
                "HostResponseMessage",
                doCreditResponse.torInformation().hostResponseMessage()
            )
            map.putString(
                "HostDetailMessage",
                doCreditResponse.hostInformation().hostDetailedMessage()
            )
            map.putString("HostTimeStamp", doCreditResponse.hostTraceInformation().hostTimeStamp())
            map.putString(
                "HostReferenceNumber",
                doCreditResponse.hostTraceInformation().hostReferenceNumber()
            )
            map.putString(
                "IssuerResponseCode",
                doCreditResponse.hostInformation().issuerResponseCode()
            )
            map.putString("MaskedPAN", doCreditResponse.torInformation().maskedPan())
            map.putString("ResponseMessage", doCreditResponse.responseMessage())
            map.putString("PayloadData", doCreditResponse.payloadData())
            map.putString(
                "PaymentAccountReferenceID",
                doCreditResponse.hostInformation().paymentAccountReferenceId()
            )
            //      map.putString("RawResponse", doCreditResponse.RawResponse);
//      map.putString("RemainingBalance", doCreditResponse.RemainingBalance);
            map.putString("RequestedAmount", doCreditResponse.torInformation().originalAmount())
            //      map.putString("ResultCode", doCreditResponse.ResultCode);
//      map.putString("ResultTxt", doCreditResponse.ResultTxt);
            map.putString(
                "RetrievalReferenceNumber",
                doCreditResponse.hostInformation().retrievalReferenceNumber()
            )
            //      map.putString("SigFileName", doCreditResponse.SigFileName);
            map.putString("SignData", doCreditResponse.signatureInformation().signatureData())
            map.putString("Timestamp", doCreditResponse.traceInformation().timeStamp())
            map.putString("Track1Data", doCreditResponse.accountInformation().track1Data())
            map.putString("Track2Data", doCreditResponse.accountInformation().track2Data())
            map.putString("Track3Data", doCreditResponse.accountInformation().track3Data())
            map.putString(
                "TransactionIntegrityClass",
                doCreditResponse.hostInformation().transactionIntegrityClass()
            )
            map.putString(
                "TransactionRemainingAmount",
                doCreditResponse.amountInformation().transactionRemainingAmount()
            )

            //      map.putString("ExtData", doCreditResponse.ExtData);
            promise.resolve(map)
        } else {
            val errorMsg = executionResult.message()
            val errorCode = executionResult.code().toString()
            Log.d("PAYMENT_RESPONSE_DEBUG", errorMsg)
            Log.d("PAYMENT_RESPONSE_DEBUG", errorCode)

            promise.reject(errorCode, errorMsg)
        }
    }

    private fun handleCashExecutionResult(
        executionResult: ExecutionResult<DoCashResponse>,
        promise: Promise
    ) {
        if (executionResult.isSuccessful) {
            val doCashResponse = executionResult.response()
            Log.d("PAYMENT_RESPONSE_DEBUG AuthCode", doCashResponse.responseCode())
            val map = Arguments.createMap()
            map.putString("ApprovedAmount", doCashResponse.amountInformation().approvedAmount())
            map.putString(
                "ApprovedCashBackAmount",
                doCashResponse.amountInformation().approvedCashBackAmount()
            )
            map.putString(
                "ApprovedMerchantFee",
                doCashResponse.amountInformation().approvedMerchantFee()
            )
            map.putString(
                "ApprovedTaxAmount",
                doCashResponse.amountInformation().approvedTaxAmount()
            )
            map.putString(
                "ApprovedTipAmount",
                doCashResponse.amountInformation().approvedTipAmount()
            )
            map.putString("AuthCode", doCashResponse.responseCode())
            map.putString("AuthorizationResponse", doCashResponse.responseMessage())
            //      map.putString("AvsResponse", doCashResponse.avsInformation().avsMessage());
//      map.putString("CurrentAccountNumber", doCashResponse.accountInformation().currentAccountNumber());
//      map.putString("CardType", doCashResponse.accountInformation().cardType());
//      map.putString("CvdApprovalCode", doCashResponse.accountInformation().cvdApprovalCode());
//      map.putString("DebitAccountType", doCashResponse.accountInformation().debitAccountType());
            map.putString("ECRTransID", doCashResponse.hostTraceInformation().ecrTransactionId())
//            map.putString("EDCType", doCashResponse.edcType())
            //      map.putString("ExtraBalance", doCreditResponse.amountInformation().ExtraBalance);
            map.putString(
                "GatewayTransactionID",
                doCashResponse.hostInformation().gatewayTransactionId()
            )
            //      map.putString("GiftCardType", doCashResponse.accountInformation().giftCardType());
            map.putString("ReferenceNumber", doCashResponse.traceInformation().referenceNumber())
            //      map.putString("HostCardType", doCashResponse.accountInformation().hostCardType());
            map.putString("HostResponseCode", doCashResponse.hostInformation().hostResponseCode())
            map.putString(
                "HostResponseMessage",
                doCashResponse.torInformation().hostResponseMessage()
            )
            map.putString(
                "HostDetailMessage",
                doCashResponse.hostInformation().hostDetailedMessage()
            )
            map.putString("HostTimeStamp", doCashResponse.hostTraceInformation().hostTimeStamp())
            map.putString(
                "HostReferenceNumber",
                doCashResponse.hostTraceInformation().hostReferenceNumber()
            )
            map.putString(
                "IssuerResponseCode",
                doCashResponse.hostInformation().issuerResponseCode()
            )
            map.putString("MaskedPAN", doCashResponse.torInformation().maskedPan())
            map.putString("ResponseMessage", doCashResponse.responseMessage())
            //      map.putString("PayloadData", doCashResponse.payloadData());
            map.putString(
                "PaymentAccountReferenceID",
                doCashResponse.hostInformation().paymentAccountReferenceId()
            )
            //      map.putString("RawResponse", doCashResponse.RawResponse);
//      map.putString("RemainingBalance", doCashResponse.RemainingBalance);
            map.putString("RequestedAmount", doCashResponse.torInformation().originalAmount())
            //      map.putString("ResultCode", doCashResponse.ResultCode);
//      map.putString("ResultTxt", doCashResponse.ResultTxt);
            map.putString(
                "RetrievalReferenceNumber",
                doCashResponse.hostInformation().retrievalReferenceNumber()
            )
            //      map.putString("SigFileName", doCashResponse.SigFileName);
            map.putString("SignData", doCashResponse.signatureInformation().signatureData())
            map.putString("Timestamp", doCashResponse.traceInformation().timeStamp())
            //      map.putString("Track1Data", doCashResponse.accountInformation().track1Data());
//      map.putString("Track2Data", doCashResponse.accountInformation().track2Data());
//      map.putString("Track3Data", doCashResponse.accountInformation().track3Data());
            map.putString(
                "TransactionIntegrityClass",
                doCashResponse.hostInformation().transactionIntegrityClass()
            )
            map.putString(
                "TransactionRemainingAmount",
                doCashResponse.amountInformation().transactionRemainingAmount()
            )

            //      map.putString("ExtData", doCashResponse.ExtData);
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
        const val NAME: String = "PaxPoslink2"
    }
}
