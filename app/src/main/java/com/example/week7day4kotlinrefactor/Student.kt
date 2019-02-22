package com.example.week7day4kotlinrefactor

import android.os.Parcel
import android.os.Parcelable

data class Student(
    var ssn: String?,
    var name: String?,
    var major: String?,
    var minor: String?,
    var gpa: String?,
    var dob: String?,
    var homeCity: String?,
    var homeState: String?)


//    constructor() {}
//
//    constructor(
//        SSN: String,
//        name: String,
//        major: String,
//        minor: String,
//        GPA: String,
//        DOB: String,
//        homeCity: String,
//        homeState: String
//    ) {
//        this.name = name
//        this.major = major
//        this.minor = minor
//        this.gpa = GPA
//        this.dob = DOB
//        this.homeCity = homeCity
//        this.homeState = homeState
//        this.ssn = SSN
//    }
//
//
//}
