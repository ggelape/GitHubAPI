package com.example.gelape.concretedesafio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.gelape.concretedesafio.adapter.GitAdapter;
import com.example.gelape.concretedesafio.model.RepoResponse;
import com.example.gelape.concretedesafio.model.Repos;
import com.example.gelape.concretedesafio.rest.ApiClient;
import com.example.gelape.concretedesafio.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = MainActivity.class.getSimpleName();
    private final String QUERYL = "language:Java";
    private final String SORTING = "stars";
    private final int CURPAGE = 1;
    private EndlessScrollListener scrollListener;
    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
    ListView listView;
    List<Repos> repos;
    GitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);

        Call<RepoResponse> call = apiService.getTopRepos(QUERYL, SORTING, CURPAGE);
        call.enqueue(new Callback<RepoResponse>()
        {

            @Override
            public void onResponse(Call<RepoResponse>call, Response<RepoResponse> response)
            {
                int statusCode = response.code();
                repos = response.body().getItems();
                adapter = new GitAdapter(getApplicationContext(), R.layout.list_item_repo, repos);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<RepoResponse>call, Throwable t)
            {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

        scrollListener = new EndlessScrollListener(5)
        {
            @Override
            public void onLoadMore(int page, int totalItemsCount)
            {
                loadMoreData(page);
            }
        };
        listView.setOnScrollListener(scrollListener);
    }

    public void loadMoreData(int page)
    {

        Call<RepoResponse> call = apiService.getTopRepos(QUERYL, SORTING, page);
        call.enqueue(new Callback<RepoResponse>()
        {

            @Override
            public void onResponse(Call<RepoResponse>call, Response<RepoResponse> response)
            {
                int statusCode = response.code();
                adapter.addAll(response.body().getItems());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<RepoResponse>call, Throwable t)
            {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}
