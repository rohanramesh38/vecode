package com.example.veccode.design;


import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.veccode.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebFragment extends Fragment {


private WebView webView;
    public WebFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

    String url=  getArguments().getString("data");
        String name=  getArguments().getString("name");

        View root= inflater.inflate(R.layout.fragment_web, container, false);

        webView=root.findViewById(R.id.web);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

        toolbar.setTitle(name);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSaveFormData(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webView.getSettings().setSupportZoom(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setClickable(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setAppCachePath(getContext().getCacheDir().getPath());
        webView.loadUrl(url);
        webView.getSettings().setUserAgentString("VecCode");

        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        webView.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    WebView webView = (WebView) v;

                    switch(keyCode)
                    {
                        case KeyEvent.KEYCODE_BACK:
                            if(webView.canGoBack())
                            {
                                webView.goBack();
                                return true;
                            }
                            break;
                    }
                }

                return false;
            }
        });


        webView.setWebChromeClient(new WebChromeClient() {


            @Override
            public boolean onCreateWindow(WebView view, boolean isDialog,
                                          boolean isUserGesture, Message resultMsg) {



                WebView newWebView = new WebView(getContext());
                newWebView.getSettings().setJavaScriptEnabled(true);
                newWebView.getSettings().setSupportZoom(true);
                newWebView.getSettings().setBuiltInZoomControls(true);
                newWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
                newWebView.getSettings().setSupportMultipleWindows(true);
                view.addView(newWebView);
                WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
                transport.setWebView(newWebView);
                resultMsg.sendToTarget();

                newWebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }
                });

                return true;
            }
        });



    return root;
    }



}
