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
class RxJavaApiCallingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        createObserver()
    }

    //region step 2 create observable around api call
    private fun createObservable(): Observable<String> {
        return Observable.create { emitter ->
            emitter.onNext(
                //region step 1 api calling
                Utils.apiCalling()
                //endregion step 1 api calling
            )
            emitter.onComplete()
        }
    }
    //endregion step 2 create observable around api call

    //region step 3 create observer
    private fun createObserver() {
        val a = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.d("Observer", "onSubscribe")
            }

            override fun onNext(t: String) {
                Log.d("Observer", "onNext")
                Log.d("Observer", t)
            }

            override fun onError(e: Throwable) {
                Log.d("Observer", "onError")
                Log.d("Observer", e.stackTrace.toString())
            }

            override fun onComplete() {
                Log.d("Observer", "onComplete")
            }
        }
        createObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(a)
    }
    //endregion step 3 create observer
}