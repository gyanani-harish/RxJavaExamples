package com.gyanani.harish.rxjavaexamples.check_lifecycle

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gyanani.harish.rxjavaexamples.R
import java.lang.Exception

open class BaseFragment : Fragment() {
    override fun onAttach(context: Context) {
        super.onAttach(context)
        printLog()
    }

    private fun printLog() {
        val e = Exception()
        Log.d("lifecycle",javaClass.simpleName+" " +e.stackTrace[1].methodName)
    }


    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
        printLog()
    }

    override fun onStart() {
        super.onStart()
        printLog()
    }

    override fun onResume() {
        super.onResume()
        printLog()
    }

    override fun onPause() {
        super.onPause()
        printLog()
    }

    override fun onStop() {
        super.onStop()
        printLog()
    }

    override fun onDestroy() {
        super.onDestroy()
        printLog()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        printLog()
    }

    override fun onDetach() {
        super.onDetach()
        printLog()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        printLog()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        printLog()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        printLog()
    }
}