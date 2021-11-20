package com.example.registerloginexample;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import org.w3c.dom.Text;
import java.util.List;
public class PlusSiteListAdapter extends BaseAdapter
        private Context context;
        private List<PlusSite> noticedList;
        public PlusSiteListAdapter(Context context, List<PlusSite> PlusSitedList)
        this.context = context;
                this.PlusSitedList = PlusSitedList;

@Override
public int getCount()
        return PlusSitedList.size(); //리스트뷰의 총 갯수

@Override
public Object getItem(int position)
        return PlusSitedList.get(position);//해당 위치의 값을 리스트뷰에 뿌려줌

@Override
public long getItemId(int position)
        return position;

//리스트뷰에서 실질적으로 뿌려주는 부분임
@Override
public View getView(int position, View convertView, ViewGroup parent)
        View v = View.inflate(context, R.layout.notice, null);
        TextView plusSiteText = (TextView)v.findViewById(R.id.plusSiteText);
        TextView plusIDText = (TextView)v.findViewById(R.id.plusIDText);
        TextView plusPasswordText = (TextView)v.findViewById(R.id.plusPasswordText);
        plusSiteText.setText(PlusSitedList.get(position).getplusSite());
        plusIDText.setText(PlusSitedList.get(position).getplusID());
        plusPasswordText.setText(PlusSitedList.get(position).getplusPassword());
        v.setTag(PlusSitedList.get(position).getPlusSite());
        return v;

