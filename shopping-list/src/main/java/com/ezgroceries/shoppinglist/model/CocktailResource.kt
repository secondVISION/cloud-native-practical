package com.ezgroceries.shoppinglist.model

import java.util.*

// Fields have to be public variables, otherwise Spring won't be able to serialize it
// (unless you add the setters/getters)
data class CocktailResource(
        val cocktailId: UUID,
        val name: String,
        val glass: String,
        val instructions: String,
        val image: String,
        val ingredients: List<String>
)
