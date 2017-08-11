package com.chiragawale.insights.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chiragawale.insights.databinding.FragmentOverviewBinding;
import com.chiragawale.insights.keys.GlobalVar;
import com.chiragawale.insights.R;


public class OverviewFragment extends Fragment {
   //For binding values to the text views
    static FragmentOverviewBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_overview,container,false);
        View view = mBinding.getRoot();

        setValues();
        return view;
    }

    //Set the values of text views of the fragment
    public static void setValues(){

        mBinding.totalPosts.setText("("+GlobalVar.mediaDao.getTotalPosts()+")");
        mBinding.totalLikesValue.setText(GlobalVar.mediaDao.getTotalLikes()+"");
        mBinding.totalCommentsValue.setText(GlobalVar.mediaDao.getTotalComments()+"");

        mBinding.mutualLikesValue.setText(GlobalVar.mediaDao.getMutualLikes()+"");
        mBinding.mutualCommentsValue.setText(GlobalVar.mediaDao.getMutualComments()+"");

        mBinding.fansLikesValue.setText(GlobalVar.mediaDao.getFanLikes()+"");
        mBinding.fansCommentsValue.setText(GlobalVar.mediaDao.getFanComments()+"");

        mBinding.followsLikesValue.setText(GlobalVar.mediaDao.getFollowsLikes()+"");
        mBinding.followsCommentsValue.setText(GlobalVar.mediaDao.getFollowsComments()+"");

        mBinding.strangersLikesValue.setText(GlobalVar.mediaDao.getStrangerLikes()+"");
        mBinding.strangersCommentsValue.setText(GlobalVar.mediaDao.getStrangerComments()+"");

        mBinding.fansValue.setText(GlobalVar.mediaDao.getFansCount()+"");
        mBinding.followsValue.setText(GlobalVar.mediaDao.getFollowsCount()+"");
        mBinding.mutualValue.setText(GlobalVar.mediaDao.getMutualCount()+"");
    }


}
