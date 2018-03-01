package com.ctyeung.mybakingapp.data;
/**
 * Created by ctyeung on 2/25/18.
 */

import com.ctyeung.mybakingapp.utility.JSONHelper;

import org.json.JSONObject;

public class Recipe
{
    private JSONObject json;

    public Recipe()
    {

    }

    public Recipe(JSONObject json)
    {
        this.json = json;
    }

    public String getId()
    {
        return JSONHelper.parseValueByKey(json, "id");
    }

    public String getName()
    {
        return JSONHelper.parseValueByKey(json, "name");
    }

    public String getIngredients()
    {
        return JSONHelper.parseValueByKey(json, "ingredient");
    }
}
