package dm.ubb.cl.tareamoviles;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.Map;

public class Indicadores extends AppCompatActivity {

    public static String URL = "http://indicadoresdeldia.cl/webservice/indicadores.json";

    TextView version, fecha ,autor, ayer ,hoy ,maniana ,dolar ,euro ,uf ,ipc ,utm ,imacec ,normal ,normalmaniana ,catalitico, santo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.indicadores);

        Bundle bundle = getIntent().getExtras();
        final String nombre = bundle.getString("nombre");

        version = (TextView) findViewById(R.id.versiontext);
        fecha = (TextView) findViewById(R.id.fechatext);
        autor = (TextView) findViewById(R.id.autortext);
        ayer = (TextView) findViewById(R.id.ayertext);
        hoy = (TextView) findViewById(R.id.hoytext);
        maniana = (TextView) findViewById(R.id.manianatext);
        dolar = (TextView) findViewById(R.id.dolartext);
        euro = (TextView) findViewById(R.id.eurotext);
        uf = (TextView) findViewById(R.id.uftext);
        ipc = (TextView) findViewById(R.id.ipctext);
        utm = (TextView) findViewById(R.id.utmtext);
        imacec = (TextView) findViewById(R.id.imacectext);
        normal = (TextView) findViewById(R.id.normaltext);
        normalmaniana = (TextView) findViewById(R.id.normalmanianatext);
        catalitico = (TextView) findViewById(R.id.cataliticotext);
        santo = (TextView) findViewById(R.id.santo);

        if (InternetChecker.isConnectedToInternet(getApplicationContext())) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        version.setText(jsonObject.getString("version"));
                        fecha.setText(jsonObject.getString("date"));
                        autor.setText(jsonObject.getString("autor"));

                        JSONObject json = jsonObject.getJSONObject("santoral");
                        ayer.setText(json.getString("ayer"));
                        hoy.setText(json.getString("hoy"));
                        maniana.setText(json.getString("maniana"));

                        if(json.getString("hoy").equalsIgnoreCase(nombre)){
                            santo.setText("Feliz santo "+ json.getString("hoy")+ "!");
                        }else if(json.getString("ayer").equalsIgnoreCase(nombre)){
                            santo.setText("Feliz santo atrasado "+ json.getString("ayer")+"!");
                        }else if(json.getString("maniana").equalsIgnoreCase(nombre)){
                            santo.setText("Feliz santo adelantado "+ json.getString("maniana")+"!");
                        }
                        /*
                        json = jsonObject.getJSONObject("moneda");
                        dolar.setText(json.getString("dolar"));
                        euro.setText(json.getString("euro"));
                        */

                        json = jsonObject.getJSONObject("indicador");
                        uf.setText(json.getString("uf"));
                        ipc.setText(json.getString("ipc"));
                        utm.setText(json.getString("utm"));
                        imacec.setText(json.getString("imacec"));

                        json = jsonObject.getJSONObject("restriccion");
                        JSONArray jsonArray = json.getJSONArray("normal");
                        normal.setText(jsonArray.getString(0));
                        jsonArray = json.getJSONArray("normal_maniana");
                        normalmaniana.setText(jsonArray.getString(0));
                        jsonArray = json.getJSONArray("catalitico");
                        catalitico.setText(jsonArray.getString(0));

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
