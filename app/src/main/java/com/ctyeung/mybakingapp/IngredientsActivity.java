package com.ctyeung.mybakingapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.ctyeung.mybakingapp.data.Ingredient;
import com.ctyeung.mybakingapp.data.RecipeFactory;
import com.ctyeung.mybakingapp.data.Recipe;
import com.ctyeung.mybakingapp.data.SharedPrefUtil;
import com.ctyeung.mybakingapp.data.Step;
import com.ctyeung.mybakingapp.utility.JSONHelper;
import com.ctyeung.mybakingapp.RecipeListAdapter;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by ctyeung on 3/5/18.
 *
 * Reference: Jake Wharton's ButterKnife
 * http://jakewharton.github.io/butterknife/
 */

public class IngredientsActivity extends AppCompatActivity
{
    private StepIngredientsFragment mFragment;
    private FragmentManager mFragmentManager;

    private SharedPrefUtil mSharedPrefUtil;
    private int mSelectedPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        // get last know index
        mSharedPrefUtil = new SharedPrefUtil(getApplicationContext());
        mSelectedPos = mSharedPrefUtil.getStepSelected();

        Recipe recipe = parseIngredients();
        SetFragment(recipe);
    }

    private Recipe parseIngredients()
    {
        String str = this.getIntent().getStringExtra(Intent.EXTRA_TEXT);
        JSONObject json = JSONHelper.parseJson(str);
        return new Recipe(json);
    }

    private void SetFragment(Recipe recipe)
    {
        TextView textView = (TextView)findViewById(R.id.ingredients_error);

        if(null==recipe)
        {
            textView.setVisibility(View.VISIBLE);
        }
        else {
            textView.setVisibility(View.INVISIBLE);

            // create 2nd fragment
            mFragment = new StepIngredientsFragment();
            mFragment.setElement(recipe, mSelectedPos);
            mFragmentManager = getSupportFragmentManager();
            mFragmentManager.beginTransaction()
                    .add(R.id.frame_ingredients, mFragment)
                    .commit();
        }
    }

    @Override
    protected void onDestroy()
    {
        mSharedPrefUtil.setIngredientSelected(mSelectedPos);
        super.onDestroy();
    }
}
