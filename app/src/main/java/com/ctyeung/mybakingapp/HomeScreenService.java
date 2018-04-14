package com.ctyeung.mybakingapp;

import android.widget.RemoteViewsService;
import android.appwidget.AppWidgetManager;
import android.content.Intent;

/**
 * Created by ctyeung on 4/8/18.
 */

public class HomeScreenService extends RemoteViewsService
{
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent)
    {
        return(new WidgetViewsFactory(this.getApplicationContext(),
                intent));
    }
}
