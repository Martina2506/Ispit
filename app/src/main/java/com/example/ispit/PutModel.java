package com.example.ispit;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class PutModel {
    public static String storageID="STORAGE_PUT";

    public int vrijemOd,vrijemeDo,id;
    public String etapaPuta,datum,opis;


    public static ArrayList<PutModel> getAll(Activity a){
        SharedPreferences sp=a.getSharedPreferences(storageID, Context.MODE_PRIVATE);
        Set keys=sp.getAll().keySet();
        ArrayList<PutModel> list = new ArrayList<>();
        Iterator<String> it = keys.iterator();
        while(it.hasNext()){
            list.add(new PutModel(Integer.valueOf(it.next()), a));
        }
        return list;
    }
    public PutModel(){}

    public void save(Activity c) {
        SharedPreferences sp=c.getSharedPreferences(storageID, Context.MODE_PRIVATE);
        id=sp.getAll().keySet().size()+1;
        JSONObject o=new JSONObject();

        try {
            o.put("etapa_puta", etapaPuta);
            o.put("datum", datum);
            o.put("vrijeme_od", vrijemOd);
            o.put("vrijeme_do", vrijemeDo);
            o.put("opis", opis);


        }
        catch (Exception e) {
            e.printStackTrace();
        }
        sp.edit().putString(String.format("%d", id), o.toString()).commit();
        while(o.length()>0)
            o.remove(o.keys().next());

    }
    public PutModel(int id, Activity a) {
        SharedPreferences sp=a.getSharedPreferences(storageID, Context.MODE_PRIVATE);
        this.id=id;
        try {
            JSONObject o = new JSONObject(sp.getString(String.format("%d", id), ""));
            this.etapaPuta = o.getString("etapa_puta");
            this.datum =o.getString("datum");
            this.vrijemOd=o.getInt("vrijemeOd");
            this.vrijemeDo=o.getInt("vrijemeDo");
            this.opis=o.getString("opis");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
