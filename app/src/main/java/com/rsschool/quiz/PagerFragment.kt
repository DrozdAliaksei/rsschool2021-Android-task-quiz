package com.rsschool.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.rsschool.quiz.databinding.FragmentPagerBinding
import com.rsschool.quiz.databinding.FragmentQuizBinding
import kotlin.system.exitProcess

class PagerFragment : Fragment() {

    private var userAnswers = mutableListOf(-1, -1, -1, -1, -1)
    private var _binding: FragmentPagerBinding? = null
    private val binding: FragmentPagerBinding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPagerBinding.inflate(inflater, container, false)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val currentIndex = binding.viewPager.currentItem
                if (currentIndex > 0) binding.viewPager.currentItem = currentIndex - 1
                else exitProcess(0)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    inner class QuizAdapter(private val viewPager2: ViewPager2) :
        RecyclerView.Adapter<QuizViewHolder>() {
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
                    holder.binding.nextButton.text = getString(R.string.SubmitButton)
                }
            }
            holder.binding.previousButton.setOnClickListener {
                viewPager2.currentItem = position - 1
            }
            holder.binding.toolbar.setNavigationOnClickListener {
                viewPager2.currentItem = position - 1
            }
            holder.binding.radioGroup.setOnCheckedChangeListener { group, i ->
                if (group.isEnabled) {
                    holder.binding.nextButton.isEnabled = true
                    holder.binding.nextButton.setOnClickListener {
                        if (position != 4) viewPager2.currentItem = position + 1
                        else {
                            val bundle = bundleOf("userResult" to userAnswers.toIntArray())
                            findNavController().navigate(
                                R.id.action_pagerFragment3_to_resultsFragment2,
                                bundle
                            )
                        }
                    }
                    when (i) {
                        holder.binding.optionOne.id -> userAnswers[position] = 0
                        holder.binding.optionTwo.id -> userAnswers[position] = 1
                        holder.binding.optionThree.id -> userAnswers[position] = 2
                        holder.binding.optionFour.id -> userAnswers[position] = 3
                        holder.binding.optionFive.id -> userAnswers[position] = 4
                    }
                }
            }
        }

        private fun setData(holder: QuizViewHolder, quiz: QuizQuestions) {
            with(holder.binding) {
                requireContext().theme.applyStyle(quiz.theme, true)
                toolbar.title = quiz.title
                question.text = quiz.question
                optionOne.text = quiz.answers[0]
                optionTwo.text = quiz.answers[1]
                optionThree.text = quiz.answers[2]
                optionFour.text = quiz.answers[3]
                optionFive.text = quiz.answers[4]
                nextButton.isEnabled = false
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