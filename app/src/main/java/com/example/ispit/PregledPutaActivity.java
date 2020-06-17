package com.example.ispit;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class PregledPutaActivity extends AppCompatActivity {

    public ArrayList<PutModel> put;
    ListView list;
    Activity me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pregled_puta);
        put=PutModel.getAll(this);
        me=this;

        list=(ListView)findViewById(R.id.pregled_puta_list);
        list.setAdapter(new MyListAdapter(this));
        list.setTextFilterEnabled(true);

    }


    public class MyListAdapter extends ArrayAdapter<PutModel> {
        public MyListAdapter(Context context) {
            super(context, 0, Collections.<PutModel>emptyList());
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null) convertView= LayoutInflater.from(getContext()).inflate(R.layout.view, null, false);
            PutModel data=put.get(position);
            String firstPart="Etapa 1: "+data.etapaPuta+" \n"+ "Datum: "+data.datum+""
                    +"Vrijeme OD: "+data.vrijemOd+" "
                    +"Vrijeme DO: "+data.vrijemeDo+""+"Opis: "+data.opis;
            ((TextView)convertView.findViewById(R.id.osnovno)).setText(firstPart);
            return convertView;
        }
    }

}
