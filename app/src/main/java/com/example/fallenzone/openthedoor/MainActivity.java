package com.example.fallenzone.openthedoor;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JsResult;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.content.Context;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.widget.Toast;

import java.util.logging.Logger;

public class MainActivity extends ActionBarActivity {

    private WebView webView;
    private TextView textView , txtUrl;
    private String username = "M5B01";
    private String pwd = "c@dla3";
    private String url = "http://163.25.117.185/OGWeb/LoginForm.aspx";
    private boolean ctrlLock = false ;
    private  boolean stopOrAuto ;
    private Button btnAutoLock , btnStopAutoLock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        webView = (WebView) findViewById(R.id.webView);
        txtUrl = (TextView) findViewById(R.id.txtUrl);

        btnAutoLock = (Button) findViewById(R.id.btnAutoLock);
        btnStopAutoLock = (Button) findViewById(R.id.btnStopAutoLock);

        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDomStorageEnabled(true);
        final WebSettings webSettings = webView.getSettings();
        webSettings.setDomStorageEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.clearCache(true);

        final Activity activity = this;
        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });

        webView.loadUrl("http://163.25.117.185/OGWeb/OGWebGuard/OGDOutActionPage.aspx");
        textView.setText("never uesd");
        txtUrl.setText(webView.getUrl());



        webView.clearHistory();
        final WebView webview = new WebView(this);
        WebSettings ws = webview.getSettings();
        ws.setSaveFormData(false);

        webView.setWebViewClient(new WebViewClient());

        //Auto lock click event
        final String urlCtrlLock = "http://163.25.117.185/OGWeb/OGWebGuard/OGDoorLatchActionPage.aspx" ;
        btnAutoLock.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                webView.loadUrl(urlCtrlLock);
                ctrlLock = true;
                stopOrAuto = true;
            }
        });
        btnStopAutoLock.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                webView.loadUrl(urlCtrlLock);
                ctrlLock = true;
                stopOrAuto = false;
            }
        });


        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                Log.v("Progress", Integer.toString(progress));
                if (progress == 100) {
                    txtUrl.setText( webView.getUrl());
                    if (ctrlLock == true){
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
                            textView.setText("Login");
                        }
                    }else if ( webView.getUrl().equals("http://163.25.117.185/OGWeb/Default.aspx")){
                        txtUrl.setText( webView.getUrl());
                        webView.loadUrl("http://163.25.117.185/OGWeb/OGWebGuard/OGDoorLatchActionPage.aspx");
                    }else if ( webView.getUrl().equals("http://163.25.117.185/OGWeb/OGWebGuard/OGDoorLatchActionPage.aspx")) {
                        txtUrl.setText( webView.getUrl());
                        if (stopOrAuto == true) {
                            view.loadUrl("javascript:(function(){" +
                                    "l=document.getElementById('OGDoorLatchActionCtrl_DeviceLatchOnList_ctl00_DeviceOnBtn_Button');" +
                                    "e=document.createEvent('HTMLEvents');" +
                                    "e.initEvent('click',true,true);" +
                                    "l.dispatchEvent(e);" +
                                    "})()");
                            textView.setText("ctrl Click Auto");
                        }else{
                            view.loadUrl("javascript:(function(){" +
                                    "l=document.getElementById('OGDoorLatchActionCtrl_DeviceLatchOffList_ctl00_DeviceOffBtn_Button');" +
                                    "e=document.createEvent('HTMLEvents');" +
                                    "e.initEvent('click',true,true);" +
                                    "l.dispatchEvent(e);" +
                                    "})()");
                            textView.setText("ctrl Click Stop");
                        }
                        ctrlLock = false;
                    }


                    if (webView.getUrl().equals("http://163.25.117.185/OGWeb/LoginForm.aspx?ReturnUrl=%2fOGWeb%2fOGWebGuard%2fOGDOutActionPage.aspx") && ctrlLock == false) {
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
                        textView.setText("Login");
                    }else if ( webView.getUrl().equals("http://163.25.117.185/OGWeb/Default.aspx")){
                        txtUrl.setText( webView.getUrl());
                        webView.loadUrl("http://163.25.117.185/OGWeb/OGWebGuard/OGDOutActionPage.aspx");
                    }else if ( webView.getUrl().equals("http://163.25.117.185/OGWeb/OGWebGuard/OGDOutActionPage.aspx")) {
                        txtUrl.setText( webView.getUrl());
                        view.loadUrl("javascript:(function(){" +
                                "l=document.getElementById('OGDOutActionCtrl_DeviceList_ctl00_DeviceBtn_Button');" +
                                "e=document.createEvent('HTMLEvents');" +
                                "e.initEvent('click',true,true);" +
                                "l.dispatchEvent(e);" +
                                "})()");
                        textView.setText("Click Open");
                    }
                }
            } // end onProgressedChanged
            @Override
            public boolean onJsAlert(WebView view, final String url, String message,
                                     JsResult result) {

                AlertDialog.Builder builder = new AlertDialog.Builder(
                        MainActivity.this);
                builder.setMessage(message)
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                arg0.dismiss();
                            }
                        }).show();
                result.confirm();
                return true;
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
