package com.logoquiz.phonepe.view

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.logoquiz.phonepe.R
import com.logoquiz.phonepe.adapter.ResultAdapter
import com.logoquiz.phonepe.adapter.SuggestionAdapter
import com.logoquiz.phonepe.common.Utils
import com.logoquiz.phonepe.common.loadUrl
import com.logoquiz.phonepe.databinding.ActivityQuizBinding
import com.logoquiz.phonepe.model.QuestionModel
import com.logoquiz.phonepe.viewModel.QuestionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

/**
 * Created by Vinod Kumar on 08/11/22.
 */

@AndroidEntryPoint
class QuizActivity : AppCompatActivity(), SuggestionAdapter.AdapterCallBack {
    private lateinit var bind: ActivityQuizBinding
    private lateinit var timer:CountDownTimer
    private val viewModelQuestion: QuestionViewModel by viewModels()
    private lateinit var randomSet:HashSet<Int>
    lateinit var answer: CharArray
    var correct_answer: String = ""
    private var numOfQuestion=1
    private var score=0
    var suggestList: MutableList<String> = mutableListOf()
    lateinit var suggestionAdapter: SuggestionAdapter
    lateinit var resultAdapter: ResultAdapter
    lateinit var userSubmitAnswer : CharArray
    var position = 0

    //Random logo
    var random = java.util.Random()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(bind.root)
        Utils.setLogoResponseList(baseContext)
        randomSet=generateRandomArray()
        setUpViewModel()
        initTimer()

    }


    private fun setUpViewModel(){
        viewModelQuestion.getRandomQuestion(randomSet).observe(this, Observer(::populateQuestionView))
    }
    private fun populateQuestionView(question: QuestionModel?){
        question?.let { q ->
            suggestList.clear()


            bind.ivQuestion.loadUrl(q.imgUrl)
            answer = q.name.toCharArray()
            for (item in answer) {
                //Add logo name to list
                suggestList.add(item.toString())
            }
            //Random add some character to list
            for (i in answer.size until answer.size * 2) suggestList.add(
                Utils.alphabet_character[random.nextInt(Utils.alphabet_character.size)]
            )
            suggestList.shuffle()
            userSubmitAnswer = CharArray(answer.size)
            position =0
            resultAdapter = ResultAdapter(q.name.length)
            bind.rvResult.adapter = resultAdapter
            suggestionAdapter = SuggestionAdapter(suggestList,q.name,this)
            bind.rvSuggestion.adapter = suggestionAdapter
        }
    }

    private fun initTimer(){
        timer = object : CountDownTimer(31000,1000){
            override fun onTick(millis: Long) {

                val seconds= millis / 1000
                bind.pbTimer.progress=(seconds.toInt() * 100) / 30
                when(seconds){
                    20L->bind.pbTimer.setIndicatorColor(ContextCompat.getColor(this@QuizActivity,R.color.green))
                    15L-> bind.pbTimer.setIndicatorColor(ContextCompat.getColor(this@QuizActivity,R.color.yellow))
                    8L-> bind.pbTimer.setIndicatorColor(ContextCompat.getColor(this@QuizActivity,R.color.red))
                }
                bind.tvTimer.text=seconds.toString()
            }

            override fun onFinish() {
                timer.cancel()
                nextQuestion()
            }

        }
        timer.start()
    }


    override fun onClickOfButton(suggestion: String) {
        userSubmitAnswer[position]= suggestion.first()
        resultAdapter.setData(suggestion)
        position++
    }
    private fun nextQuestion() {
        if(checkIfAnswerisCorrect()){
            score += 10
        }
        bind.score.text=score.toString()
        Handler(Looper.getMainLooper()).postDelayed(
            {
                if(numOfQuestion == Utils.questionsList.size){
                    Toast.makeText(applicationContext,"Logo Quiz Completed",Toast.LENGTH_SHORT).show()
                }
                else{
                    viewModelQuestion.getRandomQuestion(randomSet)
                    bind.pbTimer.setIndicatorColor(ContextCompat.getColor(this, R.color.white))
                    initTimer()
                    numOfQuestion++

                }
            },1000
        )
    }

    private fun checkIfAnswerisCorrect() : Boolean {
        return userSubmitAnswer contentEquals  answer
    }

    private fun generateRandomArray():HashSet<Int>{
        val size= Utils.questionsList.size
        val s= HashSet<Int>(size)
        while(s.size < size){
            s += Random.nextInt(0,Utils.questionsList.size)
        }
        return s
    }
}