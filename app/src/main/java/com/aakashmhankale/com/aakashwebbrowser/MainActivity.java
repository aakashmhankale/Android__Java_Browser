package com.aakashmhankale.com.aakashwebbrowser;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    WebView brow;
    EditText urltext;
    Button Go,Forward,Back,Reload,Clear;
    ProgressBar progressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        brow=(WebView)findViewById(R.id.vw_brow);
        urltext= (EditText)findViewById(R.id.et_text);
        Go=(Button)findViewById(R.id.et_btn);
        Forward=(Button)findViewById(R.id.btn_frw);
        Back=(Button)findViewById(R.id.btn_back);
        Reload=(Button)findViewById(R.id.btn_reload);
        Clear=(Button)findViewById(R.id.btn_clear);
        progressbar=(ProgressBar)findViewById(R.id.wv_pr);

        brow.setWebViewClient(new ourViewClient());

        brow.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                progressbar.setProgress(newProgress);

                if(newProgress==100){
                    progressbar.setVisibility(view.GONE);
                }else{
                    progressbar.setVisibility(view.VISIBLE);
                }
            }
        });

        WebSettings websettings = brow.getSettings();
        websettings.setJavaScriptEnabled(true);

        Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editurlstring= urltext.getText().toString();


                if(!editurlstring.startsWith("https://"))
                    editurlstring= "https://"+editurlstring;

                String url=editurlstring;
                brow.loadUrl(url);

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromInputMethod(urltext.getWindowToken(),0);
            }
        });

        Forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(brow.canGoForward())
                    brow.goForward();

            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(brow.canGoBack())
                    brow.goBack();

            }
        });

        Reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                brow.reload();

            }
        });

        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                brow.clearHistory();

            }
        });

    }
}
