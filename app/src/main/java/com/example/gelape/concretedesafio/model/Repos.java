package com.example.gelape.concretedesafio.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Repos implements Serializable
{
    //name, owner(login, avatar_url) , description, pulls_url(html_url ,title,user(login, avatar_url), body, created_at), stargazers_count, forks
    @SerializedName("name")
    private String name;
}
