package com.gyanani.harish.rxjavaexamples.simple_api_calling

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.gyanani.harish.rxjavaexamples.R

class AsyncTaskApiCallingActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        HTTPAsyncTask().execute()
    }
    @SuppressLint("StaticFieldLeak")
    inner class HTTPAsyncTask : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String {
            Log.d("HTTPAsyncTask", "doInBackground")
            return Utils.apiCalling()
        }
        override fun onPostExecute(result: String) {
            Log.d("HTTPAsyncTask", "onPostExecute")
            Log.d("HTTPAsyncTask", result)
        }
    }
}