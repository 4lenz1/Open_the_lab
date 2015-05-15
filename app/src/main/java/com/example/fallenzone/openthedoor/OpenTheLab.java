package com.example.fallenzone.openthedoor;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RemoteViews;


/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link OpenTheLabConfigureActivity OpenTheLabConfigureActivity}
 */
public class OpenTheLab extends AppWidgetProvider {

    private WebView webView;
    private String username = "M5B01";
    private String pwd = "c@dla3";
    private String url = "http://163.25.117.185/OGWeb/LoginForm.aspx";
    private Button button;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }

        //RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.open_the_lab);
       // remoteViews.setOnClickPendingIntent(R.id.button, buildButtonPendingIntent(context));
       // button = (Button) fi
        webView.loadUrl("http://163.25.117.185/OGWeb/OGWebGuard/OGDOutActionPage.aspx");
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {

                Log.v("Progress", Integer.toString(progress));
                if (progress == 100) {

                    if (webView.getUrl().equals("http://163.25.117.185/OGWeb/LoginForm.aspx?ReturnUrl=%2fOGWeb%2fOGWebGuard%2fOGDOutActionPage.aspx")) {
                        // Login
                        view.loadUrl("javascript:var x = document.getElementById('UserAccount').value = '" +
                                username + "';");
                        view.loadUrl("javascript:var y = document.getElementById('UserPassword').value = '" +
                                pwd + "';");
                        view.loadUrl("javascript:(function(){" +
                                "l=document.getElementById('LoginBtn');" +
                                "e=document.createEvent('HTMLEvents');" +
                                "e.initEvent('click',true,true);" +
                                "l.dispatchEvent(e);" +
                                "})()");

                    } else if (webView.getUrl().equals("http://163.25.117.185/OGWeb/Default.aspx")) {

                        webView.loadUrl("http://163.25.117.185/OGWeb/OGWebGuard/OGDOutActionPage.aspx");
                    } else if (webView.getUrl().equals("http://163.25.117.185/OGWeb/OGWebGuard/OGDOutActionPage.aspx")) {

                        view.loadUrl("javascript:(function(){" +
                                "l=document.getElementById('OGDOutActionCtrl_DeviceList_ctl00_DeviceBtn_Button');" +
                                "e=document.createEvent('HTMLEvents');" +
                                "e.initEvent('click',true,true);" +
                                "l.dispatchEvent(e);" +
                                "})()");

                    }
                }
            } // end onProgressedChanged
        });

    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            OpenTheLabConfigureActivity.deleteTitlePref(context, appWidgetIds[i]);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
     //   button = (Button) findViewById
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = OpenTheLabConfigureActivity.loadTitlePref(context, appWidgetId);
        // Construct the RemoteViews object
//        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.open_the_lab);
//        views.setTextViewText(R.id.appwidget_text, widgetText);
        // Instruct the widget manager to update the widget
        //appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}

