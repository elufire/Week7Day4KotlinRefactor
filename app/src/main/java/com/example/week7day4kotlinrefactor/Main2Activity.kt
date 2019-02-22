package com.example.week7day4kotlinrefactor

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.studentDob
import kotlinx.android.synthetic.main.activity_main2.studentGpa
import kotlinx.android.synthetic.main.activity_main2.studentHomeCity
import kotlinx.android.synthetic.main.activity_main2.studentName
import kotlinx.android.synthetic.main.activity_main2.studentMajor
import kotlinx.android.synthetic.main.activity_main2.studentMinor
import kotlinx.android.synthetic.main.activity_main2.studentHomeState
import kotlinx.android.synthetic.main.activity_main2.studentSsn

class Main2Activity : AppCompatActivity() {

    internal var recyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)



    }

    fun onClick(view: View) {
        val intent: Intent
        val mySqlDatabaseHelper = MySqlDatabaseHelper(this)
        var student: Student? = Student(
            studentSsn.text.toString(), studentName.text.toString(),
            studentMajor.text.toString(),
            studentMinor.text.toString(), studentGpa.text.toString(),
            studentDob.text.toString(), studentHomeCity.text.toString(),
            studentHomeState.text.toString()
        )
        when (view.id) {
            R.id.addStudent -> {

                mySqlDatabaseHelper.insertStudent(student)
                Toast.makeText(baseContext, "Student Added SuccessfullY!", Toast.LENGTH_SHORT).show()
            }
            R.id.deleteStudent -> if (!student!!.ssn.isNullOrEmpty()) {
                mySqlDatabaseHelper.deleteStudent(student.ssn)
                Toast.makeText(baseContext, "Student Deleted Successfully!", Toast.LENGTH_SHORT).show()
            }
            R.id.goBack -> {
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.updateStudent -> if (!student!!.ssn.isNullOrEmpty()) {
                mySqlDatabaseHelper.updateStudent(student)
                Toast.makeText(baseContext, "Student Deleted Successfully!", Toast.LENGTH_SHORT).show()
            }
            R.id.getoneStudent -> if (!student!!.ssn.isNullOrEmpty()) {
                student = mySqlDatabaseHelper.getStudent(student.ssn)
                if (student != null) {
                    studentName.setText(student.name)
                    studentMajor.setText(student.major)
                    studentMinor.setText(student.minor)
                    studentGpa.setText(student.gpa)
                    studentDob.setText(student.dob)
                    studentHomeCity.setText(student.homeCity)
                    studentHomeState.setText(student.homeState)
                } else {
                    Toast.makeText(baseContext, "Student does not Exist!", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}
