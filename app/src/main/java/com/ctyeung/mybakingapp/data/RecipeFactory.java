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
    /*
     * methods below can be refactored by using an interface !!!
     */
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

    public static List<Step> StepsJsonArray2List(JSONArray stepsJSON)
    {
        List<Step> steps = new ArrayList<Step>();

        // insert ingredient as 1st step
        String str = "{\"shortDescription\":\"Ingredients\"}";
        Step step = new Step(str);
        steps.add(0, step);

        // insert detail steps
        for(int i=0; i<stepsJSON.length(); i++)
        {
            JSONObject json = JSONHelper.parseJsonFromArray(stepsJSON, i);
            step = new Step(json);
            steps.add(i+1, step);
        }
        return steps;
    }

    public static List<Ingredient> IngredientsJsonArray2List(JSONArray jsonArray)
    {
        List<Ingredient> ingredients = new ArrayList<Ingredient>();

        for(int i=0; i<jsonArray.length(); i++)
        {
            JSONObject json = JSONHelper.parseJsonFromArray(jsonArray, i);
            Ingredient ingredient = new Ingredient(json);
            ingredients.add(i, ingredient);
        }
        return ingredients;
    }
}
