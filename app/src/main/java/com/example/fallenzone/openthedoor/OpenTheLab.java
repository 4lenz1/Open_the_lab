package com.example.fallenzone.openthedoor;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RemoteViews;
import android.widget.TextView;


/**
 * Implementation of App Widget functionality.
 */
public class OpenTheLab extends AppWidgetProvider {

    private WebView webView;
    private TextView textView;
    private String username = "M5B01";
    private String pwd = "c@dla3";
    private String url = "http://163.25.117.185/OGWeb/LoginForm.aspx";
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
//        textView = (TextView) findViewById(R.id.textView);
//        webView = (WebView) findViewById(R.id.webView);

        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }
//        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setBuiltInZoomControls(true);
//        webView.getSettings().setDomStorageEnabled(true);
//        final WebSettings webSettings = webView.getSettings();
//        webSettings.setDomStorageEnabled(true);
//        webView.getSettings().setDomStorageEnabled(true);
//        webView.clearCache(true);
//        webView.loadUrl(url);
//        textView.setText("never uesd");
//
//        webView.setWebViewClient(new WebViewClient());
//        webView.setWebViewClient(new WebViewClient() {
//            public void onPageFinished(WebView view, String url) {
//                //   if (progress == 100) {
//                view.loadUrl("javascript:var x = document.getElementById('UserAccount').value = '" +
//                        username + "';");
//                view.loadUrl("javascript:var y = document.getElementById('UserPassword').value = '" +
//                        pwd + "';");
//                view.loadUrl("javascript:(function(){" +
//                        "l=document.getElementById('LoginBtn');" +
//                        "e=document.createEvent('HTMLEvents');" +
//                        "e.initEvent('click',true,true);" +
//                        "l.dispatchEvent(e);" +
//                        "})()");
//                textView.setText("Submitted");
//            }
//            //  }
//
//        });
//        //webView.reload();
//        webView.loadUrl("http://163.25.117.185/OGWeb/OGWebGuard/OGDOutActionPage.aspx");
//
//        webView.setWebChromeClient(new WebChromeClient() {
//            public void onProgressChanged(WebView view, int progress) {
//                if (progress == 100) {
//                    view.loadUrl("javascript:(function(){" +
//                            "l=document.getElementById('OGDOutActionCtrl_DeviceList_ctl00_DeviceBtn_Button');" +
//                            "e=document.createEvent('HTMLEvents');" +
//                            "e.initEvent('click',true,true);" +
//                            "l.dispatchEvent(e);" +
//                            "})()");
//                    textView.setText("Submitted open");
//                }
//                //webView.loadUrl("http://163.25.117.185/OGWeb/OGWebGuard/OGDOutActionPage.aspx");
//            }
//        });
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.open_the_lab);
//        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}

