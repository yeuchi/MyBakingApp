package com.ctyeung.mybakingapp.utility;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Created by ctyeung on 2/25/18.
 */

public class JSONHelper
{
    public static JSONArray parseJsonArray(String str)
    {
        JSONArray jsonArray = null;

        try
        {
            jsonArray = new JSONArray(str);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return jsonArray;
    }

    public static JSONObject parseJson(String str)
    {
        JSONObject json = null;

        try
        {
            json = new JSONObject(str);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return json;
    }

    public static JSONArray getJsonArray(JSONObject json, String key)
    {
        JSONArray jsonArray = null;
        try
        {
            jsonArray = json.getJSONArray(key);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return jsonArray;
    }

    public static JSONArray getJsonArray(String jsonString, String key)
    {
        if(null==jsonString)
            return null;

        JSONObject json = JSONHelper.parseJson(jsonString);

        if (null == json)
            return null;

        JSONArray jsonArray = JSONHelper.getJsonArray(json, key);
        return jsonArray;
    }

    public static JSONObject parseJsonFromArray(JSONArray jsonArray, int index)
    {
        JSONObject json = null;

        try
        {
            json = jsonArray.getJSONObject(index);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return json;
    }

    public static String parseValueByKey(JSONObject json, String key)
    {
        String str = null;

        try
        {
            str = json.getString(key);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return str;
    }
}

