package com.example.quiz_app

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import kotlin.random.Random


class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition : Int = 1
    private var mCorrectQuestions : Int = 0
    private var mQuestionsList : ArrayList<Question>? = null
    private var mSelectedOptionPosition : Int = 0
    private var mUserName : String? = null

    // Initialize variables as null
    private var progressBar : ProgressBar? = null
    private var tvProgressBar : TextView? = null
    private var tvQuestion : TextView? = null
    private var ivImage : ImageView? = null

    private var tvAns1 : TextView? = null
    private var tvAns2 : TextView? = null
    private var tvAns3 : TextView? = null
    private var tvAns4 : TextView? = null

    private var btnSubmit: Button? = null

    private var selectedQuestions = ArrayList<Int>()
    private var currentQuestion : Question? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        if(mUserName.equals("Sarah")){
            Toast.makeText(this, "I love you! <3", Toast.LENGTH_LONG).show()
        }

        // Get questions from the Constants class
        // Stored as ArrayList
        mQuestionsList = Constants.getQuestions()

        setUpElements()
        setQuestion()

    }

    private fun setUpElements() {
        // Assign variables the associated elements
        progressBar = findViewById(R.id.pbProgressBar)
        tvProgressBar = findViewById(R.id.tvProgressBar)
        tvQuestion = findViewById(R.id.tvQuestionText)
        ivImage = findViewById(R.id.ivImage)

        tvAns1 = findViewById(R.id.tvAns1)
        tvAns2 = findViewById(R.id.tvAns2)
        tvAns3 = findViewById(R.id.tvAns3)
        tvAns4 = findViewById(R.id.tvAns4)

        btnSubmit = findViewById(R.id.btnSubmit)
    }

    private fun setQuestion() {
        currentQuestion = mQuestionsList!![chooseNextQuestion()]


        ivImage?.setImageResource(currentQuestion!!.image)
        progressBar?.progress = mCurrentPosition
        progressBar?.max = mQuestionsList!!.size
        tvProgressBar?.text = "${mCurrentPosition}/${progressBar?.max}"
        tvQuestion?.text = currentQuestion!!.question

        tvAns1?.text = currentQuestion!!.optionOne
        tvAns2?.text = currentQuestion!!.optionTwo
        tvAns3?.text = currentQuestion!!.optionThree
        tvAns4?.text = currentQuestion!!.optionFour


        // Set up text views to listen for click events, passing themselves to onClick function
        tvAns1?.setOnClickListener(this)
        tvAns2?.setOnClickListener(this)
        tvAns3?.setOnClickListener(this)
        tvAns4?.setOnClickListener(this)

        btnSubmit?.setOnClickListener(this)

        btnSubmit?.text = "Submit"
        defaultOptionsView()
    }

    private fun chooseNextQuestion(): Int {
        var nextQuestion = Random.nextInt(0, mQuestionsList!!.size)

        while (selectedQuestions.contains(nextQuestion)) {
            nextQuestion = Random.nextInt(0, mQuestionsList!!.size)
        }
        selectedQuestions.add(nextQuestion)
        return nextQuestion
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()

        tvAns1?.let{
            options.add(0, it)
        }
        tvAns2?.let{
            options.add(1, it)
        }
        tvAns3?.let{
            options.add(2, it)
        }
        tvAns4?.let{
            options.add(3, it)
        }

        for(option in options){
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectedOptionView(tv:TextView, selectedOptionNum:Int){
        // Sets all TextViews to a default state
        defaultOptionsView()

        // sets the selected options position to the selected option
        mSelectedOptionPosition = selectedOptionNum

        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1 -> tvAns1?.background = ContextCompat.getDrawable(this, drawableView)
            2 -> tvAns2?.background = ContextCompat.getDrawable(this, drawableView)
            3 -> tvAns3?.background = ContextCompat.getDrawable(this, drawableView)
            4 -> tvAns4?.background = ContextCompat.getDrawable(this, drawableView)
        }
    }

    override fun onClick(view: View?) {
        // when == switch statement

        // Selected TextView is referenced by id
        // Because TextViews are nullable, they must use .let
        // selectedOptionView takes the self(it), and the option number to set the style of selection
        when(view?.id){
            R.id.tvAns1 -> {
                tvAns1?.let {
                    selectedOptionView(it, 1)
                }
            }
            R.id.tvAns2 -> {
                tvAns2?.let {
                    selectedOptionView(it, 2)
                }
            }
            R.id.tvAns3 -> {
                tvAns3?.let {
                    selectedOptionView(it, 3)
                }
            }
            R.id.tvAns4 -> {
                tvAns4?.let {
                    selectedOptionView(it, 4)
                }
            }
            R.id.btnSubmit -> {
                btnSubmit?.let{
                    if(mSelectedOptionPosition == 0){
                        mCurrentPosition++

                        when{mCurrentPosition <= mQuestionsList!!.size -> {
                                setQuestion()
                            }
                            else -> {
                                loadResultsScreen()
                            }
                        }

                    }else{
                        checkAnswer()
                    }
                }
            }
        }
    }

    private fun loadResultsScreen() {
        val intent = Intent(this, CompletionActivity::class.java)
        intent.putExtra(Constants.USER_NAME, mUserName)
        intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectQuestions)
        intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList?.size)
        startActivity(intent)
        finish()
    }

    private fun checkAnswer() {
        val question = mQuestionsList?.get(currentQuestion!!.id - 1)
        if (question!!.correctAnswer != mSelectedOptionPosition) {
            answerView(mSelectedOptionPosition, R.drawable.incorrect_option_bg)
        } else {
            mCorrectQuestions++
        }
        answerView(question.correctAnswer, R.drawable.correct_option_bg)

        if (mCurrentPosition == mQuestionsList!!.size) {
            btnSubmit?.text = "FINISH"
        } else {
            btnSubmit?.text = "NEXT QUESTION"
        }

        mSelectedOptionPosition = 0
    }
}