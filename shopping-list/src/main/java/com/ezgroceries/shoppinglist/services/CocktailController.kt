package com.ezgroceries.shoppinglist.services

import com.ezgroceries.shoppinglist.model.CocktailResource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping(value= ["/cocktails"], produces = ["application/json"])
class CocktailController @Autowired constructor() {

    @GetMapping
    fun get(@RequestParam search: String): List<CocktailResource> {
        return getDummyResources()
    }

    private fun getDummyResources(): List<CocktailResource> {
        return listOf(
                CocktailResource(
                        UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4"),
                        "Margerita",
                        "Cocktail glass",
                        "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
                        "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",
                        listOf("Tequila", "Triple sec", "Lime juice", "Salt")),
                CocktailResource(
                        UUID.fromString("d615ec78-fe93-467b-8d26-5d26d8eab073"), "Blue Margerita",
                        "Cocktail glass",
                        "Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..",
                        "https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg",
                        listOf("Tequila", "Blue Curacao", "Lime juice", "Salt")))
    }
}
