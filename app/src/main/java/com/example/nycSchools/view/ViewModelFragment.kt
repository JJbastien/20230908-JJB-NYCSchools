package com.example.nycSchools.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.nycSchools.viewmodel.SchoolViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class ViewModelFragment: Fragment() {
    protected val viewModel: SchoolViewModel by activityViewModels()
}