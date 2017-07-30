package com.chiragawale.insights;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.chiragawale.insights.adapter.UserAdapterRV;
import com.chiragawale.insights.entity.Users;
import com.chiragawale.insights.keys.GlobalVar;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements UserAdapterRV.UserAdapterOnClickHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list);
        //Getting data from the Intent for searching
        Intent intent = getIntent();
        Uri query = intent.getData();
        setTitle("Results: ");

        //Getting refrence of the progress bar
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        //Setting up the adapter

        List<Users> usersList = GlobalVar.userDao.getSearchResultList(query.toString());

        UserAdapterRV mUserAdapter = new UserAdapterRV(usersList.size(),usersList,this);

        RelativeLayout emptyView = (RelativeLayout) findViewById(R.id.empty_view);
        //Progress bar
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        //gets reference for the recycler view
        RecyclerView mUserList = (RecyclerView) findViewById(R.id.rv_user_list);

        //Defines the layout for the recycler view
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mUserList.setLayoutManager(layoutManager);

        //Improves performance
        mUserList.setHasFixedSize(true);
        //Sets the adapter for the Recycler view
        mUserList.setAdapter(mUserAdapter);
        //Kicks off the loader
        mUserList.setVisibility(View.GONE);

        if(usersList.size()!=0){
            emptyView.setVisibility(View.GONE);
            mUserList.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
    //Setting up the on click listeners
    @Override
    public void onClick(Uri uri) {
        Intent intent = new Intent (Intent.ACTION_VIEW,uri);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }
}
