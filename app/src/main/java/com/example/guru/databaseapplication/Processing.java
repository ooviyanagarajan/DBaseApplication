package com.example.guru.databaseapplication;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import android.widget.Toast;

public class Processing extends AsyncTask<String,Void,String> {

    AlertDialog alertDialog;
    Context ctx;

    public Processing(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Register Status...");
    }


    @Override
    protected void onPostExecute(String res) {
        if(res.equals("Registration Success..."))
        {
            Toast.makeText(ctx, res, Toast.LENGTH_LONG).show();
        }
        else if(res.equals("Deleted Successfully"))
        {
            Toast.makeText(ctx, res, Toast.LENGTH_LONG).show();
        }
        else if(res.equals("Updated Successfully"))
        {
            Toast.makeText(ctx, res, Toast.LENGTH_LONG).show();
        }
        else
        {
            alertDialog.setMessage(res);
            alertDialog.show();
        }

    }

    @Override
    protected String doInBackground(String... params) {
          String result="";

        String reg_url = "http://192.168.43.149/registerdet.php";
        String del_url = "http://192.168.43.149/regdelete.php";
        String update_url = "http://192.168.43.149/regupdate.php";

          String method=params[0];
          if(method.equals("register")) {
              String studname = params[1];
              String studid = params[2];
              String email = params[3];



              try {
                  URL url = new URL(reg_url);
                  HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                  httpURLConnection.setRequestMethod("POST");
                  httpURLConnection.setDoOutput(true);
                  httpURLConnection.setDoInput(true);
                  OutputStream OS = httpURLConnection.getOutputStream();
                  BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                  String data = URLEncoder.encode("studname", "UTF-8") + "=" + URLEncoder.encode(studname, "UTF-8") + "&" +
                          URLEncoder.encode("studid", "UTF-8") + "=" + URLEncoder.encode(studid, "UTF-8") + "&" +
                          URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                  bufferedWriter.write(data);
                  bufferedWriter.flush();
                  bufferedWriter.close();
                  OS.close();
                  InputStream IS = httpURLConnection.getInputStream();
                  IS.close();
                  //httpURLConnection.connect();
                  httpURLConnection.disconnect();
                  return "Registration Success...";


              } catch (MalformedURLException e) {
                  result = e.getMessage();
              } catch (IOException e) {
                  result = e.getMessage();
              }
          }

          else if(method.equals("delete")) {
              String id = params[1];

              try {
                  URL url2 = new URL(del_url);
                  HttpURLConnection httpURLConnection = (HttpURLConnection) url2.openConnection();
                  httpURLConnection.setRequestMethod("POST");
                  httpURLConnection.setDoOutput(true);
                  httpURLConnection.setDoInput(true);
                  httpURLConnection.disconnect();
                  return "Deleted Successfully";
              } catch (MalformedURLException e) {
                result = e.getMessage();
                } catch (IOException e) {
                 result = e.getMessage();
                }
          }
          else if(method.equals("update")) {
              String id = params[1];

              try {
                  URL url2 = new URL(update_url);
                  HttpURLConnection httpURLConnection = (HttpURLConnection) url2.openConnection();
                  httpURLConnection.setRequestMethod("POST");
                  httpURLConnection.setDoOutput(true);
                  httpURLConnection.setDoInput(true);
                  httpURLConnection.disconnect();
                  return "Updated Successfully";
              } catch (MalformedURLException e) {
                  result = e.getMessage();
              } catch (IOException e) {
                  result = e.getMessage();
              }
          }



        return result;


    }
}

