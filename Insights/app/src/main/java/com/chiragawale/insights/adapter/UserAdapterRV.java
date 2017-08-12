package com.chiragawale.insights.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chiragawale.insights.R;
import com.chiragawale.insights.databinding.ListItemBinding;
import com.chiragawale.insights.entity.Users;

import java.util.List;

/**
 * Created by chira on 7/28/2017.
 */

public class UserAdapterRV extends RecyclerView.Adapter<UserAdapterRV.UserViewHolder> {
    private static int viewHolderCount;
    private int mNumberItems;                               //For better functionality of recycler view
    private List<Users> mUserList;                          //Holds list of users with their details
    private Context context;
    private UserAdapterOnClickHandler mOnClickHandler;      //handles click on individual items
    ListItemBinding mListItemBinding;           //For data binding
    public UserAdapterRV(int numberOfItems, List<Users> userList,UserAdapterOnClickHandler userAdapterOnClickHandler) {
        mNumberItems = numberOfItems;
        viewHolderCount = 0;
        mUserList = userList;
        mOnClickHandler = userAdapterOnClickHandler;

    }
    //Interface for handling click on individual items
    public interface UserAdapterOnClickHandler{
        void onClick(Uri uri);
    }

    @Override
    public UserAdapterRV.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        context = parent.getContext();
        int layoutIdForListItem = R.layout.list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        //Inflates a view to pass to viewholder,
        mListItemBinding = DataBindingUtil.inflate(inflater,layoutIdForListItem,parent,false);
        UserViewHolder userViewHolder = new UserViewHolder(mListItemBinding.getRoot());
        //Keeps count of the viewholders
        viewHolderCount++;
        return userViewHolder;
    }
    //Binds data
    @Override
    public void onBindViewHolder(UserAdapterRV.UserViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }


    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{



        public UserViewHolder(View listItemView) {
            super(listItemView);
            listItemView.setOnClickListener(this);

        }



        void bind(int position) {
            //Binds the data with the views referenced in the constructor
            Users currentUser = mUserList.get(position);
            //Setting the texts of their respective textViews according to the data received from the List
            mListItemBinding.followerName.setText(currentUser.getFullName());
            mListItemBinding.followerUserName.setText(currentUser.getUserName());
            // follower_track_date.setText(currentUser.getDate_trackedFrom());
            mListItemBinding.comments.setText("Comments : " + String.valueOf(currentUser.getCommentsPosted()));
            mListItemBinding.likes.setText("Likes : " + String.valueOf(currentUser.getLikesPosted()));
            //Loads images from Instagram site
            Glide.with(context).load(currentUser.getProfilePictureLink()).skipMemoryCache(true).override(144, 144).into(mListItemBinding.profilePicture);

        }
        //Sets up the uri for on click events
        @Override
        public void onClick(View v) {
            Uri uri = Uri.parse(mUserList.get(getAdapterPosition()).getProfileLink());
            mOnClickHandler.onClick(uri);
        }
    }
}
