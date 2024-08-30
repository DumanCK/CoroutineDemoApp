package com.duman.coroutineapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    private lateinit var btCounter : Button
    private lateinit var tvCounter : TextView
    private var nilai = 0
    private lateinit var tvAngkaLog : TextView

    private lateinit var printButton: Button

    private suspend fun printLogs(){
        for (i in 1..1000000){
            Log.i("PL", "nilai i: $i")
            withContext(Dispatchers.Main){
                tvAngkaLog.text = "nilai i : $i"
            }
            delay(100)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvAngkaLog = findViewById(R.id.tvAngkaLog)
        printButton = findViewById(R.id.btPrintLog)
        printButton.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                printLogs()
            }
        }

        btCounter = findViewById(R.id.btCounter)
        tvCounter = findViewById(R.id.tvCounter)
        tvCounter.text = nilai.toString()
        btCounter.setOnClickListener{
            ++nilai
            tvCounter.text = nilai.toString()
        }

    }



}