package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class resultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val username=intent.getStringExtra(constants.USER_NAME)

        val totlq=intent.getIntExtra(constants.TOTAL_QUESTIONS,0)
        val corrct=intent.getIntExtra(constants.CORRECT_ANSWERS,0)
        tvusername.text=username
        out.text="Your Score is $corrct out of $totlq"                   //displays the final result alongwithh the username
        restart.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}