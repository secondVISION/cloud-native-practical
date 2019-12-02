package com.ezgroceries.shoppinglist.model

data class ShoppingListResponse (
        val shoppingListId: String,
        val name: String,
        val ingredients: List<String>
)