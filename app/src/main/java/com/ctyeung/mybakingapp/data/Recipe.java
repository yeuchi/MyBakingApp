package com.ctyeung.mybakingapp.data;
/**
 * Created by ctyeung on 2/25/18.
 */

import com.ctyeung.mybakingapp.utility.JSONHelper;

import org.json.JSONArray;
import org.json.JSONObject;

public class Recipe
{
    private JSONObject json;
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_INGREDIENTS = "ingredients";
    private static final String KEY_STEPS = "steps";

    public Recipe(JSONObject json)
    {
        this.json = json;
    }

    public String getId()
    {
        return JSONHelper.parseValueByKey(json, KEY_ID);
    }

    public String getName()
    {
        return JSONHelper.parseValueByKey(json, KEY_NAME);
    }

    /*
     * below 2 methods can be consolidated !!!!
     */
    public JSONArray getIngredients()
    {
        String str = JSONHelper.parseValueByKey(json, KEY_INGREDIENTS);
        JSONArray array = JSONHelper.parseJsonArray(str);
        return array;
    }

    public JSONArray getSteps()
    {
        String str = JSONHelper.parseValueByKey(json, KEY_STEPS);
        JSONArray array = JSONHelper.parseJsonArray(str);
        return array;
    }

    public String getJSONString()
    {
        return json.toString();
    }
}
