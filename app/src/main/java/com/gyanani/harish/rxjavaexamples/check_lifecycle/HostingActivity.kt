package com.gyanani.harish.rxjavaexamples.check_lifecycle

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.gyanani.harish.rxjavaexamples.R


class HostingActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lifecycle","onCreate"+this.javaClass.simpleName)
        setContentView(R.layout.host_activity)
        // create a FragmentManager
        // create a FragmentManager
        val fm: FragmentManager = supportFragmentManager
// create a FragmentTransaction to begin the transaction and replace the Fragment
// create a FragmentTransaction to begin the transaction and replace the Fragment
        val fragmentTransaction: FragmentTransaction = fm.beginTransaction()
// replace the FrameLayout with new Fragment
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, Fragment1())
        fragmentTransaction.commit() // save the changes

        findViewById<Button>(R.id.btnLaunchNextFrag).setOnClickListener {
            // create a FragmentManager
            // create a FragmentManager
            val fm: FragmentManager = supportFragmentManager
// create a FragmentTransaction to begin the transaction and replace the Fragment
// create a FragmentTransaction to begin the transaction and replace the Fragment
            val fragmentTransaction: FragmentTransaction = fm.beginTransaction()
// replace the FrameLayout with new Fragment
// replace the FrameLayout with new Fragment
            fragmentTransaction.replace(R.id.frameLayout, Fragment2())
            fragmentTransaction.apply {
            } // save the changes
        }
        Log.d("lifecycle","onCreateEnd"+this.javaClass.simpleName)

    }

    override fun onStart() {
        super.onStart()
        Log.d("lifecycle","onStart"+this.javaClass.simpleName)
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifecycle","onResume"+this.javaClass.simpleName)
    }
}