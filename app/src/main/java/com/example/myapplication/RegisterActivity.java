package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class RegisterActivity extends AppCompatActivity {
    private Button registerBtn, gotoLoginBtn;

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private EditText regName,regLastName, regHomePhone, regPhone, regGmail, adddress, zipcode, regPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        registerBtn = findViewById(R.id.btnRegLogin);
        gotoLoginBtn = findViewById(R.id.btnGotoLogin);
        regName = findViewById(R.id.etFstName);
        regLastName = findViewById(R.id.etLstName);
        regHomePhone = findViewById(R.id.etReghomePhone);
        regPhone = findViewById(R.id.etRegPhone);
        regGmail = findViewById(R.id.etRegGmail);
        adddress  = findViewById(R.id.etAddress);
        zipcode = findViewById(R.id.etzipcode);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = regName.getText().toString().trim();
                String lname = regLastName.getText().toString().trim();
                String fHomePhone = regHomePhone.getText().toString().trim();
                String fPhone = regPhone.getText().toString().trim();
                String zipcodee = zipcode.getText().toString().trim();
                String fGmail = regGmail.getText().toString().trim();
               // if (fname.isEmpty() || fPassword.isEmpty() || fGmail.isEmpty() || fPhone.isEmpty()) {
                if (fname.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please Enter the first name ", Toast.LENGTH_SHORT).show();
                }

                else if (fHomePhone.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please Enter the home phone ", Toast.LENGTH_SHORT).show();
                }
                else if(fHomePhone.length()<10)
                {
                    Toast.makeText(getApplicationContext(), "Please enter 10 digit phone", Toast.LENGTH_SHORT).show();
                }

                else if (fPhone.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please Enter the home phone ", Toast.LENGTH_SHORT).show();
                }
                else if(fPhone.length()<10)
                {
                    Toast.makeText(getApplicationContext(), "Please enter 10 digit phone", Toast.LENGTH_SHORT).show();
                }

               else if (fGmail.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please Enter the emailid ", Toast.LENGTH_SHORT).show();
                }

                else if(!isValidEmailAddress(fGmail))
                {
                    Toast.makeText(getApplicationContext(), "Email is not valid!", Toast.LENGTH_SHORT).show();
                }

                else if(zipcodee.length()<6)
                {
                    Toast.makeText(getApplicationContext(), "Please enter 6 digit of zipcode", Toast.LENGTH_SHORT).show();
                }
                else {
                    // insertData(fname,fPhone,fGmail,fPassword);
                    Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                   Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                   i.putExtra("name", fname);
                   i.putExtra("homenumber",fHomePhone);
                   i.putExtra("phonenumber",fPhone);
                   i.putExtra("email",fGmail);
                   i.putExtra("zipcode",zipcodee);
                   startActivity(i);
                   // finish();
                }
            }
        });

        gotoLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                finish();
            }
        });


        try {
            //String responseText = getResponseText("https://coderbyte.com/api/challenges/json/rest-get-simple");
           //JSONObject mainResponseObject = new JSONObject(responseText);
           // System.out.println("=== mainresponse : " + mainResponseObject);
            //callAPI("https://coderbyte.com/api/challenges/json/rest-get-simple");
           // new FetchWeatherData().execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public static String callAPI(String strUrl){

        strUrl = strUrl.replaceAll(" ", "%20");

        System.out.println("URLLLLLLLLLLLLLL :"+strUrl);

        String data = "";

        InputStream iStream = null;

        try{

            URL url = new URL(strUrl);

            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

            urlConnection.connect();

            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb  = new StringBuffer();

            String line = "";

            while( ( line = br.readLine())  != null){

                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e){

            e.printStackTrace();

        }

        return data;
    }


    private String sendGet() throws Exception {
        String url = "http://myurl/";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setDoOutput(true);
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'Get' request to URL : " + url + "--" + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println("Response : -- " + response.toString());
        return response.toString();
    }

    private String getResponseText(String stringUrl) throws IOException {
        StringBuilder response = new StringBuilder();

        URL url = new URL(stringUrl);
        HttpURLConnection httpconn = (HttpURLConnection) url.openConnection();
        if (httpconn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader input = new BufferedReader(new InputStreamReader(httpconn.getInputStream()), 8192);
            String strLine = null;
            while ((strLine = input.readLine()) != null) {
                response.append(strLine);
            }
            input.close();
        }
        return response.toString();
    }


    private class FetchWeatherData extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String forecastJsonStr = null;

            try {
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are avaiable at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast
                URL url = new URL("https://coderbyte.com/api/challenges/json/rest-get-simple");

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {

                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                forecastJsonStr = buffer.toString();
                return forecastJsonStr;
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
           // tvWeatherJson.setText(s);
            Log.i("json", s);
            try {
                JSONObject jsonObject = new JSONObject(s);
               JSONArray jsonArray =  jsonObject.getJSONArray("hobbies");
                System.out.println("=== json arra: " + jsonArray.toString());
                String str = CDL.rowToString(jsonArray);
                System.out.println(str);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}