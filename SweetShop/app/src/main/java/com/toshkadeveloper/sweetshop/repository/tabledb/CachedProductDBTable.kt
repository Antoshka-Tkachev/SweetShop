package com.toshkadeveloper.sweetshop.repository.tabledb

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.repository.db.CachedProductDBHelper
import com.toshkadeveloper.sweetshop.repository.db.CachedProductDBHelper.Companion.COLUMN_CATEGORY
import com.toshkadeveloper.sweetshop.repository.db.CachedProductDBHelper.Companion.COLUMN_CATEGORY_AND_PRICE
import com.toshkadeveloper.sweetshop.repository.db.CachedProductDBHelper.Companion.COLUMN_ID
import com.toshkadeveloper.sweetshop.repository.db.CachedProductDBHelper.Companion.COLUMN_NAME
import com.toshkadeveloper.sweetshop.repository.db.CachedProductDBHelper.Companion.COLUMN_NUMBER
import com.toshkadeveloper.sweetshop.repository.db.CachedProductDBHelper.Companion.COLUMN_PAGE
import com.toshkadeveloper.sweetshop.repository.db.CachedProductDBHelper.Companion.COLUMN_PHOTO_URI
import com.toshkadeveloper.sweetshop.repository.db.CachedProductDBHelper.Companion.COLUMN_PRICE
import com.toshkadeveloper.sweetshop.repository.db.CachedProductDBHelper.Companion.COLUMN_UNIT
import com.toshkadeveloper.sweetshop.repository.db.CachedProductDBHelper.Companion.TABLE_NAME


class CachedProductDBTable(val context: Context) {
    private val dbHelper: CachedProductDBHelper = CachedProductDBHelper(context)
    private var database: SQLiteDatabase
    private var indexId: Int
    private var indexName: Int
    private var indexPrice: Int
    private var indexCategory: Int
    private var indexCategoryAndPrice: Int
    private var indexPhotoUri: Int
    private var indexUnit: Int
    private var indexPage: Int
    private var indexNumber: Int

    init {
        database = dbHelper.writableDatabase
        val cursor: Cursor = database.query(TABLE_NAME, null, null, null, null, null, null)
        indexId = cursor.getColumnIndex(COLUMN_ID)
        indexName = cursor.getColumnIndex(COLUMN_NAME)
        indexPrice = cursor.getColumnIndex(COLUMN_PRICE)
        indexCategory = cursor.getColumnIndex(COLUMN_CATEGORY)
        indexCategoryAndPrice = cursor.getColumnIndex(COLUMN_CATEGORY_AND_PRICE)
        indexPhotoUri = cursor.getColumnIndex(COLUMN_PHOTO_URI)
        indexUnit = cursor.getColumnIndex(COLUMN_UNIT)
        indexPage = cursor.getColumnIndex(COLUMN_PAGE)
        indexNumber = cursor.getColumnIndex(COLUMN_NUMBER)
        cursor.close()
        database.close()
    }

    fun cacheCatalogPage(products: MutableList<Product>, pageNumber: Int) {
        var cv: ContentValues
        database = dbHelper.writableDatabase

        database.beginTransaction();
        try {
            for (i in 0 until products.size) {
                cv = ContentValues()
                cv.put(COLUMN_ID, products[i].id)
                cv.put(COLUMN_NAME, products[i].name)
                cv.put(COLUMN_PRICE, products[i].price)
                cv.put(COLUMN_CATEGORY, products[i].category)
                cv.put(COLUMN_CATEGORY_AND_PRICE, products[i].categoryAndPrice)
                cv.put(COLUMN_PHOTO_URI, products[i].photoUri)
                cv.put(COLUMN_UNIT, products[i].unit)
                cv.put(COLUMN_PAGE, pageNumber)
                cv.put(COLUMN_NUMBER, i)
                database.insert(TABLE_NAME, null, cv)
            }
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
        database.close()
    }

    fun selectCachedCatalogPage(pageNumber: Int): MutableList<Product> {
        val products = mutableListOf<Product>()
        var bufferProduct: Product

        database = dbHelper.writableDatabase
        val cursor = database.rawQuery(
            "SELECT * FROM " + TABLE_NAME +
                    " WHERE " +
                    COLUMN_PAGE + " = ? " +
                    "ORDER BY " + COLUMN_NUMBER,
            arrayOf(pageNumber.toString()))
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            bufferProduct = Product(
                cursor.getString(indexId),
                cursor.getString(indexName),
                cursor.getInt(indexPrice),
                cursor.getString(indexPhotoUri),
                cursor.getString(indexCategory),
                cursor.getString(indexCategoryAndPrice),
                cursor.getString(indexUnit)
            )
            products.add(bufferProduct)
            cursor.moveToNext()
        }
        cursor.close()
        database.close()
        return products
    }

    fun clearTable() {
        database = dbHelper.writableDatabase
        database.delete(TABLE_NAME, null, null)
        database.close()
    }

}