package com.ctyeung.mybakingapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
/*
        List<Integer> list = AndroidImageAssets.getAll();
        GridView gridview = (GridView) rootView.findViewById(R.id.gridview);
        MasterListAdapter adapter = new MasterListAdapter(getContext(), list);
        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent,
                                    View v,
                                    int position,
                                    long id)
            {
                mCallback.onImageSelected(position);
            }
        });
*/
        //buttonClickHandlers();
        render();

        return rootView;
    }

    public void setElements(List<Step> mSteps,
                            int i)
    {
        this.recipeStepIndex = i;
        this.mSteps = mSteps;
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
        String detail = step.getDescription();

        if(null==detail ||
                0==detail.length())
            detail = step.getShortDescription();

        TextView textView = (TextView) rootView.findViewById(R.id.detail_item);
        textView.setText(detail);
    }

    private void loadVideo(Step step)
    {
        Uri uri = step.getVideoUri();

        if(null == uri)
            uri = step.getThumbnailUri();

        VideoView videoView = (VideoView)rootView.findViewById(R.id.video_item);
        videoView.stopPlayback();
        videoView.setVideoURI(uri);
        videoView.start();
    }
}
