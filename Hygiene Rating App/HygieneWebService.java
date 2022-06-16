package com.example.week20;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HygieneWebService {
    Context context;
    HygieneWebService(Context context) { this.context = context; }

    public ArrayList<Business> makeRequest(String plainUrlEnd, String searchType)
    {
        ArrayList<Business> businesses = new ArrayList<>();
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            System.out.println("CONNECTED");

            try {
                String encodedUrlEnd = URLEncoder.encode(plainUrlEnd, "UTF-8");
                URL url = new URL("http://sandbox.kriswelsh.com/hygieneapi/hygiene.php?op=search" + searchType + encodedUrlEnd);
                URLConnection connection = url.openConnection();
                InputStreamReader ins = new InputStreamReader(connection.getInputStream());
                BufferedReader in = new BufferedReader(ins);
                String line = "";
                while ((line = in.readLine()) != null) {
                    JSONArray ja = new JSONArray(line);
                    for (int i = 0; i < ja.length(); i++) {

                        JSONObject jo = (JSONObject) ja.get(i);

                        Business business = new Business();
                        business.setId(jo.getInt("id"));
                        business.setBusinessName(jo.getString("BusinessName"));
                        business.setAddressLine1(jo.getString("AddressLine1"));
                        business.setAddressLine2(jo.getString("AddressLine2"));
                        business.setAddressLine3(jo.getString("AddressLine3"));
                        business.setPostCode(jo.getString("PostCode"));
                        business.setRatingValue(jo.getInt("RatingValue"));

                        //establishment.setLatitude(jo.getDouble("Latitude"));
                        //establishment.setLongitude(jo.getDouble("Longitude"));

                        //JSONArray files = jo.getJSONArray("Location");
                        //for (int j = 0; j < files.length(); i++) {
                            //establishment.setLatitude(files.getDouble(j));
                            //establishment.setLongitude(files.getDouble(j));
                        //}

                        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = (Date) f.parse(jo.getString("RatingDate"));
                        business.setRatingDate(date);

                        businesses.add(business);
                    }

                }
                in.close();
                return businesses;
            } catch (IOException | JSONException | ParseException e) {
                System.out.println("Something went wrong");
            }
        }
        else
        {
            System.out.println("NOT-CONNECTED");
        }
        return businesses;
    }
}
