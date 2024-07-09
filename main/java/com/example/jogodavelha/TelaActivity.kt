package com.example.jogodavelha

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.jogodavelha.databinding.ActivityMainBinding
import com.example.jogodavelha.databinding.ActivityTelaBinding

class TelaActivity : AppCompatActivity () {

    private lateinit var binging: ActivityTelaBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binging = ActivityTelaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binging.root)

        binging.buttonPlayerVsMachine.setOnClickListener {

            val i = Intent(this, MainActivity::class.java)
            startActivity(i)

        }

        binging.buttonPlayerVsPlayer.setOnClickListener {

            val i = Intent(this, JogadorActivity::class.java)
            startActivity(i)
        }


    }
}
