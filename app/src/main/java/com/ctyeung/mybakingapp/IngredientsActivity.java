package com.ctyeung.mybakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;

import com.ctyeung.mybakingapp.data.Ingredient;
import com.ctyeung.mybakingapp.data.RecipeFactory;
import com.ctyeung.mybakingapp.data.Recipe;
import com.ctyeung.mybakingapp.data.Step;
import com.ctyeung.mybakingapp.utility.JSONHelper;
import com.ctyeung.mybakingapp.RecipeListAdapter;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by ctyeung on 3/5/18.
 */

public class IngredientsActivity extends AppCompatActivity
        implements com.ctyeung.mybakingapp.IngredientListAdapter.ListItemClickListener

{
    private RecyclerView mIngredientList;
    private IngredientListAdapter.ListItemClickListener mListener;
    private IngredientListAdapter mListAdapter;
    private List<Ingredient> mIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        GridLayoutManager reviewManager = new GridLayoutManager(this, 1);
        mIngredientList = (RecyclerView) findViewById(R.id.ingredient_list);
        mIngredientList.setLayoutManager(reviewManager);
        mListener = this;
        parseIngredients();
    }

    private void parseIngredients()
    {
        String str = this.getIntent().getStringExtra(Intent.EXTRA_TEXT);
        JSONObject json = JSONHelper.parseJson(str);
        Recipe recipe = new Recipe(json);
        mIngredients = RecipeFactory.IngredientsJsonArray2List(recipe.getIngredients());

        mListAdapter = new IngredientListAdapter(mIngredients.size(), mListener, mIngredients);
        mIngredientList.setAdapter(mListAdapter);
        mIngredientList.setHasFixedSize(true);
    }

    @Override
    public void onListItemClick(int clickItemIndex)
    {

    }

}
