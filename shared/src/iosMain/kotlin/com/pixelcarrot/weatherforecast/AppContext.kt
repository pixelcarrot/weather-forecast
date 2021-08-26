package com.pixelcarrot.weatherforecast

import com.pixelcarrot.weatherforecast.asset.AssetManager

actual class AppContext {
    actual fun createAssetManager(): AssetManager = AssetManager()
}
