package com.rsschool.quiz

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz.databinding.FragmentQuizBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        with(binding) {
            setContentView(root)

            viewPager.adapter = QuizAdapter()
            viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                }
            })
        }
    }

    inner class QuizAdapter : RecyclerView.Adapter<QuizViewHolder>() {
        override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
            TODO("Not yet implemented")
            holder.binding.question.text = "questions[position]"
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = QuizViewHolder(
            FragmentQuizBinding.inflate(layoutInflater, parent, false)
        )

        override fun getItemCount(): Int {
            TODO("Not yet implemented")
            //return quizQuestions.size
        }
    }

    inner class QuizViewHolder(val binding: FragmentQuizBinding) :
        RecyclerView.ViewHolder(binding.root)
}