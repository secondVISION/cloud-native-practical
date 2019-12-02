package com.ezgroceries.shoppinglist.service

import com.ezgroceries.shoppinglist.model.ShoppingListRequest
import com.ezgroceries.shoppinglist.model.ShoppingListResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/shopping-lists"], produces = ["application/json"])
class ShoppingListController @Autowired constructor() {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)  // 201
    fun post(@RequestBody input: ShoppingListRequest): ShoppingListResponse {
        val uuid = "UUID-todo" // TODO
        return ShoppingListResponse(uuid, input.name)
    }

}