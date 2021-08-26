package com.pixelcarrot.weatherforecast.asset

import android.content.Context

actual class AssetManager(private val context: Context) {
    actual fun loadAsset(name: String): String = context.assets
        .open(name)
        .bufferedReader()
        .use { it.readText() }
}
