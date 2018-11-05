package com.yc.betterlife.entity

import com.chad.library.adapter.base.entity.AbstractExpandableItem
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.google.gson.annotations.SerializedName
import com.yc.betterlife.adapter.CookTypeAdapter


data class CookTypes(
        @SerializedName("msg")
        var msg: String,
        @SerializedName("result")
        var result: CookTypesResult,
        @SerializedName("retCode")
        var retCode: Int
)

data class CookTypesResultCategoryInfo(
        @SerializedName("ctgId")
        var ctgId: String,
        @SerializedName("name")
        var name: String
)

data class CookTypesResult(
        @SerializedName("categoryInfo")
        var categoryInfo: CookTypesResultCategoryInfo,
        @SerializedName("childs")
        var childs: List<CookTypesChild>
)

data class CookTypesChild(@SerializedName("categoryInfo")
                          var categoryInfo: CookTypesChildCategoryInfo,
                          @SerializedName("childs")
                          var childs: List<CookTypesChildChild>) : AbstractExpandableItem<CookTypesChildChild>(), MultiItemEntity {
    override fun getLevel(): Int {
        return 0
    }

    override fun getItemType(): Int {
        return CookTypeAdapter.TYPE_LEVEL_0;
    }

}

data class CookTypesChildChild(
        @SerializedName("categoryInfo")
        var categoryInfo: CookTypesChildCategoryInfo) : MultiItemEntity {
    override fun getItemType(): Int {
        return CookTypeAdapter.TYPE_LEVEL_1
    }

}

data class CookTypesChildCategoryInfo(
        @SerializedName("ctgId")
        var ctgId: String,
        @SerializedName("name")
        var name: String,
        @SerializedName("parentId")
        var parentId: String
)

