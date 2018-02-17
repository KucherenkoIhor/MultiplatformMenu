package com.mpp.multiplatformmenu

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import arrow.effects.IO
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.gson.responseObject
import com.mpp.multiplatformmenu.data.Category
import com.mpp.multiplatformmenu.data.Item
import com.mpp.multiplatformmenu.data.SubCategory
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ItemsAdapter()
        findViewById<RecyclerView>(R.id.rv).adapter = adapter

        FuelManager.instance.basePath = "http://192.168.0.101:8080"

        launch(CommonPool) {
            getCategories().flatMap {
                getSubCategories(it.first().id).flatMap {
                    getItems(it.first().id)
                }
            }.unsafeRunSync().let { items ->
                launch(UI) {
                    adapter.apply {
                        dataSource = items
                    }
                }
            }
        }
    }

    private fun getCategories(): IO<List<Category>> =
            IO.pure(Fuel.get("/categories").responseObject<List<Category>>().third.get())

    private fun getSubCategories(categoryId: Int): IO<List<SubCategory>> =
            IO.pure(Fuel.get("/subcategory/$categoryId").responseObject<List<SubCategory>>().third.get())

    private fun getItems(subCategoryId: Int): IO<List<Item>> =
            IO.pure(Fuel.get("/items/$subCategoryId").responseObject<List<Item>>().third.get())

}
