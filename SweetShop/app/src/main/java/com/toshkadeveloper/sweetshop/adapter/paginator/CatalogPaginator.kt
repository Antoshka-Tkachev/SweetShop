package com.toshkadeveloper.sweetshop.adapter.paginator

import com.toshkadeveloper.sweetshop.adapter.interfaces.ICatalogAdapter
import com.toshkadeveloper.sweetshop.logic.callback.ICatalogPaginatorCallback
import com.toshkadeveloper.sweetshop.repository.firebasedb.ProductDBTable
import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.mvp.basecatalog.IBaseCatalogContract
import com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces.IProductDBTable
import com.toshkadeveloper.sweetshop.logic.data.ProductCategory

class CatalogPaginator(
    private val catalogAdapter: ICatalogAdapter,
    private val viewContract: IBaseCatalogContract.View,
    private val presenterContract: IBaseCatalogContract.Presenter
) : ICatalogPaginatorCallback {
    private val productDBTable: IProductDBTable = ProductDBTable()

    private var endLastPagePosition: Int = -1 // находимся перед 0 позицией
    private var productLastInformation: String = ""
    private var isCatalogLoaded: Boolean = false

    init {
        firstPageRequest()
    }

    private fun firstPageRequest() {
        viewContract.showCatalogLoader(true)
        productDBTable.getProductFirstPage(2 * PAGE_SIZE, this)
    }

    override fun getProductLastInformation(): String {
        return productLastInformation
    }

    override fun pageRequest() {
        if (isCatalogLoaded) return
        /**добавить нижним холделом лоадер и показать его*/
        productDBTable.getProductPage(productLastInformation, PAGE_SIZE, this)
    }

    private fun addCategory(position: Int) {
        getCatalog().add(position,
            ProductCategory((getCatalog()[position] as Product).category)
        )
    }

    private fun checkIsLastPage(pageSize: Int) {
        if (pageSize < PAGE_SIZE) isCatalogLoaded = true
    }

    private fun addCategories() {
        var position: Int  = endLastPagePosition
        while (position != getCatalog().size - 1) {
            if (getCatalog()[position] is Product &&
                getCatalog()[position + 1] is Product &&
                (getCatalog()[position] as Product).category != (getCatalog()[position + 1] as Product).category
            ) {
                addCategory(position + 1)
            }
            position++
        }
        endLastPagePosition = getCatalog().size - 1
    }

    private fun getCatalog(): MutableList<Any> {
        return catalogAdapter.getCatalog()
    }

    override fun productPageRequestCompleted(products: MutableList<Product>, _productLastInformation: String, count: Int) {
        /**Выполнять весь метод в отельном потоке*/
        productLastInformation = _productLastInformation
        checkIsLastPage(count)
        getCatalog().addAll(products)
        addCategories()
        catalogAdapter.updateAdapterData()
        /**скрыть нижний холдер с лоадером если он видимый*/
    }

    override fun scrollToCategory(products: MutableList<Product>, _productLastInformation: String, count: Int, category: ProductCategory) {
        productPageRequestCompleted(products, _productLastInformation, count)
        catalogAdapter.scrollToPosition(getCatalog().indexOf(category))
    }

    override fun productPageRequestCancelled() {
        /**скрыть нижний холдер с лоадером
         * Отменить загрузку
         * Показать нижний холдер с подписью "проблема с подключением" с кнопкой "загрузить еще", при нажатии на кнопку {
         * pageRequest();
         * скрыть нижний холдер с подписью "проблема с подключением" с кнопкой "загрузить еще"
         * } */
    }

    override fun productFirstPageRequestCompleted(products: MutableList<Product>, _productLastInformation: String, count: Int) {
        /**Выполнять весь метод в отельном потоке*/
        if (count == 0) {
            isCatalogLoaded = true
            viewContract.showEmptyCatalog();
            /**Вывести каталог пуст
             * обьеденить с методом productFirstPageRequestCancelled()*/
            return
        }
        productLastInformation = _productLastInformation
        endLastPagePosition = 0
        checkIsLastPage(count)
        getCatalog().addAll(products)
        addCategory(0)
        addCategories()
        catalogAdapter.updateAdapterData()
        presenterContract.onFirstPageProductsLoaded(true)
        presenterContract.setCatalogAndCategoriesData()
    }

    override fun productFirstPageRequestCancelled() {
        viewContract.showCatalogLoader(false)
        /**Вывести диалоговое окно "что-то пошло не так" с кнопкой "попробовать еще раз"
         * (передавать в метод колбек, чтоб при нажатии на кнопку снова запустился данный метод firstPageRequest())
         * обьеденить с методом productFirstPageRequestCancelled()*/
    }

    companion object {
         const val PAGE_SIZE: Int = 30 //должна быть больше, чем помещается holder'ов на экране
    }
}