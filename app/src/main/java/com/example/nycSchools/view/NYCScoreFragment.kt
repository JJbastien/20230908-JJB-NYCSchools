package com.example.nycSchools.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nycSchools.R
import com.example.nycSchools.databinding.FragmentNycscoreBinding
import com.example.nycSchools.model.NYCScore
import com.example.nycSchools.utils.UIState

class NYCScoreFragment: ViewModelFragment() {

    private var _binding: FragmentNycscoreBinding? = null
    private val binding: FragmentNycscoreBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNycscoreBinding.inflate(layoutInflater)

        configureObserver()
        return binding.root
    }
    //  fragment for school SAT using  liveData to observe life cycle and use State to
    // update UI
    private fun configureObserver() {
        viewModel.scoreLiveData.observe(viewLifecycleOwner) { state ->
            val school = viewModel.currentSchool
            when(state) {
                is UIState.Success<*> -> {
                    val score: NYCScore? = (state.response as List<NYCScore>).firstOrNull()
                    if (score == null) {
                        binding.apply {
                            pbScoreLoading.visibility = View.GONE
                            satScoreLoading.visibility = View.GONE
                        }
                    } else {
                        binding.apply {
                            pbScoreLoading.visibility = View.GONE
                            satScoreLoading.visibility   = View.GONE
                            satScoreTakers.text = resources.getString(R.string.score_takers, score.numOfSatTestTakers)
                            satScoreMath.text = resources.getString(R.string.score_math, score.satMathAvgScore)
                            satScoreReading.text = resources.getString(R.string.score_reading, score.satCriticalReadingAvgScore)
                            satScoreWriting.text = resources.getString(R.string.score_writing, score.satWritingAvgScore)
                            satScores.visibility = View.VISIBLE
                        }
                    }
                    binding.apply {
                        satScoreSchoolName.text = school?.schoolName
                        satScoreSchoolAddress.text = resources.getString(R.string.score_address, school?.primaryAddressLine1)
                        satScoreSchoolEmail.text = resources.getString(R.string.score_email, school?.schoolEmail)
                        satScoreStudents.text = resources.getString(R.string.score_students, school?.totalStudents)
                        schoolOverview.text = resources.getString(R.string.score_overview,school?.overviewParagraph)
                    }
                }
                is UIState.Error -> {
                    binding.apply {
                        pbScoreLoading.visibility = View.GONE
                        satScoreLoading.text = state.exception.message
                    }
                }
                is UIState.Loading -> {
                    viewModel.fetchNYCScore(viewModel.currentSchool?.dbn ?: "")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}