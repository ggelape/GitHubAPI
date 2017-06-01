package com.example.gelape.concretedesafio.rest;

import com.example.gelape.concretedesafio.model.RepoResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface
{

    @GET("search/repositories?")
    Call<RepoResponse> getTopRepos(@Query("q") String q, @Query("sort") String sorting, @Query("page") int pageNum);
}
