package com.mpp.multiplatformmenu.data

import kotlinx.serialization.Serializable

/**
 * Created by ihor_kucherenko on 2/17/18.
 * https://github.com/KucherenkoIhor
 */
@Serializable
data class SubCategory(val categoryId: Int, val id: Int, val name: String)