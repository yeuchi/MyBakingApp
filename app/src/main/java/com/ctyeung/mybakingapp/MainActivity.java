package com.ctyeung.mybakingapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.ctyeung.mybakingapp.utility.NetworkUtils;
import com.ctyeung.mybakingapp.data.Recipe;
import com.ctyeung.mybakingapp.data.RecipeFactory;
import com.ctyeung.mybakingapp.utility.JSONHelper;
import com.ctyeung.mybakingapp.RecipeListAdapter;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity  implements RecipeListAdapter.ListItemClickListener {

    private List<Recipe> recipes;
    private ProgressBar mLoadingIndicator;
    private RecipeListAdapter.ListItemClickListener mListener;
    private TextView mNetworkErrorDisplay;
    private RecipeListAdapter mListAdapter;
    private RecyclerView mRecyclerViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_display_progress);
        mNetworkErrorDisplay = (TextView) findViewById(R.id.tv_network_error_display);
        mRecyclerViewList = (RecyclerView) findViewById(R.id.recipe_list);

        requestRecipes();

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    protected void requestRecipes()
    {
        try
        {
            URL url = new URL(NetworkUtils.RECIPE_URL.toString());
            GithubQueryTask task = new GithubQueryTask();
            task.execute(url);
        }
        catch(Exception ex)
        {
            Log.e("httptest",Log.getStackTraceString(ex));
        }
    }

    public class GithubQueryTask extends AsyncTask<URL, Void, String>
    {
        public GithubQueryTask()
        {
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String githubSearchResults = null;
            try
            {
                githubSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
            return githubSearchResults;
        }

        protected void onPreExecute()
        {
            super.onPreExecute();

        }

        protected void onPostExecute(String str)
        {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            JSONArray jsonArray = JSONHelper.parseJsonArray(str);
            JSONObject json = JSONHelper.parseJsonFromArray(jsonArray, 0);

            /*
            if(null != json)
            {
                JSONArray jsonArray = JSONHelper.getJsonArray(json, "results");
                recipes = RecipeFactory.Create(jsonArray);

                if(null!=recipes &&
                        recipes.size()>0)
                {
                    mListAdapter = new RecipeListAdapter(recipes.size(), mListener, jsonArray);
                    mRecyclerViewList.setAdapter(mListAdapter);
                    return;
                }
            }*/

            // display error if no data is available
            mNetworkErrorDisplay.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onListItemClick(int clickItemIndex) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
