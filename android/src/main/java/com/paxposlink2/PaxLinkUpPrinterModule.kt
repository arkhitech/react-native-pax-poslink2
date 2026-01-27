package com.paxposlink2

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import androidx.core.graphics.scale
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableArray
import com.pax.commonlib.executor.WorkExecutor
import com.pax.egarden.devicekit.PrinterHelper
import com.pax.linkdata.LinkDevice
import com.pax.linkdata.PrintParam
import com.pax.linkdata.ResultListener
import com.pax.linkdata.cmd.LinkException
import com.pax.linkdata.cmd.printer.CommandChannelRequestContent
import com.pax.linkdata.deviceinfo.DeviceInfo
import com.pax.linkdata.deviceinfo.component.Printer
import java.io.ByteArrayOutputStream
import java.io.FileInputStream
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.net.HttpURLConnection
import java.net.URL


class PaxLinkUpPrinterModule(reactContext: ReactApplicationContext) :
  NativePaxLinkUpPrinterSpec(reactContext) {
  private val TAG = "PaxLinkUpPrinter"
  private val context: Context = reactContext
  private var mPrinterHelper: PrinterHelper? = null
  private var mLinkDeviceList: List<LinkDevice>? = null
  override fun getName(): String {
    return NAME
  }

  private fun initLinkDeviceList() {
    try {
      if (mPrinterHelper == null) {
        mPrinterHelper = PrinterHelper.getInstance(context)
      }
      mLinkDeviceList = mPrinterHelper!!.queryPrinterInfoList()
    } catch (e: LinkException) {
      e.printStackTrace()
      val errorMessage = "initLinkDeviceList failed: " + e.message;
      Log.e(TAG, "LinkException: errorCode: " + e.errCode + " errorMessage: " + errorMessage)
      mLinkDeviceList = ArrayList<LinkDevice>()
    }
  }
  private fun createTestPrinterDeviceInfo(
    deviceName: String,
    deviceId: String,
    printerComponentId: String,
    printerModal: String
    ): DeviceInfo {
    val deviceInfo: DeviceInfo = DeviceInfo();
    deviceInfo.deviceID = deviceId;
    deviceInfo.deviceName = deviceName;

    val printer = Printer();
    printer.componentID = printerComponentId;
    printer.model = printerModal;
    deviceInfo.printerList.add(printer)

    return deviceInfo;
  }

  override fun getPrinterDeviceList(testMode: Boolean?, promise: Promise) {
    try {
      if (mPrinterHelper == null) {
        mPrinterHelper = PrinterHelper.getInstance(context)
      }
      if(testMode != null && testMode) {
        val device1: DeviceInfo = DeviceInfo();

        val linkDeviceList: List<LinkDevice> = arrayListOf(
          LinkDevice(createTestPrinterDeviceInfo(
            "T3180 - Front",
            "t3180-front",
            "t3180-pcid",
            "t3180"
          )),
          LinkDevice(createTestPrinterDeviceInfo(
            "T3180 - Back",
            "t3180-back",
            "t3180-pcba",
            "t3181"
          )),
          LinkDevice(createTestPrinterDeviceInfo(
            "T3180 - Front2",
            "t3180-front2",
            "t3180-pcid2",
            "t3180"
          )),
          LinkDevice(createTestPrinterDeviceInfo(
            "T3181 - Back2",
            "t3181-back2",
            "t3181-pcba2",
            "t3181"
          )),
        );

//        val responseArray = Arguments.createArray();
//        responseArray.pushMap(Arguments.createMap().apply {
//          putString("deviceName", "T3180 - Front")
//          putString("deviceId", "t3180-front")
//          putString("printerComponentId", "t3180-pcid")
//          putString("printerModal", "t3180")
//        })
//        promise.resolve(responseArray)
//        return;
        mLinkDeviceList = linkDeviceList
      } else {
        mLinkDeviceList = mPrinterHelper!!.queryPrinterInfoList()
      }
      Log.d(TAG, "printer info query done")
      val responseArray = Arguments.createArray();
      mLinkDeviceList!!.forEach { linkDevice ->
        linkDevice.printerList.forEach { printer ->
          Log.d(
            TAG,
            "DeviceName: ${linkDevice.deviceName}, DeviceID: ${linkDevice.deviceID}" +
              " ComponentID: ${printer.componentID} model: ${printer.model}"
          )
          val printerDeviceInfoMap = Arguments.createMap();
          printerDeviceInfoMap.putString("deviceName", linkDevice.deviceName);
          printerDeviceInfoMap.putString("deviceId", linkDevice.deviceID);
          printerDeviceInfoMap.putString("printerComponentId", printer.componentID);
          printerDeviceInfoMap.putString("printerModal", printer.model);
          responseArray.pushMap(printerDeviceInfoMap)
        }
      }
      promise.resolve(responseArray);
    } catch (e: LinkException) {
      e.printStackTrace()
      val errorMessage = "queryPrinterInfoList failed: " + e.message;
      Log.e(TAG,"LinkException: errorCode: " + e.errCode + " errorMessage: " + errorMessage)
      promise.reject(e.errCode.toString(), "LinkException: errorCode: " + e.errCode + " errorMessage: " + errorMessage);
    }
  }

  private fun bitmap2Byte(bm: Bitmap?): ByteArray? {
    if (bm == null) {
      return null
    }
    val byteArrayOutputStream = ByteArrayOutputStream()
    bm.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    return byteArrayOutputStream.toByteArray()
  }

  private fun getBitmapFromURL(src: String): Bitmap? {
    try {
      val url = URL(src)
      val connection = url.openConnection() as HttpURLConnection
      connection.setDoInput(true)
      connection.connect()
      val input = connection.getInputStream()
      val myBitmap = BitmapFactory.decodeStream(input)

      val baos = ByteArrayOutputStream()
      //            myBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
      //TODO use regex
      if (src.contains(".jpg")) {
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
      } else {
        myBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
      }

      return myBitmap
    } catch (e: IOException) {
      // Log exception
      return null
    }
  }

  private fun printBitmap(
    printerDeviceId: String,
    printerComponentId: String,
    bitmap: Bitmap,
    imageWidth: Double,
    imageHeight: Double,
    cutMode: Double,
    testMode: Boolean?,
    promise: Promise) {
//    if(mLinkDeviceList == null) {
//      initLinkDeviceList()
//    }
//    val printerDevice = mLinkDeviceList.find { linkDevice ->
//      linkDevice.deviceID == printerDeviceId
//    }
//    if(printerDevice == null) {
//      promise.reject("Not found", "Printer Device not found: " + printerDeviceId)
//      return;
//    }
//    val printer = printerDevice.printerList.find { printer ->  printer.componentID == printerComponentId }
//    if(printer == null) {
//      promise.reject("Not found", "Printer not found: " + printerComponentId)
//      return;
//    }
//    printerDevice.setCurrentComponentID(printer.componentID)

    val printParam = PrintParam()
//    printParam.feedLen = 120; //120 dots ≈ 15 mm (1.5 cm) of paper feed
    printParam.cutMode = cutMode.toInt()
    if(imageWidth.toInt() > 0) {
      printParam.labelSizeW = imageWidth.toInt();
    }
    if(imageHeight.toInt() > 0) {
      printParam.labelSizeH = imageWidth.toInt();
    }
    //TODO center image perhaps by using printParam.labelGapOffset
    val mListener: ResultListener = object : ResultListener() {
      override fun onSuccess() {
        promise.resolve("success")
      }

      override fun onFailed(s: String?) {
        Log.e(TAG, "Printing failed: " + s)
        promise.reject("Printing Failed", s)
      }
    }

    var bytes: ByteArray? = null
    if(imageWidth.toInt() > 0 || imageHeight.toInt() > 0) {
      if(imageWidth.toInt() > 0 && imageHeight.toInt() > 0) {
        bytes = bitmap2Byte(bitmap.scale(imageWidth.toInt(), imageHeight.toInt(), false));
      } else if(imageWidth.toInt() > 0) {
        //scale with width as factor
        val factor = imageWidth.toInt() / b.width.toFloat()
        bytes = bitmap2Byte(bitmap.scale(imageWidth.toInt(), (b.height * factor).toInt(), false));
      } else {
        //scale with height as factor
        val factor = imageHeight.toInt() / b.height.toFloat()
        bytes = bitmap2Byte(bitmap.scale((b.width * factor).toInt(), imageHeight.toInt(), false));
      }
    } else {
      bytes = bitmap2Byte(bitmap)
    }
    if (bytes == null) {
      promise.reject("Invalid Image", "Please select a valid image file.")
      return
    }

    try {
      printParam.printData = bytes
      Log.d(TAG, "start print image")
      mPrinterHelper!!.printImage(
        printerDeviceId,
        printerComponentId,
        printParam,
        mListener
      )
    } catch (e: LinkException) {
      e.printStackTrace()
      Log.e(TAG, "LinkException: errorCode: " + e.errCode + " errorMessage: " + e.message)
      if(testMode != null && testMode) {
        promise.resolve("LinkException: errorCode: " + e.errCode + " errorMessage: " + e.message)
      } else {
        promise.reject(e.errCode.toString(), "LinkException: errorCode: " + e.errCode + " errorMessage: " + e.message)
      }
    }
  }
  override fun printImageUrl(
    printerDeviceId: String,
    printerComponentId: String,
    imageUrl: String,
    imageWidth: Double,
    imageHeight: Double,
    cutMode: Double,
    testMode: Boolean?,
    promise: Promise) {

    var bitmap: Bitmap? = null;
    if (imageUrl.contains("http")) {
      bitmap = getBitmapFromURL(imageUrl)
    } else {
      try {
        bitmap =
          MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.parse(imageUrl))
      } catch (e: IOException) {
        promise.reject(TAG, "image not found")
        return
      }
    }
    printBitmap(
      printerDeviceId,
      printerComponentId,
      bitmap!!,
      imageWidth,
      imageHeight,
      cutMode,
      testMode,
      promise
    )
  }

  override fun printImageBase64(
    printerDeviceId: String,
    printerComponentId: String,
    base64Image: String,
    imageWidth: Double,
    imageHeight: Double,
    cutMode: Double,
    testMode: Boolean?,
    promise: Promise) {

    val decodedString = Base64.decode(base64Image, Base64.DEFAULT)
    val bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    printBitmap(
      printerDeviceId,
      printerComponentId,
      bitmap,
      imageWidth,
      imageHeight,
      cutMode,
      testMode,
      promise
    )
  }

  override fun printRawCommands(
    printerDeviceId: String,
    printerComponentId: String,
    base64Strings: ReadableArray,
    testMode: Boolean?,
    promise: Promise) {
    if (mPrinterHelper == null) {
      mPrinterHelper = PrinterHelper.getInstance(context)
    }

    WorkExecutor.execute {
      try {
        for(i in 0 until base64Strings.size()) {
          val base64String = base64Strings.getString(i);
          val requestContent = CommandChannelRequestContent()
          requestContent.cmdCaller = "Simobo"
          requestContent.sendTimeout = 10000
          requestContent.recvTimeout = 10000
          requestContent.isLastCmd = (i + 1) >= base64Strings.size()
          requestContent.expRecvLen = 0
          requestContent.cmdRequestData = base64String

          mPrinterHelper!!.sendRawCommand(
            printerDeviceId,
            printerComponentId,
            requestContent
          )
        }
        promise.resolve("success");
      } catch (e: LinkException) {
        e.printStackTrace()
        Log.e(TAG, "LinkException: error printing raw command, errorCode: " + e.errCode + " errorMessage: " + e.message)
        if(testMode != null && testMode) {
          promise.resolve("LinkException: error printing raw command, errorCode: " + e.errCode + " errorMessage: " + e.message)
        } else {
          promise.reject(e.errCode.toString(), "LinkException: error printing raw command, errorCode: " + e.errCode + " errorMessage: " + e.message)
        }
      } catch (e: UnsupportedEncodingException) {
        Log.e(TAG, "UnsupportedEncodingException: error printing raw command, " + e.message)
        e.printStackTrace()
        promise.reject("UnsupportedEncodingException", e.message)
      }
    }
  }

  companion object {
    const val NAME: String = "PaxLinkUpPrinter"
  }
}
