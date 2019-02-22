package com.example.week7day4kotlinrefactor

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.rvMainRecyclerView

import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    internal var tvDisplay: TextView? = null
    var studentsArrayList: ArrayList<Student>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //tvDisplay = findViewById(R.id.tvDisplay);
        Log.d(TAG, "Before Database create!")
        val mySqlDatabaseHelper = MySqlDatabaseHelper(this)
        if(mySqlDatabaseHelper.allStudents.isNullOrEmpty()){
            val student = Student(
                "247", "l", "e", "t", "s",
                "g", "o", "!"
            )
            val studentTwo = Student(
                "600", "l", "e", "t", "s",
                "g", "o", "!"
            )
            mySqlDatabaseHelper.insertStudent(student)
            mySqlDatabaseHelper.insertStudent(studentTwo)
            val letsGo = mySqlDatabaseHelper.getStudent("247")
            Log.d(TAG, "Created: " + letsGo!!.name)
        }


        val rvAdapter = RecyclerViewAdapter(listOfStudents())
        val layoutManager = LinearLayoutManager(this)
        rvMainRecyclerView.layoutManager = layoutManager
        rvMainRecyclerView.adapter = rvAdapter

        //        }

    }//        tvDisplay.append(letsGo.getName());
    //        tvDisplay.append(letsGo.getMajor());
    //        tvDisplay.append(letsGo.getMinor());
    //letsGo.setSSN("600");
    //mySqlDatabaseHelper.updateStudent(letsGo);

    fun onClick(view: View) {
        when (view.id) {
            R.id.editTable -> {
                val intent = Intent(this, Main2Activity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun listOfStudents(): ArrayList<Student>? {
        val mySqlDatabaseHelper = MySqlDatabaseHelper(this)

        studentsArrayList = mySqlDatabaseHelper.allStudents
        return studentsArrayList
    }

    companion object {
        val TAG = "tag_one"
    }

}