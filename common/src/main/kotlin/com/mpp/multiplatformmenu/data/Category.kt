package com.mpp.multiplatformmenu.data

import kotlinx.serialization.Serializable

/**
 * Created by ihor_kucherenko on 2/17/18.
 * https://github.com/KucherenkoIhor
 */
@Serializable
data class Category(val id: Int = 0, val name: String)