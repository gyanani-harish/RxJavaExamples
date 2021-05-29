package com.gyanani.harish.rxjavaexamples.simple_api_calling

import android.os.*
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class HandlerThreadActivity: AppCompatActivity() {
    private val handlerThread : HandlerThread = HandlerThread("my handler thread")
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handlerThread.start()
        handler = Handler(handlerThread.looper)
        handler.post(ExampleRunnable1())
        handler.post(ExampleRunnable2())
    }

    override fun onDestroy() {
        super.onDestroy()
        handlerThread.quit()
    }

    class ExampleRunnable1 : Runnable{
        override fun run() {
            Log.d("TAG", "Runnable1")
            SystemClock.sleep(1000)
            val apiResponse = Utils.apiCalling()
            Log.d("TAG",apiResponse)
        }
    }

    class ExampleRunnable2 : Runnable{
        override fun run() {
            for(i in 0..4){
                Log.d("TAG", "Runnable2"+i)
                SystemClock.sleep(1000)
            }
        }
    }
}