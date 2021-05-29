package com.gyanani.harish.rxjavaexamples.simple_api_calling

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gyanani.harish.rxjavaexamples.R

class JavaThreadApiCallingActivity: AppCompatActivity() {

    private lateinit var textView1: TextView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        textView1 = findViewById(R.id.textView1)
        callApiUsingRunnable()
    }

    private fun callApiUsingRunnable() {
        Log.d("callApiUsingRunnable", "step1")
        Thread {
            Log.d("callApiUsingRunnable", "step2")
            val result = Utils.apiCalling()
            Log.d("callApiUsingRunnable", "step3")
            //cannot do here because background thread cannot access UI in android
            //textView1.text = result
            runOnUiThread{
                Log.d("callApiUsingRunnable", "step4")
                Log.d("callApiUsingRunnable", result)
                textView1.text = result
            }
        }.start()
    }

}