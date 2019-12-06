package com.ezgroceries.shoppinglist

import com.ezgroceries.shoppinglist.model.CocktailDto
import com.ezgroceries.shoppinglist.model.ShoppingListRequest
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@SpringBootTest
@AutoConfigureMockMvc
@ComponentScan("com.ezgroceries.shoppinglist.service", "config:")
internal class ShoppingListApplicationTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    @Throws(Exception::class)
    fun getCocktails() {
        this.mockMvc
                .perform(get("/cocktails")
                        // RequestParams:
                        .param("search", "searchRequestParam")
                        .accept(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk)
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].cocktailId").exists())
                .andExpect(jsonPath("$[0].name").value("Margerita"))
                .andExpect(jsonPath("$[0].glass").value("Cocktail glass"))
                .andExpect(jsonPath("$[0].instructions").exists())
                .andExpect(jsonPath("$[0].image").exists())
                // A second item should exist as well
                .andExpect(jsonPath("$[1].cocktailId").exists())
    }

    @Test
    @Throws(Exception::class)
    fun postShoppingList() {
        val shoppingListRequest = ShoppingListRequest("requestName")
        this.mockMvc
                .perform(post("/shopping-lists")
                        // RequestBody: don't forget to set which type the body is!
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(convertToJson(shoppingListRequest))
                        .accept(MediaType.parseMediaType("application/json")))
                .andExpect(status().isCreated)
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("shoppingListId").exists())
                .andExpect(jsonPath("name").value("requestName"))
    }

    @Test
    @Throws(Exception::class)
    fun postAddCocktail() {
        val input = listOf(
                CocktailDto("id0"),
                CocktailDto("id1"),
                CocktailDto("id2")
        )
        this.mockMvc
                // Don't forget to include the base path of the controller: shopping-lists
                .perform(post("/shopping-lists/SHOPPING-LIST-ID-GOES-HERE/cocktails")
                        // RequestBody: don't forget to set which type the body is!
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(convertToJson(input))
                        .accept(MediaType.parseMediaType("application/json")))
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[1]").exists())
                .andExpect(jsonPath("$[2]").exists())
    }

    @Test
    @Throws(Exception::class)
    fun getShoppingList() {
        this.mockMvc
                .perform(get("/shopping-lists/SHOPPING-LIST-ID-GOES-HERE")
                        .accept(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk)
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("shoppingListId").exists())
                .andExpect(jsonPath("shoppingListId").value("uuid"))
                .andExpect(jsonPath("name").value("name"))
                .andExpect(jsonPath("ingredients").exists())
    }

    @Test
    @Throws(Exception::class)
    fun getAllShoppingLists() {
        this.mockMvc
                .perform(get("/shopping-lists")
                        .accept(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk)
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].shoppingListId").exists())
                .andExpect(jsonPath("$[0].shoppingListId").value("uuid"))
                .andExpect(jsonPath("$[0].name").value("name"))
                .andExpect(jsonPath("$[0].ingredients").exists())
                .andExpect(jsonPath("$[1].shoppingListId").exists())
                .andExpect(jsonPath("$[2].shoppingListId").exists())
    }

    private fun convertToJson(any: Any): String {
        val mapper = ObjectMapper()
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false)
        val ow = mapper.writer().withDefaultPrettyPrinter()
        return ow.writeValueAsString(any)

        // Or just:
        // return ObjectMapper().writeValueAsString(any)
    }

}
