package com.ctyeung.mybakingapp.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ctyeung on 3/24/18.
 */

public class SharedPrefUtil {

    SharedPreferences sharedPreferences;

    public static final String STEP_SELECTED = "step";
    public static final String INGREDIENT_SELECTED = "ingredient";
    public static final String INGREDIENTS_SELECTED = "ingredients";

    private Context mContext;
    public static final String mPref = "mypref";

    public SharedPrefUtil(Context context)
    {
        mContext = context;
        sharedPreferences = mContext.getSharedPreferences(mPref, Context.MODE_PRIVATE);
    }

    public int getStepSelected()
    {
        return sharedPreferences.getInt(STEP_SELECTED, 1);
    }

    public void setStepSelected(int index)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(STEP_SELECTED, index);
        editor.commit();
    }

    public int getIngredientSelected()
    {

        return sharedPreferences.getInt(INGREDIENT_SELECTED, 1);
    }

    public void setIngredientSelected(int index)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(INGREDIENT_SELECTED, index);
        editor.commit();
    }

    public String getIngredients()
    {
        return sharedPreferences.getString(INGREDIENTS_SELECTED, "not available");
    }

    public void setIngredients(String str)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(INGREDIENTS_SELECTED, str);
        editor.commit();
    }
}
