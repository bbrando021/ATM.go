package com.example.patel.fast;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


/**
 * Created by Brandon on 4/8/2017.
 */

public class httpRequestTask extends AsyncTask<URL,Void,Object>{

    private String url = "https://capitaloneserver-dalofeco.c9users.io";
    private JSONObject json = new JSONObject();
    private String extension;
    private JSONObject responseJson = new JSONObject();

    public httpRequestTask(JSONObject j, String e){
        this.json = j;
        this.extension = e;
    }

    @Override
    protected void onPostExecute(Object result) {
        ;
    }
    @Override
    protected Void doInBackground(URL... params) {
        try {

            URL obj = new URL(url + extension);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            //add reuqest header
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");

            // Send post request

            OutputStream wr = con.getOutputStream();
            wr.write(json.toString().getBytes());
            wr.flush();
            wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + json.toString() );
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
            JSONObject jsonObj = new JSONObject(response.toString());
        //System.out.println(jsonObj.getString("token"));

            if(extension.equals("/login")){
                //accounts a = new accounts(jsonObj);
                responseJson = jsonObj;
            }
            if(extension.equals("/transaction")) {
                responseJson = jsonObj;
                System.out.println(responseJson + "HEREHEHEHREHRHERHERHEHREHRHEHREHRHE");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public JSONObject getResponse(){
        return responseJson;
    }

}
