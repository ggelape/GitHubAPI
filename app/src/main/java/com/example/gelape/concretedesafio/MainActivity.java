package com.example.gelape.concretedesafio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

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
    private final static String QUERYL = "language:Java";
    private final static String SORTING = "stars";
    private final static int CURPAGE = 1;
    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
    ListView listView;

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
                Log.i("URL", response.toString());
                final List<Repos> movies = response.body().getItems();
                GitAdapter adapter = new GitAdapter(getApplicationContext(), R.layout.list_item_repo, movies);
                listView.setAdapter(adapter);
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
