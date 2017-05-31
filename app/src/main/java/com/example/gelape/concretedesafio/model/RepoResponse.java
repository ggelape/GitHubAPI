package com.example.gelape.concretedesafio.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RepoResponse
{
    @SerializedName("total_count")
    private int totalCount;
    @SerializedName("incomplete_results")
    private boolean incompleteTorF;
    @SerializedName("items")
    private List<Repos> items;

    public int getTotalCount()
    {
        return totalCount;
    }
    public void setTotalCount(int totalCount)
    {
        this.totalCount = totalCount;
    }
    public List<Repos> getItems()
    {
        return items;
    }
    public void setItems(List<Repos> items)
    {
        this.items = items;
    }
}
