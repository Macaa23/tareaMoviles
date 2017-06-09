package dm.ubb.cl.tareamoviles;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.Map;

import dm.ubb.cl.tareamoviles.R;
import dm.ubb.cl.tareamoviles.InternetChecker;

public class Indicadores extends AppCompatActivity {
    TextView nombre, correo;
    TextView alias, web, direccion, telefono, a, w, d, t;
    ProgressDialog progressDialog;
    public static String URL = "http://indicadoresdeldia.cl/webservice/indicadores.json";

    String id_usuario;
    String nick, pw, dir, tel, desc;
    Button button;
    private ViewTreeObserver.OnScrollChangedListener mOnScrollChangedListener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.indicadores);

        if (InternetChecker.isConnectedToInternet(getApplicationContext())) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        /*nombre = (TextView) findViewById(R.id.textView5);
                        correo = (TextView) findViewById(R.id.textView11);
                        alias = (TextView) findViewById(R.id.textView8);
                        web = (TextView) findViewById(R.id.textView17);
                        direccion = (TextView) findViewById(R.id.textView18);
                        telefono = (TextView) findViewById(R.id.textView19);
                        a = (TextView) findViewById(R.id.textView9);
                        w = (TextView) findViewById(R.id.textView12);
                        d = (TextView) findViewById(R.id.textView13);
                        t = (TextView) findViewById(R.id.textView14);
                        nombre.setText(session.getUserDetails().get(UserSessionManager.KEY_NOMBRE));
                        correo.setText(session.getUserDetails().get(UserSessionManager.KEY_CORREO));

                        JSONObject jsonObject = new JSONObject(response);
                        alias.setText(jsonObject.getString("alias"));
                        if (alias.getText().equals("null")) {
                            alias.setVisibility(View.GONE);
                            a.setVisibility(View.GONE);
                        }
                        web.setText(jsonObject.getString("web"));
                        if (web.getText().equals("null")) {
                            web.setVisibility(View.GONE);
                            w.setVisibility(View.GONE);
                        }
                        direccion.setText(jsonObject.getString("direccion"));
                        if (direccion.getText().equals("null")) {
                            direccion.setVisibility(View.GONE);
                            d.setVisibility(View.GONE);
                        }
                        telefono.setText(jsonObject.getString("telefono"));
                        if (telefono.getText().equals("null")) {
                            telefono.setVisibility(View.GONE);
                            t.setVisibility(View.GONE);
                        }
                        //descripcion.setText("");


                        /*nick = alias.getText().toString();
                        pw = web.getText().toString();
                        dir = direccion.getText().toString();
                        tel = telefono.getText().toString();
                        desc = descripcion.getText().toString();
                        modulo = new Modulo(c.getString("id"),c.getString("nombre"),c.getString("tipo_modulo"));
                        todos_los_modulos.add(modulo);
*/
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Indicadores.this, "Error", Toast.LENGTH_LONG).show();
                    error.printStackTrace();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

        } else {
            Toast.makeText(getApplicationContext(), "No tienes conexión a internet", Toast.LENGTH_LONG).show();
        }

    }


}
