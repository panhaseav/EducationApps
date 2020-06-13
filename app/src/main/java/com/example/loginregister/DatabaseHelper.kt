package com.example.loginregister

import android.content.ContentValues
import  android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, dbname, factory, version) {
    override fun onCreate(pO: SQLiteDatabase?) {

        pO?.execSQL(
            "create table user(" +
                    " id integer(10) primary key," +
                    "name varchar(30)," +
                    "email varchar(100)," +
                    "password varchar(20) )"
        )
        println("Database created")
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("drop table user;")
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun insertUserData(name: String, email: String, password: String) {
        val db: SQLiteDatabase = writableDatabase

        val values = contentValuesOf(
            "name" to name,
            "email" to email,
            "password" to password
        )

        db.insert("user", null, values)
        db.close()
    }

    fun userPresent(email: String, password: String): Boolean {
        val db = readableDatabase
        val query = ("select * from user where email = ? and password = ?")
//        val query=("select * from user")
        println("this is my sql query $query")
        val cursor = db.rawQuery(query, arrayOf(email, password))
        println("testing the cursor $cursor")
        if (cursor.count <= 0) {
            cursor.close()
            println("Didnt find a value")
            return false
        } else {
            println("You are wrong")
        }
        cursor.close()
        return true
    }


    companion object {
        internal val dbname = "userDB"
        internal val factory = null
        internal val version = 1
    }
}