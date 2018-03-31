package com.ctyeung.mybakingapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;

import com.ctyeung.mybakingapp.data.SharedPrefUtil;
import com.ctyeung.mybakingapp.data.RecipeFactory;
import com.ctyeung.mybakingapp.data.Recipe;
import com.ctyeung.mybakingapp.data.Step;
import com.ctyeung.mybakingapp.utility.JSONHelper;
import java.util.List;
import org.json.JSONObject;

/**
 * Created by ctyeung on 3/5/18.
 */

public class StepsActivity extends AppCompatActivity
        implements com.ctyeung.mybakingapp.StepListAdapter.ListItemClickListener
{
    private boolean isTwoPane = false;

    private StepListAdapter.ListItemClickListener mListener;
    private StepListAdapter mListAdapter;

    private Toast _toast;
    private RecyclerView mRecipeList;

    private List<Step> mSteps;
    private Recipe mRecipe;

    private int stepDetailIndex = 0;
    private BaseFragment fragment=null;
    private FragmentManager fragmentManager;

    private SharedPrefUtil sharedPrefUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        // get last know index
        sharedPrefUtil = new SharedPrefUtil(getApplicationContext());
        stepDetailIndex = sharedPrefUtil.getStepSelected();

        GridLayoutManager reviewManager = new GridLayoutManager(this, 1);
        mRecipeList = (RecyclerView) this.findViewById(R.id.step_list);
        mRecipeList.setLayoutManager(reviewManager);
        mListener = this;
        parseSteps();
        SetFragment();
    }

    private void SetFragment()
    {
        isTwoPane = (null==findViewById(R.id.frame_step_detail))?false:true;

        if(isTwoPane)
        {
            // remove existing fragment
            if(fragment != null)
                getSupportFragmentManager()
                        .beginTransaction()
                        .remove(fragment)
                        .commit();

            // insert fragment
            if(0==stepDetailIndex)
            {
                fragment = new StepIngredientsFragment();
                fragment.setElement(mRecipe);
            }
            else
            {
                fragment = new StepDetailFragment();
                fragment.setElements(mSteps, stepDetailIndex);
            }

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

        StepListAdapter.mViewHolderCount = 0;
        mListAdapter = new StepListAdapter(mSteps.size(), mListener, mSteps, stepDetailIndex);
        mRecipeList.setAdapter(mListAdapter);
        mRecipeList.setHasFixedSize(true);
    }

    @Override
    public void onListItemClick(int clickItemIndex)
    {
        stepDetailIndex = clickItemIndex;

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
            SetFragment();
        }
    }

    @Override
    protected void onDestroy()
    {
        sharedPrefUtil.setStepSelected(stepDetailIndex);
        super.onDestroy();
    }
}
