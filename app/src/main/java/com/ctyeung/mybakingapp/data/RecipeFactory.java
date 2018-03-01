package com.ctyeung.mybakingapp.data;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import com.ctyeung.mybakingapp.utility.JSONHelper;

/**
 * Created by ctyeung on 2/25/18.
 */

public class RecipeFactory
{
    public static List<Recipe> Create(JSONArray movieList)
    {
        List<Recipe> recipes = new ArrayList<Recipe>();
        for(int i=0; i<movieList.length(); i++)
        {
            JSONObject json = JSONHelper.parseJsonFromArray(movieList, i);
            Recipe recipe = new Recipe(json);
            recipes.add(i, recipe);
        }
        return recipes;
    }
}
