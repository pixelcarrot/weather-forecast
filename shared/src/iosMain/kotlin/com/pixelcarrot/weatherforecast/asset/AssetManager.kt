package com.pixelcarrot.weatherforecast.asset

import platform.Foundation.*

actual class AssetManager {
    actual fun loadAsset(name: String): String {
        val nsUrl = NSBundle.mainBundle.URLForResource(name, "")
        val nsData = nsUrl?.let { NSData.create(it) }
        val nsString = nsData?.let { NSString.create(it, NSUTF8StringEncoding) }
        return nsString.toString()
    }
}