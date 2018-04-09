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
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        int appWidgetId = intent.getIntExtra(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

        return (new ListProvider(this.getApplicationContext(), intent));
    }
}
