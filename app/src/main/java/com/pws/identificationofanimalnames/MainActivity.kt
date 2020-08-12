package com.pws.identificationofanimalnames

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var btn_Play: Button
    lateinit var btn_About: Button
    lateinit var btn_Close: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.setTitle("Home");

        btn_Play = findViewById(R.id.btn_Play)
        btn_About = findViewById(R.id.btn_About)
        btn_Close = findViewById(R.id.btn_Close)

        btn_Play.setOnClickListener(this)
        btn_About.setOnClickListener(this)
        btn_Close.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                /*lambda expressions ->*/
                R.id.btn_Play -> {
                    val movePlay = Intent(this@MainActivity, Play::class.java)
                    startActivity(movePlay)
                }
                R.id.btn_About -> {
                    Toast.makeText(
                        getApplicationContext(),
                        "Do Nothing , Just Display Toast Call \"About\"",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                R.id.btn_Close -> {
                    onBackPressed()
                }
            }
        }
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setMessage("Exit From The Application ?")
            .setCancelable(false)
            .setPositiveButton("Ya",
                DialogInterface.OnClickListener { arg0, arg1 -> finish() })
            .setNegativeButton("Tidak",
                DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
            .show()
    }
}