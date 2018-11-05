package com.yc.betterlife.http

import com.yc.betterlife.entity.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //微信精选分类
    @GET("wx/article/category/query")
    fun wechattabs(): Observable<WechatTabs>

    //微信精选分类列表
    @GET("wx/article/search")
    fun wechatSearch(@Query("cid") cid: Int,
                     @Query("page") page:Int,
                     @Query("size") size:Int): Observable<WechatItem>


    //菜谱分类
    @GET("v1/cook/category/query")
    fun cookTypes(): Observable<CookTypes>

    //根据ID查询菜谱
    @GET("v1/cook/menu/search")
    fun cookListByCid(@Query("cid") cid: String,
                     @Query("page") page:Int,
                     @Query("size") size:Int): Observable<CookList>

    //根据菜谱名查询列表
    @GET("v1/cook/menu/search")
    fun cookListByName(@Query("name") name: String,
                     @Query("page") page:Int,
                     @Query("size") size:Int): Observable<CookList>
    //查询菜谱详情
    @GET("v1/cook/menu/query")
    fun cookBookDetail(@Query("id") id: String): Observable<CookBookDetail>

}