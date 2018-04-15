package com.ctyeung.mybakingapp.data;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;
import com.ctyeung.mybakingapp.R;
import android.content.res.Resources;

/**
 * Created by ctyeung on 3/25/18.
 */

public class StepDetail
{

    protected JSONObject mJson;

    public StepDetail(String str,
                      Context context)
    {
        try
        {
            mJson = new JSONObject(str);
        }
        catch(Exception ex)
        {
            String string = context.getResources().getString(R.string.step_construct);
            Log.e(string,Log.getStackTraceString(ex));
        }
    }

    public StepDetail(JSONObject json)
    {
        mJson = json;

    }
}
