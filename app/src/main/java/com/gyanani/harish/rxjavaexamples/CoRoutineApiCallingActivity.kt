package com.gyanani.harish.rxjavaexamples

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class CoRoutineApiCallingActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        lifecycleScope.launch {
            val result = coRoutineExample()
            Log.d("CoRoutine",result)
        }
    }

    private suspend fun coRoutineExample(): String {
        return withContext(Dispatchers.IO) {
            Utils.apiCalling()
        }
    }

}