package com.example.project;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Locale;

public class JsonHandlerThread extends Thread {
    private static final String TAG = "JsonHandlerThread";

    private static String jsonUrl = "https://www.edb.gov.hk/attachment/en/student-parents/sch-info/sch-search/sch-location-info/SCH_LOC_EDB.json";


    public static String makeRequest() {
        String response = null;
        try {
            URL url = new URL(jsonUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");


            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = inputStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }


    private static String inputStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                Log.e(TAG, "IOException: " + e.getMessage());
            }
        }
        return sb.toString();
    }

    public void run() {

        String contactStr = makeRequest();
        Log.e(TAG, "Response from url: " + contactStr);

        if (contactStr != null) {
            try {


                JSONArray contacts = new JSONArray(String.valueOf(contactStr));

                for (int i = 0; i < contacts.length(); i++) {
                    JSONObject c = contacts.getJSONObject(i);

                    if((contacts.getJSONObject(i))==contacts.getJSONObject(0)){

                    }else if(Locale.getDefault().getLanguage().equals("zh")){
                        JSONObject t = contacts.getJSONObject(0);
                        String tname = t.getString("E");
                        String taddress = t.getString("G");
                        String ttelephone = t.getString("AA");
                        String tcatrgory = t.getString("C");
                        String tgender = t.getString("Q");
                        String tfax = t.getString("AC");
                        String twebsite = t.getString("AE");
                        String treligion = t.getString("AG");


                        String name = c.getString("E");
                        String address = c.getString("G");
                        String telephone = c.getString("AA");
                        String category = c.getString("C");
                        String gender = c.getString("Q");
                        String fax = c.getString("AC");
                        String website = c.getString("AE");
                        String religion = c.getString("AG");



                    ContactInfo.addContact(tname, taddress, ttelephone, tcatrgory, tgender, tfax,
                            twebsite, treligion, name, address, telephone, category, gender, fax,
                            website, religion);
                    }else{
                        JSONObject t = contacts.getJSONObject(0);
                        String tname = t.getString("D");
                        String taddress = t.getString("F");
                        String ttelephone = t.getString("Z");
                        String tcatrgory = t.getString("B");
                        String tgender = t.getString("P");
                        String tfax = t.getString("AB");
                        String twebsite = t.getString("AD");
                        String treligion = t.getString("AF");


                        String name = c.getString("D");
                        String address = c.getString("F");
                        String telephone = c.getString("Z");
                        String category = c.getString("B");
                        String gender = c.getString("P");
                        String fax = c.getString("AB");
                        String website = c.getString("AD");
                        String religion = c.getString("AF");



                        ContactInfo.addContact(tname, taddress, ttelephone, tcatrgory, tgender, tfax,
                                twebsite, treligion, name, address, telephone, category, gender, fax,
                                website, religion);
                    }
                }
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }
        } else {
            Log.e(TAG, "Couldn't get json from server.");
        }
    }


}
