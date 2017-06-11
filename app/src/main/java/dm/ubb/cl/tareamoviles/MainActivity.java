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
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String nombre;
    Button b;
    EditText Nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Nombre = (EditText)findViewById(R.id.editText);

        b = (Button) findViewById(R.id.siguiente);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre = Nombre.getText().toString();
                Intent intent = new Intent(MainActivity.this, Indicadores.class);
                intent.putExtra("nombre",nombre);
                startActivity(intent);
                finish();
            }
        });
    }
}
