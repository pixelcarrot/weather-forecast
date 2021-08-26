package com.pixelcarrot.weatherforecast

import android.content.Context
import com.pixelcarrot.weatherforecast.asset.AssetManager

actual class AppContext(private val context: Context) {
    actual fun createAssetManager(): AssetManager = AssetManager(context)
}
