package com.example.registerloginexample;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
public class PlusSiteListAdapter extends BaseAdapter{
        private final Context context;
        private final List<PlusSiteResult> sitedList;
        public PlusSiteListAdapter(Context context, List<PlusSiteResult> sitedList) {
                this.context = context;
                this.sitedList = sitedList;
        }

        @Override
        public int getCount(){
                return sitedList.size();} //리스트뷰의 총 갯수

        @Override
        public Object getItem(int position){
                return sitedList.get(position);}  //해당 위치의 값을 리스트뷰에 뿌려줌

        @Override
        public long getItemId(int position){
                return position;}

        //리스트뷰에서 실질적으로 뿌려주는 부분임
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
                View v = View.inflate(context, R.layout.sitelist, null);
                TextView plusSiteText = (TextView) v.findViewById(R.id.siteText);
                TextView plusIDText = (TextView) v.findViewById(R.id.idText);
                TextView plusPasswordText = (TextView) v.findViewById(R.id.pwText);
                plusSiteText.setText(sitedList.get(position).getPlusSite());
                plusIDText.setText(sitedList.get(position).getPlusID());
                plusPasswordText.setText(sitedList.get(position).getPlusPassword());
                v.setTag(sitedList.get(position).getPlusSite());
                return v;
        }
}