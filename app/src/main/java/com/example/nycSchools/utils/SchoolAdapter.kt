package com.example.nycSchools.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nycSchools.databinding.SchoolItemBinding
import com.example.nycSchools.model.NYCSchool

//Adapter set up for Recycler view
class SchoolAdapter(
    private val schools: MutableList<NYCSchool> = mutableListOf(),
    private val setSchool: (NYCSchool) -> Unit
) : RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder>() {
    inner class SchoolViewHolder(
        private val binding: SchoolItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
            fun bind(nycSchool: NYCSchool) {
                binding.apply {
                    tvSchoolName.text = nycSchool.schoolName
                    tvSchoolEmail.text = nycSchool.schoolEmail
                    btnSearchDbn.setOnClickListener {
                        setSchool(nycSchool)
                    }
                }
            }
    }
    //function to get new data from the api
    //We clear the data and then we all the new data
    fun setSchoolsList(newList: List<NYCSchool>) {
        schools.clear()
        schools.addAll(newList)
        notifyItemRangeChanged(0, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder =
        SchoolViewHolder(
            SchoolItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        holder.bind(schools[position])
    }

    override fun getItemCount(): Int = schools.size
}