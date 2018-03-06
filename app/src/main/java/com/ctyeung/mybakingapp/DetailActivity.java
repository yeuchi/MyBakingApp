package com.ctyeung.mybakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by ctyeung on 3/5/18.
 */

public class DetailActivity extends AppCompatActivity
    implements com.ctyeung.mybakingapp.RecipeListAdapter.ListItemClickListener

{
    private Toast _toast;
    private RecipeListAdapter.ListItemClickListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    @Override
    public void onListItemClick(int clickItemIndex) {

    }

}
