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
}
