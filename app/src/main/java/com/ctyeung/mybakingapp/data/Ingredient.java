package com.ctyeung.mybakingapp.data;

/**
 * Created by ctyeung on 3/6/18.
 */

import android.content.Context;

import com.ctyeung.mybakingapp.utility.JSONHelper;

import org.json.JSONObject;

public class Ingredient extends StepDetail
{
    public static final String KEY_QUANTITY = "quantity";
    public static final String KEY_MEASURE = "measure";
    public static final String KEY_INGREDIENT = "ingredient";

    public Ingredient(String str,
                      Context context)
    {
        super(str, context);
    }

    public Ingredient(JSONObject json)
    {
        super(json);
    }

    public String getJSONString()
    {
        return mJson.toString();
    }

    public String getQuantity()
    {
        return JSONHelper.parseValueByKey(mJson, KEY_QUANTITY);
    }

    public String getMeasure()
    {
        return JSONHelper.parseValueByKey(mJson, KEY_MEASURE);
    }

    public String getIngredient()
    {
        return JSONHelper.parseValueByKey(mJson, KEY_INGREDIENT);
    }
}
