package com.ctyeung.mybakingapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.util.Log;
import android.content.res.Resources;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import android.widget.Toast;
import android.util.DisplayMetrics;

import com.ctyeung.mybakingapp.utility.NetworkUtils;
import com.ctyeung.mybakingapp.data.Recipe;
import com.ctyeung.mybakingapp.data.RecipeFactory;
import com.ctyeung.mybakingapp.utility.JSONHelper;
import com.ctyeung.mybakingapp.data.SharedPrefUtil;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
        implements RecipeListAdapter.ListItemClickListener {

    private Toast mToast;
    private List<Recipe> recipes;
    private ProgressBar mLoadingIndicator;
    private RecipeListAdapter.ListItemClickListener mListener;
    private TextView mNetworkErrorDisplay;
    private RecipeListAdapter mListAdapter;
    private RecyclerView mRecyclerViewList;
    private SharedPrefUtil sharedPrefUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        sharedPrefUtil = new SharedPrefUtil(this);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_display_progress);
        mNetworkErrorDisplay = (TextView) findViewById(R.id.tv_network_error_display);

        int columns = calNumOfColumns();
        GridLayoutManager layoutManager = new GridLayoutManager(this, columns);
        mRecyclerViewList = (RecyclerView) findViewById(R.id.recipe_list);
        mRecyclerViewList.setLayoutManager(layoutManager);
        mListener = this;

        requestRecipes();
    }

    protected int calNumOfColumns()
    {
        // check if this is a 7" or larger tablet
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int widthPixels = metrics.widthPixels;
        float scaleFactor = metrics.density;
        float widthDp = widthPixels / scaleFactor;
        int numOfColumns = (widthDp>600)?5:1;
        return numOfColumns;
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

            if(null == jsonArray)
            {
                mNetworkErrorDisplay.setVisibility(View.VISIBLE);
                return;
            }

            recipes = RecipeFactory.Create(jsonArray);

            if(null!=recipes &&
                    recipes.size()>0)
            {
                RecipeListAdapter.mViewHolderCount = 0;
                mListAdapter = new RecipeListAdapter(recipes.size(), mListener, recipes);
                mRecyclerViewList.setAdapter(mListAdapter);
                mRecyclerViewList.setHasFixedSize(true);
                return;
            }
        }
    }

    @Override
    public void onListItemClick(int clickItemIndex)
    {
        if(mToast!=null)
            mToast.cancel();

        Recipe selectedRecipe = recipes.get(clickItemIndex);
        //JSONArray jsonArray = selectedRecipe.getIngredients();
        //sharedPrefUtil.setIngredients(jsonArray.toString());

        // load detail page
        Intent intent = new Intent(this, StepsActivity.class);
        String str = selectedRecipe.getJSONString();
        intent.putExtra(Intent.EXTRA_TEXT, str);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
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

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
