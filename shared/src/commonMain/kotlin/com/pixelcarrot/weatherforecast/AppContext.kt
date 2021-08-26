package com.pixelcarrot.weatherforecast

import com.pixelcarrot.weatherforecast.asset.AssetManager

expect class AppContext {
    fun createAssetManager(): AssetManager
}
