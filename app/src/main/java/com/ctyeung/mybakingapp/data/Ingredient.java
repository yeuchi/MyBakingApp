package com.ctyeung.mybakingapp.data;

/**
 * Created by ctyeung on 3/6/18.
 */

import com.ctyeung.mybakingapp.utility.JSONHelper;

import org.json.JSONObject;

public class Ingredient
{
    private JSONObject json;
    private String recipe;

    public Ingredient()
    {

    }

    public Ingredient(JSONObject json)
    {
        this.json = json;

    }

    public String getJSONString()
    {
        return json.toString();
    }

    public String getQuantity()
    {
        return JSONHelper.parseValueByKey(json, "quantity");
    }

    public String getMeasure()
    {
        return JSONHelper.parseValueByKey(json, "measure");
    }

    public String getIngredient()
    {
        return JSONHelper.parseValueByKey(json, "ingredient");
    }
}
