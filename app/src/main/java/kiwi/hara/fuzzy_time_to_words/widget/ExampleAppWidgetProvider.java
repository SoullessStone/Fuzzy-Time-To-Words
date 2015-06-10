package kiwi.hara.fuzzy_time_to_words.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Calendar;
import java.util.Random;

import kiwi.hara.fuzzy_time_to_words.R;
import kiwi.hara.fuzzy_time_to_words.helper.TimeGenerator;
import kiwi.hara.fuzzy_time_to_words.model.AbstractLanguage;
import kiwi.hara.fuzzy_time_to_words.model.BerndeutschGenerator;
import kiwi.hara.fuzzy_time_to_words.model.EnglishGenerator;
import kiwi.hara.fuzzy_time_to_words.model.HochdeutschGenerator;

/**
 * Created by SoullessStone on 10.06.2015.
 */
public class ExampleAppWidgetProvider extends AppWidgetProvider {
    private static final String ACTION_CLICK = "ACTION_CLICK";
    private static final String TAG = ExampleAppWidgetProvider.class.getSimpleName();

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {

        // Get all ids
        ComponentName thisWidget = new ComponentName(context,
                ExampleAppWidgetProvider.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        for (int widgetId : allWidgetIds) {

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.widget_layout);

            // Set the text
            AbstractLanguage lang = new BerndeutschGenerator();
            int random = new Random().nextInt(3);
            Log.v(TAG, "-> " + random);
            if (random==1)
                lang = new HochdeutschGenerator();
            if (random==2)
                lang = new EnglishGenerator();

            TimeGenerator generator = new TimeGenerator(Calendar.getInstance(), lang);
            remoteViews.setTextViewText(R.id.update, generator.toString());

            // Register an onClickListener
            Intent intent = new Intent(context, ExampleAppWidgetProvider.class);

            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.update, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }
}