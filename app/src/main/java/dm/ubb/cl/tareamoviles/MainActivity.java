package dm.ubb.cl.tareamoviles;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myWebView = (WebView) findViewById(R.id.webPage);
        if(InternetHelper.isConnectedToInternet(getApplicationContext())) {
            avisoDialog = new ProgressDialog(this);
            avisoDialog.setMessage("Conectando al servidor... Por favor espere.");
            avisoDialog.setCancelable(true);
            avisoDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            avisoDialog.show();
            myWebView.loadUrl(Constantes.URL_NOTICIASUBB);
            // Obtener el refreshLayout
            refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);

            // Seteamos los colores que se usarán a lo largo de la animación
            refreshLayout.setColorSchemeResources(
                    R.color.s1,
                    R.color.s2,
                    R.color.s3,
                    R.color.s4
            );

            // Iniciar la tarea asíncrona al revelar el indicador
            refreshLayout.setOnRefreshListener(
                    new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            myWebView.loadUrl(Constantes.URL_NOTICIASUBB);
                            refreshLayout.setRefreshing(false);
                        }
                    }
            );

            refreshLayout.getViewTreeObserver().addOnScrollChangedListener(mOnScrollChangedListener =
                    new ViewTreeObserver.OnScrollChangedListener() {
                        @Override
                        public void onScrollChanged() {
                            if (myWebView.getScrollY() == 0)
                                refreshLayout.setEnabled(true);
                            else
                                refreshLayout.setEnabled(false);

                        }
                    });
            avisoDialog.dismiss();
        }else {
            Toast.makeText(getApplicationContext(), "No tienes conexión a internet", Toast.LENGTH_LONG).show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
}
