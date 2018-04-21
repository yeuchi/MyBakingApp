package com.ctyeung.mybakingapp;

import android.content.res.Resources;
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
import com.ctyeung.mybakingapp.data.SharedPrefUtil;
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
    private View mRootView;
    private SharedPrefUtil mSharedPrefUtil;
    private Context mContext;
    private int scrollY = 0;
    private int mSelectedIngredientIndex;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle saveInstanceState)
    {
        mRootView = inflater.inflate(R.layout.fragment_ingredients, container, false);
        mContext = mRootView.getContext();

        // get last know index
        mSharedPrefUtil = new SharedPrefUtil(mContext);

        GridLayoutManager reviewManager = new GridLayoutManager(mContext, 1);
        mIngredientList = (RecyclerView) mRootView.findViewById(R.id.ingredient_list);
        mIngredientList.setLayoutManager(reviewManager);
        mListener = this;

        // new ingredient list - selection from prior rotation
        mListAdapter = new IngredientListAdapter(mListener, mIngredients);
        mSelectedIngredientIndex = mSharedPrefUtil.getIngredientSelected();
        mListAdapter.mSelectedPosition = mSelectedIngredientIndex;

        mIngredientList.setAdapter(mListAdapter);
        mIngredientList.setHasFixedSize(true);

        // set scroll position from prior rotation
        scrollY = mSharedPrefUtil.getIngredListScrollPos();
        mIngredientList.smoothScrollToPosition(scrollY);

        // set scroll event listener
        mIngredientList.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                scrollY += dy;
                mSharedPrefUtil.setIngredListScrollPos(scrollY);
            }
        });
        return mRootView;
    }

    @Override
    public void setElement(Recipe recipe,           // recipe of choice
                           int selectedStepIndex)   // selected step index -- don't use
    {
        // don't use selectedStepIndex
        mIngredients = RecipeFactory.IngredientsJsonArray2List(recipe.getIngredients());
        IngredientListAdapter.mViewHolderCount = 0;
    }

    @Override
    public void onListItemClick(int clickItemIndex)
    {
        mSelectedIngredientIndex = clickItemIndex;
        // persist the value into shared preference if rotate
    }

    public void onAttach(Context context)
    {
        super.onAttach(context);

        try
        {
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString() +
                    mContext.getResources().getString(R.string.implement_click));
        }
    }

    @Override
    public void onDestroy()
    {
        mSharedPrefUtil.setIngredientSelected(mSelectedIngredientIndex);
        super.onDestroy();
    }

}
