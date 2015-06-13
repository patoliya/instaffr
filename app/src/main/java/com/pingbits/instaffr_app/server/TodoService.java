package com.pingbits.instaffr_app.server;


import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface TodoService {

    @GET("/todo/get/{name}")
    void getTodos(@Path("name") String name, Callback<List<TodoItem>> cb);

    @FormUrlEncoded
    @POST("/todo/add")
    void postTodo(@Field("name") String name, @Field("title") String title, @Field("addedBy") String addedBy, Callback<String> cb);
}
