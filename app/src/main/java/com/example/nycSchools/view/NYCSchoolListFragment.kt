package com.example.nycSchools.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nycSchools.R
import com.example.nycSchools.databinding.FragmentNycschoolListBinding
import com.example.nycSchools.model.NYCSchool
import com.example.nycSchools.utils.UIState

// Fragment to display the SAT scores
class NYCSchoolListFragment : ViewModelFragment() {

    private var _binding: FragmentNycschoolListBinding? = null
    private val binding: FragmentNycschoolListBinding get() = _binding!!

    private lateinit var schoolAdapter: SchoolAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNycschoolListBinding.inflate(layoutInflater)

        configureObserver()
        return binding.root
    }
    //  fragment for school list using liveData to observe life cycle and use State to
    // update UI
    private fun configureObserver() {
        viewModel.schoolLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.Success<*> -> {
                    binding.apply {
                        pbLoading.visibility = View.GONE
                        tvSchoolErrorLoadingText.visibility = View.GONE
                        schoolAdapter = SchoolAdapter(setSchool = ::setSchool)
                        schoolAdapter.setSchoolsList(state.response as List<NYCSchool>)
                        rvSchoolList.adapter = schoolAdapter
                    }
                }
                is UIState.Error -> {
                    binding.apply {
                        pbLoading.visibility = View.GONE
                        tvSchoolErrorLoadingText.text = state.exception.message
                    }
                }
                else -> {}
            }
        }
    }
    //function to set data received to fragment attached to the activity containing the fragment in question
    private fun setSchool(nycSchool: NYCSchool) {
        viewModel.setSchool(nycSchool)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, NYCScoreFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}