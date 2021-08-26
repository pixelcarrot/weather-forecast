package com.pixelcarrot.weatherforecast

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.pixelcarrot.weatherforecast.di.AppModule
import com.pixelcarrot.weatherforecast.usecase.GetCurrentWeatherUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val module: AppModule by lazy { AppModule(AppContext(applicationContext)) }
    private val useCase: GetCurrentWeatherUseCase by lazy { module.getCurrentWeatherUseCase }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        CoroutineScope(Job() + Dispatchers.Main).launch {
            tv.text = useCase.execute(0.0, 0.0).toString()
        }
    }

}
