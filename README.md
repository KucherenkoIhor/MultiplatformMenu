# MultiplatformMenu

The simple example multiplatform project in Kotlin created using Android Studio. It contains the backend and android subprojects. 

## Backend
The backend subproject uses [Ktor](http://ktor.io) framework to build API.
It contains methods like this:
  * `/categories`
  Response:
  ```
 [
    {
      "id": 1,
      "name": "Menu"
    },
    {
      "id": 2,
      "name": "Drinks"
    },
    {
      "id": 3,
      "name": "Cocktails"
    }
]
```
## Android client
The Android client uses the [Fuel](https://github.com/kittinunf/Fuel) library for network requests and the [Arrow](http://arrow-kt.io) library which contains functional features such as typeclasses.

The example of Fuel usage:
```Kotlin
fun getCategories(): IO<List<Category>> =
        IO.pure(Fuel.get("/categories").responseObject<List<Category>>().third.get())
```

The example of usage the Monad from the Arrow library with coroutines.

```Kotlin
try {
   IO.monad().binding {
      val categories = getCategories().bind()
      val subcategories = getSubCategories(categories.first().id).bind()
      val items = getItems(subcategories.first().id).bind()
      items
   }.ev().unsafeRunSync().let { items -> launch(UI) { adapter.dataSource = items } }
} catch (e: Exception) {
   e.printStackTrace()
}

This example demonstrates how to create an async sequence of operations.

