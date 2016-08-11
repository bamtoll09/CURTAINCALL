package zer0pen.us.gp_ysy;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private View header, touchView;
    public static WebView webView;
    public static DrawerLayout drawer;
    private ListView navView;
    private FavoritAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        touchView = (View) findViewById(R.id.touch_view);
        webView = (WebView) findViewById(R.id.webView);
        navView = (ListView) findViewById(R.id.nav_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        adapter = new FavoritAdapter(this, R.layout.list_favorite);

        header = getLayoutInflater().inflate(R.layout.list_favorite_header, null, false);

        navView.addHeaderView(header);
        navView.setAdapter(adapter);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                isHome();
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //webView.loadUrl("http://www.google.co.kr");
        webView.loadUrl("http://sunrin.graphics/2016/");

        /*Log.d("asdf", webView.getUrl().equals("http://sunrin.graphics/2016/") + "");

        if (webView.getUrl().equals("http://sunrin.graphics/2016/")) {

            if (touchView.getVisibility() == View.GONE)
                touchView.setVisibility(View.VISIBLE);

            touchView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("asdf", "touched");
                    webView.loadUrl("http://sunrin.graphics/2016/main");
                    touchView.setVisibility(View.GONE);
                }
            });
        }*/
    }

    public void isHome() {
        Log.d("asdf", webView.getUrl().equals("http://sunrin.graphics/2016/") + "");

        if (webView.getUrl().equals("http://sunrin.graphics/2016/")) {

            if (touchView.getVisibility() == View.GONE)
                touchView.setVisibility(View.VISIBLE);

            touchView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("asdf", "touched");
                    webView.loadUrl("http://sunrin.graphics/2016/main");
                    touchView.setVisibility(View.GONE);
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_favorite:
                ItemFavoriteUrl db = new ItemFavoriteUrl(webView.getTitle(), webView.getUrl());
                db.save();
                adapter.notifyDataSetChanged();
                //adapter.add(ItemFavoriteUrl.findById(ItemFavoriteUrl.class, ItemFavoriteUrl.count(ItemFavoriteUrl.class)));
                Toast.makeText(MainActivity.this, R.string.favorite_saved, Toast.LENGTH_SHORT).show();
                /*for (int i=0; i<ItemFavoriteUrl.count(ItemFavoriteUrl.class); i++) {
                    db = ItemFavoriteUrl.find
                    Log.d("db", db.url);
                }*/
                Log.d("count", adapter.getCount() + "");
                break;

            case R.id.action_clear:
                ItemFavoriteUrl.deleteAll(ItemFavoriteUrl.class);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, R.string.clear, Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

}
