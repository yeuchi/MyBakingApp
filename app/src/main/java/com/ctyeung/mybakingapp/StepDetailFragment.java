package com.ctyeung.mybakingapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.ctyeung.mybakingapp.data.Recipe;
import com.ctyeung.mybakingapp.data.RecipeFactory;
import com.ctyeung.mybakingapp.data.Step;
import com.ctyeung.mybakingapp.utility.JSONHelper;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by ctyeung on 3/17/18.
 */

public class StepDetailFragment extends Fragment
{
    private View rootView;

    private List<Step> mSteps;
    private TextView btnPrevious;
    private TextView btnNext;
    private int recipeStepIndex = 1;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle saveInstanceState)
    {

        rootView = inflater.inflate(R.layout.fragment_step_detail, container, false);
        render();

        return rootView;
    }

    public void setElements(List<Step> mSteps,
                            int i)
    {
        this.recipeStepIndex = i;
        this.mSteps = mSteps;

        if(null!=rootView)
            render();
    }

    public void onAttach(Context context)
    {
        super.onAttach(context);

        try
        {
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString() + "must implement OnClickListener");
        }
    }

    private void render()
    {
        Step step = mSteps.get(recipeStepIndex);
        loadDescription(step);
        loadVideo(step);
    }

    private void loadDescription(Step step)
    {
        String desc = step.getDescription();
        String shortDesc = step.getShortDescription();

        String str = "no description available.";

        if(null!=desc && desc.length()>0)
            str = desc;

        else if (null!=desc && desc.length()>0)
            str = desc;

        TextView textView = (TextView) rootView.findViewById(R.id.detail_item);
        textView.setText(str);
    }

    private void loadVideo(Step step)
    {
        TextView textView = (TextView)rootView.findViewById(R.id.fragment_video_nada);
        VideoView videoView = (VideoView) rootView.findViewById(R.id.video_item);
        ProgressBar progressBar = (ProgressBar)rootView.findViewById(R.id.fragment_progress);
        progressBar.setVisibility(View.INVISIBLE);

        Uri videoUri = step.getVideoUri();
        Uri thumbnailUri = step.getThumbnailUri();

        if(null == videoUri &&
                null == thumbnailUri)
        {
            // show error
            textView.setVisibility(View.VISIBLE);
            videoView.setVisibility(View.INVISIBLE);
            return;
        }
        else {
            Uri uri = (null==videoUri)?
                        thumbnailUri:
                        videoUri;

            textView.setVisibility(View.INVISIBLE);
            videoView.setVisibility(View.VISIBLE);
            videoView.stopPlayback();
            videoView.setVideoURI(uri);
            videoView.start();
        }
    }
}
