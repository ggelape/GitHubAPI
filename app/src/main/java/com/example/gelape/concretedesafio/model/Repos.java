package com.example.gelape.concretedesafio.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Repos implements Serializable
{
    //name, owner(login, avatar_url) , description,
    //pulls_url(html_url ,title,user(login, avatar_url), body, created_at), stargazers_count, forks
    @SerializedName("name")
    private String name;

    @SerializedName("owner")
    private List<Owner> ownerInfo;

    @SerializedName("description")
    private String description;

    @SerializedName("pulls_url")
    private List<Pulls> pullsInfo;

    @SerializedName("stargazers_count")
    private int starsCount;

    @SerializedName("forks")
    private int forksCount;

    public Repos (String name, List<Owner> ownerInfo, String description, List<Pulls> pullsInfo, int starsCount, int forksCount)
    {
        this.name = name;
        this.ownerInfo = ownerInfo;
        this.description = description;
        this.pullsInfo = pullsInfo;
        this.starsCount = starsCount;
        this.forksCount = forksCount;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public List<Owner> getOwnerInfo()
    {
        return ownerInfo;
    }
    public void setOwnerInfo(List<Owner> ownerInfo)
    {
        this.ownerInfo = ownerInfo;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public List<Pulls> getPullsInfo()
    {
        return pullsInfo;
    }
    public void setPullsInfo(List<Pulls> pullsInfo)
    {
        this.pullsInfo = pullsInfo;
    }
    public int getStarsCount()
    {
        return starsCount;
    }
    public void setStarsCount(int starsCount)
    {
        this.starsCount = starsCount;
    }
    public int getForksCount()
    {
        return forksCount;
    }
    public void setForksCount(int forksCount)
    {
        this.forksCount = forksCount;
    }

}
