package com.mobile.mywidgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class RandomNumberWidget extends AppWidgetProvider {

     void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = "appwidget_text";
        String lastUpdate = "Random: " + NumberGenerator.Generate(100);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.random_number_widget);

        views.setTextViewText(R.id.appwidget_text, lastUpdate);

        views.setOnClickPendingIntent(R.id.btn_click, getPendingSlefIntent(context, appWidgetId, WIDGET_CLICK));
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    public static final String WIDGET_CLICK = "widgetsclicj";
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (WIDGET_CLICK.equals(intent.getAction())) {
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.random_number_widget);
            String lastUpdate = "Random:" + NumberGenerator.Generate(100);
            int appWidgetId = intent.getIntExtra(WIDGET_ID_EXTRA, 0);
            views.setTextViewText(R.id.appwidget_text, lastUpdate);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    public static final String WIDGET_ID_EXTRA = "widget_id_Extra";

    protected PendingIntent getPendingSlefIntent(Context context, int appWidgetId, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        intent.putExtra(WIDGET_ID_EXTRA, appWidgetId);
        return PendingIntent.getBroadcast(context, appWidgetId, intent, 0);
    }
}

