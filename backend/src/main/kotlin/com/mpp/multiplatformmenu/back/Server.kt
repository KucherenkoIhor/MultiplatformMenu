package com.mpp.multiplatformmenu.back

/**
 * Created by ihor_kucherenko on 2/17/18.
 * https://github.com/KucherenkoIhor
 */
import com.google.gson.GsonBuilder
import com.mpp.multiplatformmenu.data.Category
import com.mpp.multiplatformmenu.data.Item
import com.mpp.multiplatformmenu.data.SubCategory
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

val gson = GsonBuilder().setPrettyPrinting().serializeNulls().create()

fun main(args: Array<String>) {

    val server = embeddedServer(Netty, 8080) {
        routing {
            get("/categories") {

                call.respond(
                        gson.toJson(
                            listOf(
                                    Category(1, "Menu"),
                                    Category(2, "Drinks"),
                                    Category(3, "Cocktails")
                            )
                        )
                )
            }
            get("/subcategory/{categoryId}") {
                call.respond(
                        gson.toJson(
                            listOf(
                                    SubCategory(1, 1, "Salads"),
                                    SubCategory(1, 2, "Fish And Seafood"),
                                    SubCategory(1, 3, "Soups")
                            )
                        )
                )
            }
            get("/items/{subCategoryId}")    {
                call.respond(
                        gson.toJson(
                            listOf(
                                    Item(1, 1, 1, "Overseas herring caviar"),
                                    Item(1, 1, 2, "Salted anchovy with baked potatoes and homemade butter"),
                                    Item(1, 1, 3, "Fried Anchovy with Georgian sauce"),
                                    Item(1, 1, 4, "Forshmak in a new way")
                            )
                        )
                )
            }
        }
    }
    server.start(wait = true)
}