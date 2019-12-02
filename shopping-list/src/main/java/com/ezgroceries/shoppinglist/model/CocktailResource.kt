package com.ezgroceries.shoppinglist.model

import java.util.*

data class CocktailResource(
        val cocktailId: UUID,
        val name: String,
        val glass: String,
        val instructions: String,
        val image: String,
        val ingredients: List<String>
)
