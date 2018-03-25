package com.ctyeung.mybakingapp.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ctyeung on 3/24/18.
 */

public class SharedPrefUtil {

    SharedPreferences sharedPreferences;

    public static final String mypreference = "mypref";
    public static final String STEP_SELECTED = "step";

    private Context context;

    public SharedPrefUtil(Context context)
    {
        this.context = context;
        sharedPreferences = this.context.getSharedPreferences(mypreference, Context.MODE_PRIVATE);
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
}
