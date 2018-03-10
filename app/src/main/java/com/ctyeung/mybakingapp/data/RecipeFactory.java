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
    public static List<Recipe> Create(JSONArray recipeJSON)
    {
        List<Recipe> recipes = new ArrayList<Recipe>();
        for(int i=0; i<recipeJSON.length(); i++)
        {
            JSONObject json = JSONHelper.parseJsonFromArray(recipeJSON, i);
            Recipe recipe = new Recipe(json);
            recipes.add(i, recipe);
        }
        return recipes;
    }

    public static List<Step> GetDetails(JSONArray detailJSON)
    {
        List<Step> steps = new ArrayList<Step>();

        // insert ingredient as 1st step
        String str = "{\"shortDescription\":\"Ingredients\"}";
        Step step = new Step(str);
        steps.add(0, step);

        // insert detail steps
        for(int i=0; i<detailJSON.length(); i++)
        {
            JSONObject json = JSONHelper.parseJsonFromArray(detailJSON, i);
            step = new Step(json);
            steps.add(i+1, step);
        }
        return steps;
    }
}
