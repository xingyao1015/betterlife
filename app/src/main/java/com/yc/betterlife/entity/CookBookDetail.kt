package com.yc.betterlife.entity
import com.google.gson.annotations.SerializedName


data class CookBookDetail(
    @SerializedName("msg")
    var msg: String,
    @SerializedName("result")
    var result: CookBookDetailResult,
    @SerializedName("retCode")
    var retCode: Int
)

data class CookBookDetailResult(
    @SerializedName("ctgIds")
    var ctgIds: List<String>,
    @SerializedName("ctgTitles")
    var ctgTitles: String,
    @SerializedName("menuId")
    var menuId: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("recipe")
    var recipe: CookBookDetailRecipe,
    @SerializedName("thumbnail")
    var thumbnail: String
)

data class CookBookDetailRecipe(
    @SerializedName("img")
    var img: String,
    @SerializedName("ingredients")
    var ingredients: String,
    @SerializedName("method")
    var method: String,
    @SerializedName("sumary")
    var sumary: String,
    @SerializedName("title")
    var title: String
)


data class CookBookDetailRecipeMethod(
        @SerializedName("img")
        var img: String,
        @SerializedName("step")
        var step: String
)
