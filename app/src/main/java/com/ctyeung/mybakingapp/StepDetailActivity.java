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

    private TextView btnPrevious;
    private TextView btnNext;

    private int recipeStepIndex = 1;
    private FragmentManager fragmentManager;
    private StepDetailFragment fragment;

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
        String str = this.getIntent().getStringExtra(Intent.EXTRA_TEXT);
        JSONObject json = JSONHelper.parseJson(str);
        mRecipe = new Recipe(json);
        mSteps = RecipeFactory.StepsJsonArray2List(mRecipe.getSteps());
    }

    private void SetFragment()
    {
        // create 2nd fragment
        fragment = new StepDetailFragment();
        fragment.setElements(mSteps, recipeStepIndex);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.frame_step_detail2, fragment)
                .commit();
    }

    private void buttonClickHandlers()
    {
        // ??? CTY ??? show ingredients

        btnPrevious = (TextView) findViewById(R.id.btn_previous);
        btnPrevious.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
            recipeStepIndex = (recipeStepIndex > 1)?
                                recipeStepIndex-1:1;

            fragment.setElements(mSteps, recipeStepIndex);
            }
        });

        btnNext = (TextView) findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int size = mSteps.size()-1;
                recipeStepIndex = (recipeStepIndex < size)?
                                recipeStepIndex+1:
                                size;

            fragment.setElements(mSteps, recipeStepIndex);
            }
        });
    }
}
