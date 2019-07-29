package mikaeldionitoharbigantara.gmail.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HalamanJson extends AppCompatActivity implements Spinner.OnItemSelectedListener{
    private Spinner spinner;

    private ArrayList<String> mahasiswa;

    private JSONArray result;

    private TextView textViewNama;
    private TextView textViewAsal;
    private TextView textViewLahir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_json);

        mahasiswa = new ArrayList<String>();

        spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);

        textViewNama = (TextView) findViewById(R.id.textViewNama);
        textViewAsal = (TextView) findViewById(R.id.textViewAsal);
        textViewLahir = (TextView) findViewById(R.id.textViewLahir);

        getData();
    }
    private void getData(){
        StringRequest stringRequest = new StringRequest(Config.DATA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            result = j.getJSONArray(Config.JSON_ARRAY);

                            //Calling method getStudents to get the students from the JSON Array
                            getMahasiswa(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getMahasiswa(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                mahasiswa.add(json.getString(Config.TAG_USERNAME));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        spinner.setAdapter(new ArrayAdapter<String>(HalamanJson.this, android.R.layout.simple_spinner_dropdown_item, mahasiswa));
    }

    //Method to get student name of a particular position
    private String getNama(int position){
        String nama="";
        try {
            //Getting object of given index
            JSONObject json = result.getJSONObject(position);

            //Fetching name from that object
            nama = json.getString(Config.TAG_NAMA);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return nama;
    }

    //Doing the same with this method as we did with getName()
    private String getAsal(int position){
        String asal="";
        try {
            JSONObject json = result.getJSONObject(position);
            asal = json.getString(Config.TAG_ASAL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return asal;
    }

    //Doing the same with this method as we did with getName()
    private String getLahir(int position){
        String lahir="";
        try {
            JSONObject json = result.getJSONObject(position);
            lahir = json.getString(Config.TAG_TGLLAHIR);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lahir;
    }


    //this method will execute when we pic an item from the spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Setting the values to textviews for a selected item
        textViewNama.setText(getNama(position));
        textViewAsal.setText(getAsal(position));
        textViewLahir.setText(getLahir(position));
    }

    //When no item is selected this method would execute
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        textViewNama.setText("");
        textViewAsal.setText("");
        textViewLahir.setText("");
    }
}


