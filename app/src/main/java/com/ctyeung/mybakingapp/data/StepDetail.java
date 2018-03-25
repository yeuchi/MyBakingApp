package com.ctyeung.mybakingapp.data;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by ctyeung on 3/25/18.
 */

public class StepDetail
{

    protected JSONObject json;

    public StepDetail(String str )
    {
        try
        {
            this.json = new JSONObject(str);
        }
        catch(Exception ex)
        {
            Log.e("Step() constructor",Log.getStackTraceString(ex));
        }
    }

    public StepDetail(JSONObject json)
    {
        this.json = json;

    }
}
