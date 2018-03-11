package com.ctyeung.mybakingapp.data;

import android.net.Uri;
import android.util.Log;

import org.json.JSONObject;

/**
 * Created by ctyeung on 3/6/18.
 */
import com.ctyeung.mybakingapp.utility.JSONHelper;

import org.json.JSONObject;

import java.net.URL;

public class Step
{
    private JSONObject json;

    public Step(String str )
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

    public Step(JSONObject json)
    {
        this.json = json;

    }

    public String getShortDescription()
    {
        return JSONHelper.parseValueByKey(json, "shortDescription");
    }

    public String getDescription()
    {
        return JSONHelper.parseValueByKey(json, "description");
    }

    public Uri getVideoUri()
    {
        return parseUri("videoURL");
    }

    public Uri getThumbnailUri()
    {
        return parseUri( "thumbnailUrl");
    }

    private Uri parseUri(String key)
    {
        String str = JSONHelper.parseValueByKey(json, key);
        Uri uri = (null==str || 0==str.length())?
                    null:
                    Uri.parse(str);

        return uri;
    }

    public String getJSONString()
    {
        return json.toString();
    }
}
