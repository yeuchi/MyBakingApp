package com.ctyeung.mybakingapp.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import com.ctyeung.mybakingapp.R;

/**
 * Created by ctyeung on 3/24/18.
 */

public class SharedPrefUtil {

    SharedPreferences sharedPreferences;

    public static final String VIDEO_AUTO = "videoAuto";
    public static final String VIDEO_POS = "video";
    public static final String STEP_SELECTED = "step";
    public static final String DETAIL_SELECTED = "detail";
    public static final String INGREDIENT_SELECTED = "ingredient";
    public static final String INGREDIENTS_SELECTED = "ingredients";
    public static final String mPref = "mypref";

    private Context mContext;

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

    public int getDetailSelected()
    {
        return sharedPreferences.getInt(DETAIL_SELECTED, 1);
    }

    public void setDetailSelected(int index)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(DETAIL_SELECTED, index);
        editor.commit();
    }

    public long getVideoPosition()
    {

        return sharedPreferences.getLong(VIDEO_POS, 1);
    }

    public void setVideoPosition(long pos)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(VIDEO_POS, pos);
        editor.commit();

        // debug
        long p = getVideoPosition();
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
        return sharedPreferences.getString(INGREDIENTS_SELECTED, mContext.getResources().getString(R.string.not_available));
    }

    public void setIngredients(String str)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(INGREDIENTS_SELECTED, str);
        editor.commit();
    }

    public boolean getAutoPlayValue()
    {
        return sharedPreferences.getBoolean(VIDEO_AUTO, false);
    }

    public void setAutoPlayValue(boolean isAuto)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(VIDEO_AUTO, isAuto);
        editor.commit();

        // debug
        boolean b = getAutoPlayValue();
    }
}
