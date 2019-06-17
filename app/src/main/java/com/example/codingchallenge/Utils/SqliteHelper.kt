package com.example.codingchallenge.Utils

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.codingchallenge.Model.UserLogin


class SqliteHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        //Create Table when oncreate gets called
        sqLiteDatabase.execSQL(SQL_TABLE_USERS)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS $TABLE_USERS")
    }

    fun addUser(user: UserLogin) {

        //get writable database
        val db = this.writableDatabase

        //create content values to insert
        val values = ContentValues()

        //Put username in  @values
        values.put(KEY_USERNAME, user.username)

        //Put password in  @values
        values.put(KEY_PASSWORD, user.password)

        //Put email in  @values
        values.put(KEY_EMAIL, user.email)

        // insert row
        db.insert(TABLE_USERS, null, values)
    }

    fun authenticate(user: UserLogin): UserLogin? {
        val db = this.readableDatabase
        val cursor = db.query(TABLE_USERS, // Selecting Table
                arrayOf(KEY_USERNAME, KEY_PASSWORD, KEY_EMAIL), //Selecting columns want to query
                "$KEY_USERNAME=?",
                arrayOf(user.username), null, null, null)//Where clause
        if (cursor != null && cursor.moveToFirst() && cursor.count > 0) {
            //if cursor has value then in user database there is user associated with this given email
            val registeredUser = UserLogin(cursor.getString(0), cursor.getString(1),cursor.getString(2))
            println("registered username = ${registeredUser.username}")
            println("registered password = ${registeredUser.password}")
            //Match both passwords check they are same or not
            if (user.password.equals(registeredUser.password,ignoreCase = true)) {
                return registeredUser
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null
    }

    fun isEmailExists(email: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.query(TABLE_USERS, // Selecting Table
                arrayOf(KEY_USERNAME,KEY_PASSWORD,KEY_EMAIL), //Selecting columns want to query
                "$KEY_EMAIL=?",
                arrayOf(email), null, null, null)//Where clause

        return cursor != null && cursor.moveToFirst() && cursor.count > 0

        //if email does not exist return false
    }

    companion object {

        const val DATABASE_NAME = "challenge"
        const val DATABASE_VERSION = 1
        const val TABLE_USERS = "users"
        const val KEY_USERNAME = "username"
        const val KEY_EMAIL = "email"
        const val KEY_PASSWORD = "password"

        //SQL for creating users table
        const val SQL_TABLE_USERS = (" CREATE TABLE " + TABLE_USERS
                + " ( "
                + KEY_USERNAME + " TEXT, "
                + KEY_EMAIL + " TEXT, "
                + KEY_PASSWORD + " TEXT"
                + " ) ")
    }
}