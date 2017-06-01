package com.example.gelape.concretedesafio.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gelape.concretedesafio.R;
import com.example.gelape.concretedesafio.model.Owner;
import com.example.gelape.concretedesafio.model.Pulls;
import com.example.gelape.concretedesafio.model.Repos;
import com.example.gelape.concretedesafio.model.Users;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GitAdapter extends ArrayAdapter<Repos>
{
    //region Variable declaration
    private int rowLayout;
    private List<Repos> repos;
    private Owner owner;
    private List<Pulls> pulls;
    private List<Users> users;
    private Context context;
    String name;
    String ownerLogin;
    String ownerUrl;
    String description;
    String pullHtml;
    String pullTitle;
    String pullLogin;
    String pullUrl;
    String pullBody;
    String pullCreated;
    int starCount;
    int forkCount;
    //endregion

    public GitAdapter(Context context, int rowLayout, List<Repos> repos)
    {
        super(context, rowLayout, repos);
        this.repos = repos;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return super.getCount();
    }

    @Override
    public long getItemId(int position)
    {
        return super.getItemId(position);
    }

    //name, owner(login, avatar_url) , description,
    //pulls_url(html_url ,title,user(login, avatar_url), body, created_at), stargazers_count, forks
    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        //region View Holder Declarations and handling
        View convertView;
        final GitViewHolder holder;
        final Repos reposs = repos.get(position);
        if (view == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_repo, parent, false);
            holder = new GitViewHolder(convertView);
            convertView.setTag(holder);
        }
        else
        {
            convertView = view;
            holder = (GitViewHolder) convertView.getTag();
        }
        //endregion

        ownerUrl = reposs.getOwnerInfo().getAvatarUrl();
        Picasso.with(context).cancelRequest(holder.authorPhoto);
        Picasso.with(context).load(ownerUrl).into(holder.authorPhoto);
        holder.titleTextView.setText(repos.get(position).getName());
        holder.detailsTextView.setText(repos.get(position).getDescription());
        holder.usernameTextView.setText(reposs.getOwnerInfo().getLogin());
        holder.forkNumber.setText(repos.get(position).getForksCount());
        holder.starNumber.setText(repos.get(position).getStarsCount());
//        holder.cardView.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                movieTitleS = holder.movieTitle.getText().toString();
//                dataS = holder.data.getText().toString();
//                movieDescriptionS = holder.movieDescription.getText().toString();
//                ratingI = holder.rating.getText().toString();
//                posterUrl2 = "http://image.tmdb.org/t/p/w300/"+movies.get(position).getPosterPath();
//                Intent intent = new Intent(context.getApplicationContext(), MovieDetailsActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("posterUrl2",posterUrl2);
//                intent.putExtra("movieTitleS",movieTitleS);
//                intent.putExtra("dataS",dataS);
//                intent.putExtra("movieDescriptionS",movieDescriptionS);
//                intent.putExtra("ratingI",ratingI);
//                context.startActivity(intent);
//
//            }
//        });

        return convertView;

    }

    public class GitViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout repoLayout;
        TextView titleTextView;
        TextView detailsTextView;
        TextView usernameTextView;
        TextView forkNumber;
        TextView starNumber;
        ImageView authorPhoto;
        CardView cardView;

        public GitViewHolder(View v)
        {
            super(v);
            repoLayout = (LinearLayout) v.findViewById(R.id.repo_layout);
            authorPhoto = (ImageView) v.findViewById(R.id.authorPhoto);
            titleTextView = (TextView) v.findViewById(R.id.titleTextView);
            detailsTextView = (TextView) v.findViewById(R.id.detailsTextView);
            usernameTextView = (TextView) v.findViewById(R.id.usernameTextView);
            forkNumber = (TextView) v.findViewById(R.id.forkNumber);
            starNumber = (TextView) v.findViewById(R.id.starNumber);
            cardView = (CardView) v.findViewById(R.id.cardView);
        }


    }

}
