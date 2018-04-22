package com.ctyeung.mybakingapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.widget.VideoView;

import com.ctyeung.mybakingapp.data.RecipeFactory;
import com.ctyeung.mybakingapp.data.Recipe;
import com.ctyeung.mybakingapp.data.SharedPrefUtil;
import com.ctyeung.mybakingapp.data.Step;
import com.ctyeung.mybakingapp.utility.JSONHelper;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.BindView;

/**
 * Created by ctyeung on 3/5/18.
 *
 * Reference: Jake Wharton's ButterKnife
 * http://jakewharton.github.io/butterknife/
 */

public class StepDetailActivity extends AppCompatActivity
{
    private List<Step> mSteps;
    private Recipe mRecipe;

    private int mStepIndex;
    private BaseFragment mFragment;
    private FragmentManager mFragmentManager;

    public @BindView(R.id.btn_previous) TextView mBtnPrevious;
    public @BindView(R.id.btn_next) TextView mBtnNext;
    private SharedPrefUtil mSharedPrefUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);
        ButterKnife.bind(this);

        // tool is defined in the layout file
        Toolbar toolbar = (Toolbar) findViewById(R.id.stepDetail_toolbar);
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        // set step selection prior to rotation
        mSharedPrefUtil = new SharedPrefUtil(this);
        mStepIndex = mSharedPrefUtil.getStepSelected();

        parseSteps();
        SetFragment();
        buttonClickHandlers();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                Intent homeIntent = new Intent(this, MainActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    private void parseSteps()
    {
        // step index
        mStepIndex = this.getIntent().getIntExtra(Intent.EXTRA_INDEX, 1);

        // recipe
        String str = this.getIntent().getStringExtra(Intent.EXTRA_TEXT);
        JSONObject json = JSONHelper.parseJson(str);
        mRecipe = new Recipe(json);
        mSteps = RecipeFactory.StepsJsonArray2List(mRecipe.getSteps(), this);
    }

    private void SetFragment()
    {
        mSharedPrefUtil.setStepSelected(mStepIndex);

        // remove existing fragment
        if(mFragment != null)
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(mFragment)
                    .commit();

        // insert fragment
        if(0==mStepIndex)
        {
            mFragment = new StepIngredientsFragment();
            mFragment.setElement(mRecipe, 0);
        }
        else
        {
            mFragment = new StepDetailFragment();
            mFragment.setElements(mSteps, mStepIndex);
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
                mSharedPrefUtil.resetStepDetailChildren();

                mStepIndex = (mStepIndex > 0)?
                              mStepIndex-1:0;

                SetFragment();
            }
        });

        mBtnNext = (TextView) findViewById(R.id.btn_next);
        mBtnNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mSharedPrefUtil.resetStepDetailChildren();

                int size = mSteps.size()-1;
                mStepIndex = (mStepIndex < size)?
                              mStepIndex+1:
                              size;

                SetFragment();
            }
        });
    }
}
