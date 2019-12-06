package com.ezgroceries.shoppinglist.model

// Every input (request) / output (response) has to be its own class
data class ShoppingListCreationResponse (
        val shoppingListId: String,
        val name: String
)