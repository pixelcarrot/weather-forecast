package com.pixelcarrot.weatherforecast.platform

import platform.Foundation.*

actual class Platform actual constructor() {
    actual fun loadAsset(name: String): String {
        val nsUrl = NSBundle.mainBundle.URLForResource(name, "")
        val nsData = nsUrl?.let { NSData.create(it) }
        val nsString = nsData?.let { NSString.create(it, NSUTF8StringEncoding) }
        return nsString.toString()
    }
}