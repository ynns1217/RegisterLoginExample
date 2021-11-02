 package com.example.registerloginexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

 public class Search extends AppCompatActivity {
     private List<String> items = Arrays.asList("네이버","넷플릭스","네이트","야후");

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_search);

         SearchView searchVew = findViewById(R.id.search_view);
         TextView resultTextView = findViewById(R.id.textView);
         resultTextView.setText(getResult());

         searchVew.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
             @Override
             public boolean onQueryTextSubmit(String query){
                 return false;
             }

             @Override
             public boolean onQueryTextChange(String newText) {
                 resultTextView.setText(search(newText));

                 return true;
             }
         });
     }

     private String search(String query) {
         StringBuilder sb = new StringBuilder();
         for (int i = 0; i < items.size(); i++) {
             String item = items.get(i);
             if (item.contains(query)) {
                 sb.append(item);
                 if (i != items.size() - 1) {
                     sb.append("\n");
                 }
             }
         }
             return sb.toString();
     }





     private String getResult() {
         StringBuilder sb =new StringBuilder();
         for(int i =0; i<items.size();i++) {
             String item = items.get(i);
             sb.append(item);
             if (i != items.size() -1)
             {
                 sb.append("\n");
             }
         }
         return sb.toString();
     }
 }