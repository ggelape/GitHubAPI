package com.example.gelape.concretedesafio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gelape.concretedesafio.adapter.GitAdapter;
import com.example.gelape.concretedesafio.model.RepoResponse;
import com.example.gelape.concretedesafio.model.Repos;
import com.example.gelape.concretedesafio.rest.ApiClient;
import com.example.gelape.concretedesafio.rest.ApiInterface;

import java.util.HashMap;
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);

        scrollListener = new EndlessScrollListener(5)
        {
            @Override
            public void onLoadMore(int page, int totalItemsCount)
            {
                loadMoreData(page);
            }
        };
        listView.setOnScrollListener(scrollListener);
        loadMoreData(CURPAGE);
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
                Log.i("URL", response.toString());
                final List<Repos> repos = response.body().getItems();
                GitAdapter adapter = new GitAdapter(getApplicationContext(), R.layout.list_item_repo, repos);
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
    }
}
