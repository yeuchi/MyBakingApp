package com.ctyeung.mybakingapp.data;

import org.json.JSONObject;

/**
 * Created by ctyeung on 3/6/18.
 */
import com.ctyeung.mybakingapp.utility.JSONHelper;

import org.json.JSONObject;

public class Step
{
    private JSONObject json;
    private String recipe;

    public Step()
    {

    }

    public Step(JSONObject json)
    {
        this.json = json;

    }

    public String getShortDescription()
    {
        return JSONHelper.parseValueByKey(json, "shortDescription");
    }

    public String getJSONString()
    {
        return json.toString();
    }
}
