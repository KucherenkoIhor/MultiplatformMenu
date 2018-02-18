package com.mpp.multiplatformmenu

import arrow.effects.IO
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.gson.responseObject
import com.mpp.multiplatformmenu.data.Category
import com.mpp.multiplatformmenu.data.Item
import com.mpp.multiplatformmenu.data.SubCategory

/**
 * Created by ihor_kucherenko on 2/18/18.
 * https://github.com/KucherenkoIhor
 */

var host: String? = null
    set(value) {
        field = value
        FuelManager.instance.basePath = value
    }

fun getCategories(): IO<List<Category>> =
        IO.pure(Fuel.get("/categories").responseObject<List<Category>>().third.get())

fun getSubCategories(categoryId: Int): IO<List<SubCategory>> =
        IO.pure(Fuel.get("/subcategory/$categoryId").responseObject<List<SubCategory>>().third.get())

fun getItems(subCategoryId: Int): IO<List<Item>> =
        IO.pure(Fuel.get("/items/$subCategoryId").responseObject<List<Item>>().third.get())