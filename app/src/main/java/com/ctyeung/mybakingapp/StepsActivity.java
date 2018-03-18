package com.ctyeung.mybakingapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;

import com.ctyeung.mybakingapp.data.Ingredient;
import com.ctyeung.mybakingapp.data.RecipeFactory;
import com.ctyeung.mybakingapp.data.Recipe;
import com.ctyeung.mybakingapp.data.Step;
import com.ctyeung.mybakingapp.utility.JSONHelper;
import com.ctyeung.mybakingapp.RecipeListAdapter;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by ctyeung on 3/5/18.
 */

public class StepsActivity extends AppCompatActivity
        implements com.ctyeung.mybakingapp.StepListAdapter.ListItemClickListener
{
    private FragmentManager fragmentManager;
    private boolean isTwoPane = false;

    private StepListAdapter.ListItemClickListener mListener;
    private StepListAdapter mListAdapter;

    private Toast _toast;
    private RecyclerView mRecipeList;

    private List<Step> mSteps;
    private Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        GridLayoutManager reviewManager = new GridLayoutManager(this, 1);
        mRecipeList = (RecyclerView) this.findViewById(R.id.step_list);
        mRecipeList.setLayoutManager(reviewManager);
        mListener = this;
        parseSteps();
        SetFragment();
    }

    private void SetFragment()
    {
        isTwoPane = false;  // replace with checking code

        if(isTwoPane)
        {
            // create 2nd fragment
            StepDetailFragment fragment = new StepDetailFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.frame_step_detail, fragment)
                    .commit();
        }
    }

    private void parseSteps()
    {
        String str = this.getIntent().getStringExtra(Intent.EXTRA_TEXT);
        JSONObject json = JSONHelper.parseJson(str);
        mRecipe = new Recipe(json);
        mSteps = RecipeFactory.StepsJsonArray2List(mRecipe.getSteps());

        mListAdapter = new StepListAdapter(mSteps.size(), mListener, mSteps);
        mRecipeList.setAdapter(mListAdapter);
        mRecipeList.setHasFixedSize(true);
    }

    @Override
    public void onListItemClick(int clickItemIndex)
    {
        // phone mode
        if(false==isTwoPane)
        {
            Intent intent = (0 == clickItemIndex) ?
                    new Intent(this, IngredientsActivity.class) :    // 0 index - ingredients
                    new Intent(this, StepDetailActivity.class);     // all other steps

            // need to parse for mRecipe from List<Step>
            String str = mRecipe.getJSONString();
            intent.putExtra(Intent.EXTRA_TEXT, str);
            startActivity(intent);
        }
        // tablet mode
        else
        {

        }
    }

}
