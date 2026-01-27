/*
 * ===========================================================================================
 * = COPYRIGHT
 *          PAX Computer Technology(Shenzhen) CO., LTD PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or nondisclosure
 *   agreement with PAX Computer Technology(Shenzhen) CO., LTD and may not be copied or
 *   disclosed except in accordance with the terms in that agreement.
 *     Copyright (C) YYYY-? PAX Computer Technology(Shenzhen) CO., LTD All rights reserved.
 * Description: // Detail description about the function of this module,
 *             // interfaces with the other modules, and dependencies.
 * Revision History:
 * Date	                 Author	                Action
 * 2021/01/06  	         Alex           	    Create
 * ===========================================================================================
 */
package com.pax.linkupsdk.demo

import java.util.concurrent.ExecutorService
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

object WorkExecutor {
  private val mWorkExecutor: ExecutorService

  init {
    val threadCount = Runtime.getRuntime().availableProcessors()
    mWorkExecutor = ThreadPoolExecutor(
      threadCount, threadCount * 2, 3, TimeUnit.SECONDS,
      LinkedBlockingQueue<Runnable>(20)
    )
  }

  fun execute(command: Runnable) {
    mWorkExecutor.execute(command)
  }

  fun release() {
    mWorkExecutor.shutdown()
  }
}
