package com.ctyeung.mybakingapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.widget.VideoView;

import com.ctyeung.mybakingapp.data.RecipeFactory;
import com.ctyeung.mybakingapp.data.Recipe;
import com.ctyeung.mybakingapp.data.Step;
import com.ctyeung.mybakingapp.utility.JSONHelper;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by ctyeung on 3/5/18.
 */

public class StepDetailActivity extends AppCompatActivity
{
    private List<Step> mSteps;
    private Recipe mRecipe;

    private int mRecipeStepIndex = 1;
    private BaseFragment mFragment;
    private FragmentManager mFragmentManager;

    private TextView mBtnNext;
    private TextView mBtnPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        parseSteps();
        SetFragment();
        buttonClickHandlers();
    }

    private void parseSteps()
    {
        // step index
        mRecipeStepIndex = this.getIntent().getIntExtra(Intent.EXTRA_INDEX, 1);

        // recipe
        String str = this.getIntent().getStringExtra(Intent.EXTRA_TEXT);
        JSONObject json = JSONHelper.parseJson(str);
        mRecipe = new Recipe(json);
        mSteps = RecipeFactory.StepsJsonArray2List(mRecipe.getSteps());
    }

    private void SetFragment()
    {
        // remove existing fragment
        if(mFragment != null)
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(mFragment)
                    .commit();

        // insert fragment
        if(0==mRecipeStepIndex)
        {
            mFragment = new StepIngredientsFragment();
            mFragment.setElement(mRecipe, 0);
        }
        else
        {
            mFragment = new StepDetailFragment();
            mFragment.setElements(mSteps, mRecipeStepIndex);
        }

        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .add(R.id.frame_step_detail2, mFragment)
                .commit();
    }

    private void buttonClickHandlers()
    {
        mBtnPrevious = (TextView) findViewById(R.id.btn_previous);
        mBtnPrevious.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mRecipeStepIndex = (mRecipeStepIndex > 0)?
                                    mRecipeStepIndex-1:0;

                SetFragment();
            }
        });

        mBtnNext = (TextView) findViewById(R.id.btn_next);
        mBtnNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int size = mSteps.size()-1;
                mRecipeStepIndex = (mRecipeStepIndex < size)?
                                    mRecipeStepIndex+1:
                                    size;

                SetFragment();
            }
        });
    }
}
