package com.yc.betterlife.entity

import com.google.gson.annotations.SerializedName


data class CookList(
        @SerializedName("msg")
        var msg: String,
        @SerializedName("result")
        var result: CookListResult,
        @SerializedName("retCode")
        var retCode: Int
)


data class CookListResult(
        @SerializedName("curPage")
        var curPage: Int,
        @SerializedName("list")
        var list: ArrayList<CookListListBean>? = null,
        @SerializedName("total")
        var total: Int
)

data class CookListListBean(
        @SerializedName("ctgIds")
        var ctgIds: List<String>,
        @SerializedName("ctgTitles")
        var ctgTitles: String,
        @SerializedName("menuId")
        var menuId: String,
        @SerializedName("name")
        var name: String,
        @SerializedName("recipe")
        var recipe: CookListListRecipe,
        @SerializedName("thumbnail")
        var thumbnail: String
)

data class CookListListRecipe(
        @SerializedName("method")
        var method: String? = null,
        @SerializedName("sumary")
        var sumary: String,
        @SerializedName("title")
        var title: String,
        @SerializedName("img")
        var img: String? = null,
        @SerializedName("ingredients")
        var ingredients: String? = null
)