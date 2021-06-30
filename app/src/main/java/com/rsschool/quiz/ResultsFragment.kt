package com.rsschool.quiz

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rsschool.quiz.databinding.FragmentResultsBinding
import kotlin.system.exitProcess

class ResultsFragment : Fragment() {
    private var _binding: FragmentResultsBinding? = null
    private val binding: FragmentResultsBinding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultsBinding.inflate(inflater, container, false)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_resultsFragment2_to_pagerFragment3)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userAnswers = arguments?.getIntArray("userResult")
        val userResult = correctAnswers(userAnswers)

        userAnswers?.let {
            with(binding) {
                result.text = getString(R.string.result, userResult)
                revert.setOnClickListener { findNavController().navigate(R.id.action_resultsFragment2_to_pagerFragment3) }
                close.setOnClickListener { exitProcess(0) }
                share.setOnClickListener {
                    val sharingResultText = """                You result is $userResult / 5
                    | ${QuizQuestions.One.title}: ${QuizQuestions.One.question}
                    | You answer : ${QuizQuestions.One.answers[userAnswers[0]]}
                    | ${QuizQuestions.Two.title}: ${QuizQuestions.Two.question}
                    | You answer: ${QuizQuestions.Two.answers[userAnswers[1]]}
                    | ${QuizQuestions.Three.title}: ${QuizQuestions.Three.question}
                    | You answer: ${QuizQuestions.Three.answers[userAnswers[2]]}
                    | ${QuizQuestions.Four.title}: ${QuizQuestions.Four.question}
                    | You answer: ${QuizQuestions.Four.answers[userAnswers[3]]}
                    | ${QuizQuestions.Five.title}: ${QuizQuestions.Five.question}
                    | You answer: ${QuizQuestions.Five.answers[userAnswers[4]]}
                """.trimMargin()
                    val intent = Intent(Intent.ACTION_SEND)
                    intent.type = "text/plain"
                    intent.putExtra(Intent.EXTRA_TEXT, sharingResultText)
                    startActivity(intent)
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun correctAnswers(userAnswers: IntArray?): Int {
        var correct = 0
        userAnswers?.let {
            for (index in 0..4) {
                when (index) {
                    0 -> if (userAnswers[index] == QuizQuestions.One.indexOfTrueAnswer) ++correct
                    1 -> if (userAnswers[index] == QuizQuestions.Two.indexOfTrueAnswer) ++correct
                    2 -> if (userAnswers[index] == QuizQuestions.Three.indexOfTrueAnswer) ++correct
                    3 -> if (userAnswers[index] == QuizQuestions.Four.indexOfTrueAnswer) ++correct
                    4 -> if (userAnswers[index] == QuizQuestions.Five.indexOfTrueAnswer) ++correct
                }
            }
        }
        return correct
    }
}