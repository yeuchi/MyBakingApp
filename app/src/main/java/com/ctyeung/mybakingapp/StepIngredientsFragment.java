package com.ctyeung.mybakingapp;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ctyeung.mybakingapp.data.Ingredient;
import com.ctyeung.mybakingapp.data.Recipe;
import com.ctyeung.mybakingapp.data.RecipeFactory;
import com.ctyeung.mybakingapp.utility.JSONHelper;

import org.json.JSONObject;
import android.content.Context;

import java.util.List;

/**
 * Created by ctyeung on 3/25/18.
 */

public class StepIngredientsFragment extends BaseFragment
        implements com.ctyeung.mybakingapp.IngredientListAdapter.ListItemClickListener
{
    private RecyclerView mIngredientList;
    private IngredientListAdapter.ListItemClickListener mListener;
    private IngredientListAdapter mListAdapter;
    private List<Ingredient> mIngredients;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle saveInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_ingredients, container, false);
        Context context = rootView.getContext();

        GridLayoutManager reviewManager = new GridLayoutManager(context, 1);
        mIngredientList = (RecyclerView) rootView.findViewById(R.id.ingredient_list);
        mIngredientList.setLayoutManager(reviewManager);
        mListener = this;

        mListAdapter = new IngredientListAdapter(mListener, mIngredients);
        mIngredientList.setAdapter(mListAdapter);
        mIngredientList.setHasFixedSize(true);

        return rootView;
    }

    @Override
    public void setElement(Recipe recipe)
    {
        mIngredients = RecipeFactory.IngredientsJsonArray2List(recipe.getIngredients());

        IngredientListAdapter.mViewHolderCount = 0;
    }

    @Override
    public void onListItemClick(int clickItemIndex)
    {
        // nothing to do here ... maybe highlight selection ?
    }

    public void onAttach(Context context)
    {
        super.onAttach(context);

        try
        {
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString() + "must implement OnClickListener");
        }
    }

}
