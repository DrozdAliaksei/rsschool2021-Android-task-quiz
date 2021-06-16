package com.rsschool.quiz

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz.databinding.FragmentQuizBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userAnswers: Array<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        with(binding) {
            setContentView(root)
            viewPager.adapter = QuizAdapter(viewPager)
            viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    viewPager.isUserInputEnabled = false
                }
            })
        }
    }

    inner class QuizAdapter(val viewPager2: ViewPager2) : RecyclerView.Adapter<QuizViewHolder>() {
        override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
            when (position) {
                0 -> {
                    setData(holder, QuizQuestions.One)
                    holder.binding.apply {
                        previousButton.isVisible = false
                        toolbar.navigationIcon = null
                    }
                }
                1 -> setData(holder, QuizQuestions.Two)
                2 -> setData(holder, QuizQuestions.Three)
                3 -> setData(holder, QuizQuestions.Four)
                4 -> {
                    setData(holder, QuizQuestions.Five)
                    holder.binding.nextButton.text = "Submit"
                }
            }
            holder.binding.previousButton.setOnClickListener {
                viewPager2.currentItem = position - 1
            }
            holder.binding.nextButton.setOnClickListener {
                if (position != 4) viewPager2.currentItem = position + 1
                //else open new activity with result
            }

            holder.binding.radioGroup.setOnCheckedChangeListener { group, i ->

            }
        }

        private fun setData(holder: QuizViewHolder, quiz: QuizQuestions) {
            with(holder.binding) {
                toolbar.title = quiz.title
                question.text = quiz.question
                optionOne.text = quiz.answers[0]
                optionTwo.text = quiz.answers[1]
                optionThree.text = quiz.answers[2]
                optionFour.text = quiz.answers[3]
                optionFive.text = quiz.answers[4]
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = QuizViewHolder(
            FragmentQuizBinding.inflate(layoutInflater, parent, false)
        )

        override fun getItemCount(): Int {
            return 5
        }
    }

    inner class QuizViewHolder(val binding: FragmentQuizBinding) :
        RecyclerView.ViewHolder(binding.root)
}