package com.example.webview;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;

import android.webkit.WebView;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
//import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MainActivity extends Activity  {


    private  EditText addressBar;

    private WebView webView;
    private Button buttonGo;
    private Button buttonStatic;
    private ImageButton btnBack,btnForward,btnReload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGo =(Button)findViewById(R.id.button_go);
        buttonStatic =(Button)findViewById(R.id.button_static);

        addressBar =(EditText)findViewById(R.id.editText_addressBar);
        webView =(WebView)findViewById(R.id.webView);

        btnForward = (ImageButton) findViewById(R.id.button_forward);
        btnBack = (ImageButton) findViewById(R.id.button_back);
        btnReload = (ImageButton) findViewById(R.id.button_reload);




        // Tùy biến WebViewClient để điều khiển các sự kiện trên WebView
        webView.setWebViewClient(new MyWebViewClient(addressBar));


        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (webView.canGoBack()){
                    webView.goBack();
                    addressBar.setText(webView.getOriginalUrl());
                    Toast.makeText(MainActivity.this,"previous page is loaded",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this,"no previous page",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnForward.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (webView.canGoForward()){
                    webView.goForward();
                    addressBar.setText(webView.getOriginalUrl());
                    Toast.makeText(MainActivity.this,"forward page is loaded",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this,"no forward page",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnReload.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                    webView.reload();
                    addressBar.setText(webView.getUrl());
                    Toast.makeText(MainActivity.this,"page is reloaded",Toast.LENGTH_LONG).show();
            }
        });

        buttonGo.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                goUrl();
            }
        });

        buttonStatic.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStaticContent();
            }
        });
    }

    private void goUrl()  {
        String url = addressBar.getText().toString().trim();
        if(url.isEmpty())  {
            Toast.makeText(this,"Please enter url",Toast.LENGTH_SHORT).show();
            return;
        }
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);
        //swipe.setRefreshing(true);
    }


    private void showStaticContent()  {
        String staticContent="<h2>Select web page</h2>"
                + "<ul><li><a href='http://eclipse.org'>Eclipse</a></li>"
                +"<li><a href='http://google.com'>Google</a></li></ul>";
        webView.loadData(staticContent, "text/html", "UTF-8");
    }

}
