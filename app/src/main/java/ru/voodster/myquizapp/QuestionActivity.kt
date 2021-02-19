package ru.voodster.myquizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import ru.voodster.myquizapp.databinding.ActivityQuestionBinding

class QuestionActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var quebinding: ActivityQuestionBinding

    private var mCurrentPosition:Int = 1
    private var mQuestionList:ArrayList<Question>? = null
    private var mSelectedOptionPosition : Int = 0
    private var rightAnswers : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        quebinding = ActivityQuestionBinding.inflate(layoutInflater)
        val questionMainView = quebinding.root
        setContentView(questionMainView)


        mQuestionList = Constants.getQuestions()

        setQuestion()

        quebinding.optOneBtn.setOnClickListener (this)
        quebinding.optTwoBtn.setOnClickListener (this)
        quebinding.optThreeBtn.setOnClickListener (this)
        quebinding.optFourBtn.setOnClickListener (this)

    }

    private fun defaultOptionView() {
        val options = ArrayList<TextView>()
        options.add(0,quebinding.optOneBtn)
        options.add(1,quebinding.optTwoBtn)
        options.add(2,quebinding.optThreeBtn)
        options.add(3,quebinding.optFourBtn)


        for(option in options) {
            option.setBackgroundColor(Color.parseColor("#004A7C"))
            option.typeface = Typeface.DEFAULT
        }

    }

    private fun selectedOpt(tv:TextView,
                            selectedOptionNum:Int) {
        defaultOptionView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.setBackgroundColor(Color.parseColor("#FF018786"))
    }


    private fun setQuestion (){
        val question =
                mQuestionList!![mCurrentPosition-1]

        defaultOptionView()

        quebinding.progressBar.setProgress(mCurrentPosition)
            quebinding.questionImageView.setBackgroundResource(question.image)
            quebinding.questionTextView.text = question.question
            quebinding.optOneBtn.text = question.optOne
            quebinding.optTwoBtn.text = question.optTwo
            quebinding.optThreeBtn.text = question.optThree
            quebinding.optFourBtn.text = question.optFour
        }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.optOneBtn ->{
                selectedOpt(quebinding.optOneBtn, 1)
            }
            R.id.optTwoBtn ->{
                selectedOpt(quebinding.optTwoBtn, 2)
            }
            R.id.optThreeBtn->{
                selectedOpt(quebinding.optThreeBtn, 3)
            }
            R.id.optFourBtn ->{
                selectedOpt(quebinding.optFourBtn, 4)
            }
        }
    }


    fun submit(view: View){

        if (mSelectedOptionPosition == mQuestionList!![mCurrentPosition-1].correctAnswer) {
            rightAnswers++
        }
        if (mCurrentPosition == mQuestionList!!.size) {
            if (rightAnswers ==mQuestionList!!.size){
                val intent = Intent(this, FinishActivity::class.java)
                startActivity(intent)
                finish()
            }else {
                Toast.makeText(this,
                "Right answers:   $rightAnswers / 3 ",Toast.LENGTH_SHORT).show()
                mCurrentPosition = 1
                setQuestion()
                rightAnswers = 0
            }
        }else{
            mCurrentPosition++
            setQuestion()
        }
    }
}