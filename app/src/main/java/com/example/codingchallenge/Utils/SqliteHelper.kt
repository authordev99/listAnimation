package com.example.codingchallenge.Utils

import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import android.content.Context
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

    //using this method we can add users to user table
    fun addUser(user: UserLogin) {

        //get writable database
        val db = this.writableDatabase

        //create content values to insert
        val values = ContentValues()
        //Put email in  @values
        values.put(KEY_EMAIL, user.email)

        //Put password in  @values
        values.put(KEY_PASSWORD, user.password)

        // insert row
        val todo_id = db.insert(TABLE_USERS, null, values)
    }

    fun authenticate(user: UserLogin): UserLogin? {
        val db = this.readableDatabase
        val cursor = db.query(TABLE_USERS, // Selecting Table
                arrayOf(KEY_EMAIL, KEY_PASSWORD), //Selecting columns want to query
                "$KEY_EMAIL=?",
                arrayOf(user.email), null, null, null)//Where clause

        if (cursor != null && cursor.moveToFirst() && cursor.count > 0) {
            //if cursor has value then in user database there is user associated with this given email
            val user1 = UserLogin(cursor.getString(0), cursor.getString(1))

            //Match both passwords check they are same or not
            if (user.password.equals(user1.password,ignoreCase = true)) {
                return user1
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null
    }

    fun isEmailExists(email: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.query(TABLE_USERS, // Selecting Table
                arrayOf(KEY_EMAIL, KEY_PASSWORD), //Selecting columns want to query
                "$KEY_EMAIL=?",
                arrayOf(email), null, null, null)//Where clause

        return cursor != null && cursor.moveToFirst() && cursor.count > 0

        //if email does not exist return false
    }

    companion object {

        //DATABASE NAME
        val DATABASE_NAME = "login"

        //DATABASE VERSION
        val DATABASE_VERSION = 1

        //TABLE NAME
        val TABLE_USERS = "users"

        //COLUMN email
        val KEY_EMAIL = "email"

        //COLUMN password
        val KEY_PASSWORD = "password"

        //SQL for creating users table
        val SQL_TABLE_USERS = (" CREATE TABLE " + TABLE_USERS
                + " ( "
                + KEY_EMAIL + " TEXT, "
                + KEY_PASSWORD + " TEXT"
                + " ) ")
    }
}