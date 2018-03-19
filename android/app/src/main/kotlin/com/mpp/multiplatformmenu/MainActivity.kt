package com.mpp.multiplatformmenu

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class MainActivity : AppCompatActivity(), View.OnClickListener by OnItemClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ItemsAdapter(this)
        rv.adapter = adapter

        host = "http://192.168.0.101:8080"

        try {
            launch(CommonPool) {
                getCategories().flatMap {
                    getSubCategories(it.first().id).flatMap {
                        getItems(it.first().id)
                    }
                }.unsafeRunSync().let { items -> launch(UI) { adapter.dataSource = items } }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }


//        launch(CommonPool) {
//            getCategories().flatMap {
//                getSubCategories(it.first().id).flatMap {
//                    getItems(it.first().id)
//                }
//            }.unsafeRunAsync {
//                        it.fold({
//                            it.printStackTrace()
//                        }, {
//                            launch(UI) { adapter.dataSource = it }
//                        })
//                    }
//        }
//
//
//        try {
//            IO.monad().binding {
//                val categories = getCategories().bind()
//                val subcategories = getSubCategories(categories.first().id).bind()
//                val items = getItems(subcategories.first().id).bind()
//                items
//            }.ev().unsafeRunSync().let { items -> launch(UI) { adapter.dataSource = items } }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }

    }
}
