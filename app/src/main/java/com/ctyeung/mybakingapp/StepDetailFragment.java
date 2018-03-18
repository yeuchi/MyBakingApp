package com.ctyeung.mybakingapp;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ctyeung on 3/17/18.
 */

public class StepDetailFragment extends Fragment
{
    private View rootView;
    private int index;

    public void setIndex(int i)
    {
        index = i;
        // load detail
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
        return rootView;
    }
}
