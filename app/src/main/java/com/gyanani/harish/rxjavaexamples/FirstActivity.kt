package com.gyanani.harish.rxjavaexamples

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * Api call using HttpURLConnection and RxJava (for thread management)
 */
class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        createObserver()
    }

    //region step 1 api calling
    private fun apiCalling(): String {
        val url = URL("https://jsonplaceholder.typicode.com/todos/1")
        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        urlConnection.requestMethod = "GET"

        val inputStream: InputStream = urlConnection.inputStream
        return readStream(inputStream)
    }

    private fun readStream(inputStream: InputStream): String {
        var reader: BufferedReader? = null
        val response = StringBuffer()
        try {
            reader = BufferedReader(InputStreamReader(inputStream))
            var line: String? = ""
            while (reader.readLine().also { line = it } != null) {
                response.append(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (reader != null) {
                try {
                    reader.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return response.toString()
    }
    //endregion step 1 api calling

    //region step 2 create observable around api call
    private fun createObservable(): Observable<String> {
        return Observable.create { emitter ->
            emitter.onNext(apiCalling())
            emitter.onComplete()
        }
    }
    //endregion step 2 create observable around api call

    //region step 3 create observer
    private fun createObserver() {
        val a = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.d("abcd", "onSubscribe")
            }

            override fun onNext(t: String) {
                Log.d("abcd", "onNext")
                Log.d("abcd", t)
            }

            override fun onError(e: Throwable) {
                Log.d("abcd", "onError")
                Log.d("abcd", e.stackTrace.toString())
            }

            override fun onComplete() {
                Log.d("abcd", "onComplete")
            }
        }
        createObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(a)
    }
    //endregion step 3 create observer
}