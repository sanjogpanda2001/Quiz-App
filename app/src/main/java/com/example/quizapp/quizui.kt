package com.example.quizapp


import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color.parseColor
import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quizui.*

class quizui : AppCompatActivity(), View.OnClickListener {
    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Questioned>? = null
    private var mSelectPosition: Int = 0
    private var correctanswer=0
    private var mUserName:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizui)

        mUserName=intent.getStringExtra(constants.USER_NAME)

        mQuestionsList = constants.getQuestions()
        setQuestion()

        option_one.setOnClickListener(this)
        option_two.setOnClickListener(this)
        option_three.setOnClickListener(this)
        option_four.setOnClickListener(this)
        submit.setOnClickListener(this)

    }

    private fun setQuestion() {

        val qu: Questioned? = mQuestionsList!![mCurrentPosition - 1]
        defaultOptionsView()
        if(mCurrentPosition==mQuestionsList!!.size)
            submit.text="FINISH"
        else submit.text="SUBMIT"

        progressBar.progress = mCurrentPosition
        tvprogress.text = "$mCurrentPosition" + "/" + progressBar.max

        if (qu != null) {
            iv.setImageResource(qu.image)
            tvprogress.text="$mCurrentPosition"+"/"+ progressBar.max
            option_one.text = qu.optionOne
            option_two.text = qu.optionTwo
            option_three.text = qu.optionThree
            option_four.text = qu.optionFour
        }
    }
    private  fun defaultOptionsView(){
        val options=ArrayList<TextView>()
        options.add(0,option_one)
        options.add(1,option_two)
        options.add(2,option_three)
        options.add(3,option_four)
        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface= Typeface.DEFAULT
            option.background=ContextCompat.getDrawable(this,R.drawable.default_option_border_bg)

        }
    }
private fun answerView(answer:Int,drawableView: Int){
    when(answer){
        1->{
            option_one.background=ContextCompat.getDrawable(this,drawableView)

        }
        2->{
            option_two.background=ContextCompat.getDrawable(this,drawableView)

        }
        3->{
            option_three.background=ContextCompat.getDrawable(this,drawableView)

        }
        4->{
            option_four.background=ContextCompat.getDrawable(this,drawableView)

        }
    }
}
    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
            R.id.option_one->{
                selectedOptionView(option_one,1)

            }
                R.id.option_two->{
                    selectedOptionView(option_two,2)
                }
                R.id.option_three->{
                    selectedOptionView(option_three,3)
                }
                R.id.option_four->{
                    selectedOptionView(option_four,4)
                }
                R.id.submit->{
                    if(mSelectPosition==0){
                        mCurrentPosition++

                        when{
                            mCurrentPosition<= mQuestionsList!!.size->{
                                setQuestion()
                            }
                            else->{
                                val intent=Intent(this,resultActivity::class.java)
                                intent.putExtra(constants.USER_NAME,mUserName)
                                intent.putExtra(constants.CORRECT_ANSWERS,correctanswer)
                                intent.putExtra(constants.TOTAL_QUESTIONS,mQuestionsList!!.size)
                               // intent.putExtra(constants.USER_NAME,mUserName)
                                //intent.putExtra(constants.CORRECT_ANSWERS,correctanswer)
                                //intent.putExtra(constants.TOTAL_QUESTIONS,mQuestionsList!!.size)
                                startActivity(intent)  //Intent(this, resultActivity::class.java)
                               // Toast.makeText(this,"You have successfully completed the quiz",Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    else{
                        val questn=mQuestionsList?.get(mCurrentPosition-1)
                        if(questn!!.correct!= mSelectPosition){
                            answerView(mSelectPosition,R.drawable.redwrong)
                        }
                        else{
                            correctanswer++
                        }
                        answerView(questn.correct,R.drawable.greencorrect)

                        if (mCurrentPosition==mQuestionsList!!.size){
                            submit.text="FINISH"
                        }
                        else{
                            submit.text="GO TO NEXT QUESTION"
                        }
                    }
                    mSelectPosition=0
                }
            }
        }
    }




    private fun selectedOptionView(tv:TextView,selectedOptionNumber:Int){
        defaultOptionsView()
        mSelectPosition=selectedOptionNumber

        tv.setTextColor(Color.parseColor("#363A43"))

        tv.background=ContextCompat.getDrawable(this,R.drawable.selectedoption)

    }


}