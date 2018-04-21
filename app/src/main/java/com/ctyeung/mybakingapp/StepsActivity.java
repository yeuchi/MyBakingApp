package com.ctyeung.mybakingapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.content.Intent;

import com.ctyeung.mybakingapp.data.SharedPrefUtil;
import com.ctyeung.mybakingapp.data.RecipeFactory;
import com.ctyeung.mybakingapp.data.Recipe;
import com.ctyeung.mybakingapp.data.Step;
import com.ctyeung.mybakingapp.utility.JSONHelper;
import java.util.List;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.BindView;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.appwidget.AppWidgetManager;
/**
 * Created by ctyeung on 3/5/18.
 */

public class StepsActivity extends AppCompatActivity
        implements com.ctyeung.mybakingapp.StepListAdapter.ListItemClickListener
{
    private boolean isTwoPane = false;

    private StepListAdapter.ListItemClickListener mListener;
    private StepListAdapter mListAdapter;

    public @BindView(R.id.step_list) RecyclerView mRecipeList;

    private List<Step> mSteps;
    private Recipe mRecipe;

    private int mStepDetailIndex = 0;
    private BaseFragment mFragment=null;
    private FragmentManager mFragmentManager;

    private SharedPrefUtil mSharedPrefUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        ButterKnife.bind(this);

        // get last know index
        mSharedPrefUtil = new SharedPrefUtil(getApplicationContext());
        mStepDetailIndex = mSharedPrefUtil.getStepSelected();

        GridLayoutManager reviewManager = new GridLayoutManager(this, 1);
        mRecipeList.setLayoutManager(reviewManager);
        mListener = this;
        parseSteps();
        setFragment();
        updateWidget();
    }

    private void updateWidget()
    {
        // https://stackoverflow.com/questions/3455123/programmatically-update-widget-from-activity-service-receiver
        mSharedPrefUtil.setStepSelected(mStepDetailIndex);
        int[] ids = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), HomeScreenWidget.class));
        HomeScreenWidget myWidget = new HomeScreenWidget();
        myWidget.onUpdate(this, AppWidgetManager.getInstance(this),ids);

    }
    private void setFragment()
    {
        isTwoPane = (null==findViewById(R.id.frame_step_detail))?false:true;

        if(isTwoPane)
        {
            // remove existing fragment
            if(mFragment != null)
                getSupportFragmentManager()
                        .beginTransaction()
                        .remove(mFragment)
                        .commit();

            // insert fragment
            if(0==mStepDetailIndex)
            {
                mFragment = new StepIngredientsFragment();
                mFragment.setElement(mRecipe, mStepDetailIndex);
            }
            else
            {
                mFragment = new StepDetailFragment();
                mFragment.setElements(mSteps, mStepDetailIndex);
            }

            mFragmentManager = getSupportFragmentManager();
            mFragmentManager.beginTransaction()
                    .add(R.id.frame_step_detail, mFragment)
                    .commit();
        }
    }

    private void parseSteps()
    {
        String str = this.getIntent().getStringExtra(Intent.EXTRA_TEXT);
        JSONObject json = JSONHelper.parseJson(str);
        mRecipe = new Recipe(json);
        mSteps = RecipeFactory.StepsJsonArray2List(mRecipe.getSteps(), this);

        StepListAdapter.Reset();
        mListAdapter = new StepListAdapter(mListener, mSteps, mStepDetailIndex);
        mRecipeList.setAdapter(mListAdapter);
        mRecipeList.setHasFixedSize(true);
    }

    @Override
    public void onListItemClick(int clickItemIndex)
    {
        mSharedPrefUtil.resetStepDetailChildren();
        mSharedPrefUtil.resetIngredientChildren();

        // persist the value into shared preference if rotate
        mStepDetailIndex = clickItemIndex;

        // phone mode
        if(false==isTwoPane)
        {
            Intent intent = (0 == clickItemIndex) ?
                    new Intent(this, IngredientsActivity.class) :    // 0 index - ingredients
                    new Intent(this, StepDetailActivity.class);     // all other steps

            // need to parse for mRecipe from List<Step>
            String str = mRecipe.getJSONString();
            intent.putExtra(Intent.EXTRA_TEXT, str);
            intent.putExtra(Intent.EXTRA_INDEX, clickItemIndex);
            startActivity(intent);
        }
        // tablet mode
        else
        {
            setFragment();
        }
    }

    @Override
    protected void onDestroy()
    {
        mSharedPrefUtil.setStepSelected(mStepDetailIndex);
        super.onDestroy();
    }
}
