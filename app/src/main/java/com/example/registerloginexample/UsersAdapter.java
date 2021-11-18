package com.example.registerloginexample;

//ArrayList에 있는 PersonalData 타입의 데이터를 RecyclerView에 보여주는 작업

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.CustomViewHolder> {

    private ArrayList<PersonalData> mList = null;
    private Activity context = null;


    public UsersAdapter(Activity context, ArrayList<PersonalData> list) {
        this.context = context;
        this.mList = list;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView plusSite;
        protected TextView plusID;
        protected TextView plusPassword;


        public CustomViewHolder(View view) {
            super(view);
            this.plusSite = (TextView) view.findViewById(R.id.textView_list_plusSite);
            this.plusID = (TextView) view.findViewById(R.id.textView_list_plusID);
            this.plusPassword = (TextView) view.findViewById(R.id.textView_list_plusPassword);
        }
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_main, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.plusSite.setText(mList.get(position).getMember_plusSite());
        viewholder.plusID.setText(mList.get(position).getMember_plusID());
        viewholder.plusPassword.setText(mList.get(position).getMember_plusPassword());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);

    }
}
