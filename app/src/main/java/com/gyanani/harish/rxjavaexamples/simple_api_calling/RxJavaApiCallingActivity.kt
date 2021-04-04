package com.gyanani.harish.rxjavaexamples.simple_api_calling

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.gyanani.harish.rxjavaexamples.R
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber

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
        /*return Observable.create(object: ObservableOnSubscribe<String>{
            override fun subscribe(emitter: ObservableEmitter<String>) {
                emitter.onNext(
                    //region step 1 api calling
                    Utils.apiCalling()
                    //endregion step 1 api calling
                )
                emitter.onComplete()
            }
        })*/
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
        val observer = object : Observer<String> {
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
            /*.map(object: Function<String, String> {
                override fun apply(t: String): String {
                    return t.replace("{", "starting braces")
                }
            })*/
            /*.map(object : Function<String, StringBuffer> {
                override fun apply(t: String): StringBuffer {
                    return StringBuffer(t.replace("{", "starting braces"))
                }
            })*/
            .map(object : Function<String, StringBuffer> {
            override fun apply(t: String): StringBuffer {
                return StringBuffer(t.replace("{", "starting braces"))
            }
            })
            .map(object : Function<StringBuffer, String> {
                override fun apply(t: StringBuffer): String {
                    return t.toString()
                }
            })
            .map { t -> t.replace("{", "starting braces") }
            /*.map(String::uppercase)*/
            .subscribe(observer)
    }
    //endregion step 3 create observer
}