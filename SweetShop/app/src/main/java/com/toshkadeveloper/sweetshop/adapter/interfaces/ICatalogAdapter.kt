package com.toshkadeveloper.sweetshop.adapter.interfaces

interface ICatalogAdapter {
    fun getItemCount(): Int
    fun updateAdapterData()
    fun getCatalog() : MutableList<Any>
    fun getItemViewType(position: Int): Int
    fun scrollToPosition(position: Int)
}