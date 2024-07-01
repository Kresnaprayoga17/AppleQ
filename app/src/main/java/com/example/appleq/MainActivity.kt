package com.example.appleq

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.appleq.R.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnIntent : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        btnIntent = findViewById(id.btnMenu)
        btnIntent.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                id.btnMenu -> run {
                    val intentBiasa = Intent(this@MainActivity, MenuActivity::class.java)
                    startActivity(intentBiasa)
                }
            }
        }
    }
}