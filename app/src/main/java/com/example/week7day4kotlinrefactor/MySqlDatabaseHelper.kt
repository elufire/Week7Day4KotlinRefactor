package com.example.week7day4kotlinrefactor

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import java.util.ArrayList

import com.example.week7day4kotlinrefactor.DatabaseConstraints.DATABASE_NAME
import com.example.week7day4kotlinrefactor.DatabaseConstraints.DATABASE_VERSION
import com.example.week7day4kotlinrefactor.DatabaseConstraints.FIELD_DOB
import com.example.week7day4kotlinrefactor.DatabaseConstraints.FIELD_GPA
import com.example.week7day4kotlinrefactor.DatabaseConstraints.FIELD_HOMECITY
import com.example.week7day4kotlinrefactor.DatabaseConstraints.FIELD_HOMESTATE
import com.example.week7day4kotlinrefactor.DatabaseConstraints.FIELD_MAJOR
import com.example.week7day4kotlinrefactor.DatabaseConstraints.FIELD_MINOR
import com.example.week7day4kotlinrefactor.DatabaseConstraints.FIELD_NAME
import com.example.week7day4kotlinrefactor.DatabaseConstraints.FIELD_SSN
import com.example.week7day4kotlinrefactor.DatabaseConstraints.TABLE_NAME


class MySqlDatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    val allStudents: ArrayList<Student>?
        get() {
            val sqLiteDatabase = readableDatabase
            val query = "SELECT * FROM $TABLE_NAME"
            val cursor = sqLiteDatabase.rawQuery(query, null)

            if (cursor.moveToFirst()) {
                val arrayList = ArrayList<Student>()
                do {
                    val ssn = cursor.getString(cursor.getColumnIndex(FIELD_SSN))
                    val name = cursor.getString(cursor.getColumnIndex(FIELD_NAME))
                    val major = cursor.getString(cursor.getColumnIndex(FIELD_MAJOR))
                    val minor = cursor.getString(cursor.getColumnIndex(FIELD_MINOR))
                    val gpa = cursor.getString(cursor.getColumnIndex(FIELD_GPA))
                    val dob = cursor.getString(cursor.getColumnIndex(FIELD_DOB))
                    val homeCity = cursor.getString(cursor.getColumnIndex(FIELD_HOMECITY))
                    val homeState = cursor.getString(cursor.getColumnIndex(FIELD_HOMESTATE))
                    arrayList.add(Student(ssn, name, major, minor, gpa, dob, homeCity, homeState))
                } while (cursor.moveToNext())
                return arrayList
            } else {
                return null
            }

        }

    override fun onCreate(db: SQLiteDatabase) {
        val createQuery = ("Create Table " + TABLE_NAME + "("
                + FIELD_SSN + " TEXT PRIMARY KEY, "
                + FIELD_NAME + " TEXT, "
                + FIELD_MAJOR + " TEXT, "
                + FIELD_MINOR + " TEXT, "
                + FIELD_GPA + " TEXT, "
                + FIELD_DOB + " TEXT, "
                + FIELD_HOMECITY + " TEXT, "
                + FIELD_HOMESTATE + " TEXT)")

        db.execSQL(createQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onCreate(db)
    }

    fun insertStudent(student: Student?) {
        val database = readableDatabase
        val contentValues = ContentValues()

        if (student != null) {
            contentValues.put(FIELD_SSN, student.ssn)
            contentValues.put(FIELD_NAME, student.name)
            contentValues.put(FIELD_MAJOR, student.major)
            contentValues.put(FIELD_MINOR, student.minor)
            contentValues.put(FIELD_GPA, student.gpa)
            contentValues.put(FIELD_DOB, student.dob)
            contentValues.put(FIELD_MAJOR, student.major)
            contentValues.put(FIELD_HOMECITY, student.homeCity)
            contentValues.put(FIELD_HOMESTATE, student.homeState)


            database.insert(TABLE_NAME, null, contentValues)
        }

    }

    fun getStudent(passedSsn: String?): Student? {
        var returnStudent: Student? = null
        if (passedSsn != null && !passedSsn.isEmpty()) {
            val sqLiteDatabase = readableDatabase
            val query = "SELECT * FROM " + TABLE_NAME + " WHERE " +
                    FIELD_SSN + " = \"" + passedSsn + "\""
            val cursor = sqLiteDatabase.rawQuery(query, null)
            if (cursor.moveToFirst()) {
                val ssn = cursor.getString(cursor.getColumnIndex(FIELD_SSN))
                val name = cursor.getString(cursor.getColumnIndex(FIELD_NAME))
                val major = cursor.getString(cursor.getColumnIndex(FIELD_MAJOR))
                val minor = cursor.getString(cursor.getColumnIndex(FIELD_MINOR))
                val gpa = cursor.getString(cursor.getColumnIndex(FIELD_GPA))
                val dob = cursor.getString(cursor.getColumnIndex(FIELD_DOB))
                val homeCity = cursor.getString(cursor.getColumnIndex(FIELD_HOMECITY))
                val homeState = cursor.getString(cursor.getColumnIndex(FIELD_HOMESTATE))
                returnStudent = Student(ssn, name, major, minor, gpa, dob, homeCity, homeState)
            }
            cursor.close()
        }

        return returnStudent
    }

    fun deleteStudent(passedSsn: String?): Int {
        val whereClause = "$FIELD_SSN =\"$passedSsn\""
        val sqLiteDatabase = writableDatabase
        return sqLiteDatabase.delete(TABLE_NAME, whereClause, null)
    }

    fun updateStudent(student: Student?): Int {
        if (student != null) {
            val whereClause = FIELD_SSN + " = \"" + student.ssn + "\""
            val sqLiteDatabase = writableDatabase
            val contentValues = ContentValues()
            contentValues.put(FIELD_SSN, student.ssn)
            contentValues.put(FIELD_NAME, student.name)
            contentValues.put(FIELD_MAJOR, student.major)
            contentValues.put(FIELD_MINOR, student.minor)
            contentValues.put(FIELD_GPA, student.gpa)
            contentValues.put(FIELD_DOB, student.dob)
            contentValues.put(FIELD_HOMECITY, student.homeCity)
            contentValues.put(FIELD_HOMESTATE, student.homeState)
            return sqLiteDatabase.update(TABLE_NAME, contentValues, whereClause, null)
        }
        return 0
    }

}