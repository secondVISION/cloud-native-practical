package com.ezgroceries.shoppinglist.model

import com.fasterxml.jackson.annotation.JsonInclude

// Every input (request) / output (response) has to be its own class
data class ShoppingListResponse(
        val shoppingListId: String,
        val name: String,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        val ingredients: List<String>? = null
)