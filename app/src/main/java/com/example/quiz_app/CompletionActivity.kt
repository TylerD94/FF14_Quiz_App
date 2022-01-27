package com.example.quiz_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class CompletionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completion)

        val tvName : TextView = findViewById(R.id.tvName)
        val tvScore : TextView = findViewById(R.id.tvScoreText)
        val btnFinish : Button = findViewById(R.id.btnFinish)

        val userName : String? = intent.getStringExtra(Constants.USER_NAME)
        tvName.setText(userName)

        val correctAnswers : Int = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        val totalQuestions : Int = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val scoreText = "Your score is $correctAnswers out of $totalQuestions!"
        tvScore.setText(scoreText)

        btnFinish.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}