package com.example.week7day4kotlinrefactor

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import java.util.ArrayList

class RecyclerViewAdapter(internal var studentsArrayList: ArrayList<Student>?) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): RecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerViewAdapter.ViewHolder, position: Int) {
        val student = studentsArrayList!![position]

        if (student != null) {
            val ssn = student.ssn
            val name = student.name
            val major = student.major
            val minor = student.minor
            val gpa = student.gpa
            val dob = student.dob
            val homeCity = student.homeCity
            val homeState = student.homeState


            viewHolder.ssn.text = ssn
            viewHolder.name.text = name
            viewHolder.major.text = major
            viewHolder.minor.text = minor
            viewHolder.gpa.text = gpa
            viewHolder.dob.text = dob
            viewHolder.homeCity.text = homeCity
            viewHolder.homeState.text = homeState
        }
    }

    override fun getItemCount(): Int {
        return if (studentsArrayList != null) studentsArrayList!!.size else 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var imgImage: ImageView
        internal var ssn: TextView
        internal var name: TextView
        internal var major: TextView
        internal var minor: TextView
        internal var gpa: TextView
        internal var dob: TextView
        internal var homeCity: TextView
        internal var homeState: TextView

        init {
            imgImage = itemView.findViewById(R.id.imgViewImage)
            ssn = itemView.findViewById(R.id.ssn)
            name = itemView.findViewById(R.id.name)
            major = itemView.findViewById(R.id.major)
            minor = itemView.findViewById(R.id.minor)
            gpa = itemView.findViewById(R.id.gpa)
            dob = itemView.findViewById(R.id.dob)
            homeCity = itemView.findViewById(R.id.homeCity)
            homeState = itemView.findViewById(R.id.homeState)

        }
    }
}
