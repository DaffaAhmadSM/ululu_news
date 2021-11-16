package com.example.ululunews;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {
    List<Model> storeList;
    Realm realm;
    public RealmHelper(Realm realm) { this.realm = realm; }

    public void save (Model modelNews){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null) {
                    Number currentIdNum = realm.where(Model.class).max("id");
                    int nextId;
                    if (currentIdNum == null){
                        nextId = 1;
                    }else {
                        nextId = currentIdNum.intValue() + 1; }
                    modelNews.setId(nextId);
                    Model modelNews1 = realm.copyToRealm(modelNews);
                    Log.e("Created", "Database was created" + modelNews1);
                }
            }
        });
    }
    public List<Model> getAllNews(){
        RealmResults<Model> results = realm.where(Model.class).findAll();
        return results;
    }
    public void delete(Integer id) {
        final RealmResults<Model> modelNews = realm.where(Model.class).equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                modelNews.deleteFromRealm(0);
            }
        });
    }
}
