package com.socmodder.android.mysign;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import com.j256.ormlite.dao.Dao;
import com.socmodder.android.database.DatabaseHelper;
import com.socmodder.android.database.Sign;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Miller
 * Date: 12/9/12
 * Time: 4:31 PM
 */
public class SearchSigns extends Activity {
    SearchView searcher;
    ListView listView;
    DatabaseHelper helper;
    Dao<Sign, Integer> signDao;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_signs_layout);
        setupLayoutItems();

    }

    @Override
    public void onStart(){
        super.onStart();
        /*List<Sign> signs = querySigns();
        if(signs != null){
            listView.setAdapter(new SignArrayAdapter(this, signs));
        } */
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    public void setupLayoutItems(){
        searcher = (SearchView)findViewById(R.id.searchview);
        listView = (ListView)findViewById(R.id.search_listview);

        searcher.setSubmitButtonEnabled(true);
    }

    public List querySigns(){
        try {
            signDao = helper.getDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Sign> signList = null;
        try {
            signList = signDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return signList;
    }
}