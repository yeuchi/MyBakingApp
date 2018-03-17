package com.ctyeung.mybakingapp;

import android.net.Uri;
import android.os.Bundle;
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
    private TextView btnLeft;
    private TextView btnRight;
    private int recipeStepIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        parseSteps();
        buttonClickHandlers();
        render();
    }

    private void render()
    {
        Step step = mSteps.get(recipeStepIndex);
        loadDescription(step);
        loadVideo(step);
    }

    private void parseSteps()
    {
        String str = this.getIntent().getStringExtra(Intent.EXTRA_TEXT);
        JSONObject json = JSONHelper.parseJson(str);
        mRecipe = new Recipe(json);
        mSteps = RecipeFactory.StepsJsonArray2List(mRecipe.getSteps());
    }

    private void loadDescription(Step step)
    {
        String detail = step.getDescription();

        if(null==detail ||
                0==detail.length())
            detail = step.getShortDescription();

        TextView textView = (TextView) this.findViewById(R.id.detail_item);
        textView.setText(detail);
    }

    private void loadVideo(Step step)
    {
        Uri uri = step.getVideoUri();

        if(null == uri)
            uri = step.getThumbnailUri();

        VideoView videoView = (VideoView)this.findViewById(R.id.video_item);
        videoView.stopPlayback();
        videoView.setVideoURI(uri);
        videoView.start();
    }

    private void buttonClickHandlers()
    {
        btnLeft = (TextView) findViewById(R.id.btn_left);
        btnLeft.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                recipeStepIndex = (recipeStepIndex > 1)?
                                    recipeStepIndex-1:1;

                render();
            }
        });

        btnRight = (TextView) findViewById(R.id.btn_right);
        btnRight.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                recipeStepIndex = (recipeStepIndex < mSteps.size())?
                                    recipeStepIndex+1:
                                    mSteps.size()-1;

                render();
            }
        });
    }

    /*
     * Add listener for left / right swipes
     *
     * Fragments
     * https://stackoverflow.com/questions/19561036/adding-fragments-dynamically
     */
}
