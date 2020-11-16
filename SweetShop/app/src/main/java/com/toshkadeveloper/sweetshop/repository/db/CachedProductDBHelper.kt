package com.toshkadeveloper.sweetshop.repository.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase

import android.database.sqlite.SQLiteOpenHelper

class CachedProductDBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE " + TABLE_NAME + " ( " +
                    COLUMN_ID + " TEXT PRIMARY KEY, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_PRICE + " INTEGER, " +
                    COLUMN_CATEGORY + " TEXT, " +
                    COLUMN_CATEGORY_AND_PRICE + " TEXT, " +
                    COLUMN_PHOTO_URI + " BLOB, " +
                    COLUMN_UNIT + " TEXT, " +
                    COLUMN_PAGE + " INTEGER, " +
                    COLUMN_NUMBER + " INTEGER " +
                    ");"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) { }

    companion object {
        private const val DB_NAME = "SweetShop.db"
        private const val DB_VERSION = 1
        const val TABLE_NAME = "CachedProduct"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_PRICE = "price"
        const val COLUMN_CATEGORY = "category"
        const val COLUMN_CATEGORY_AND_PRICE = "category_and_price"
        const val COLUMN_PHOTO_URI = "photo_uri"
        const val COLUMN_UNIT = "unit"
        const val COLUMN_PAGE = "page"
        const val COLUMN_NUMBER = "number"

    }
}