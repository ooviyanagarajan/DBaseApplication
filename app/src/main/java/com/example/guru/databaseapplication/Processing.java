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
            alertDialog.setMessage(res);
            alertDialog.show();

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

                  httpURLConnection.disconnect();
                  return "Registration Success...";
//

              } catch (MalformedURLException e) {
                  result = e.getMessage();
              } catch (IOException e) {
                  result = e.getMessage();
              }
          }

           if(method.equals("delete")) {
               String studid = params[1];

              try {
                  URL url2 = new URL(del_url);
                  HttpURLConnection httpURLConnection = (HttpURLConnection) url2.openConnection();
                //  httpURLConnection.setRequestMethod("GET");
                  httpURLConnection.setDoOutput(true);
                  httpURLConnection.setDoInput(true);

                  OutputStream OS = httpURLConnection.getOutputStream();
                  BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                  String data = URLEncoder.encode("studid", "UTF-8") + "=" + URLEncoder.encode(studid, "UTF-8");
                  bufferedWriter.write(data);
                  bufferedWriter.flush();
                  bufferedWriter.close();
                  OS.close();

                  InputStream inputStream = httpURLConnection.getInputStream();
                  BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                  String response = "";
                  String line = "";
                  while ((line = bufferedReader.readLine())!=null)
                  {
                      response+= line;
                  }
                  bufferedReader.close();
                  inputStream.close();
                  httpURLConnection.disconnect();
                  return response;


              } catch (MalformedURLException e) {
                result = e.getMessage();
                } catch (IOException e) {
                 result = e.getMessage();
                }
          }
           if (method.equals("update")) {
              String studid = params[1];
              String email = params[2];

              try {
                  URL url2 = new URL(update_url);

                  HttpURLConnection httpURLConnection = (HttpURLConnection) url2.openConnection();
                 // httpURLConnection.setRequestMethod("POST");
                  httpURLConnection.setDoOutput(true);
                  httpURLConnection.setDoInput(true);


                  OutputStream OS = httpURLConnection.getOutputStream();

                  BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                  String data = URLEncoder.encode("studid", "UTF-8") + "=" + URLEncoder.encode(studid, "UTF-8") + "&" +
                          URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                  bufferedWriter.write(data);
                  bufferedWriter.flush();
                  bufferedWriter.close();
                  OS.close();

                  InputStream inputStream = httpURLConnection.getInputStream();
                  BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                  String response = "Updated Successfully";
                  String line = "";
                  while ((line = bufferedReader.readLine())!=null)
                  {
                      response+= line;
                  }
                  bufferedReader.close();
                  inputStream.close();
                  httpURLConnection.disconnect();
                  return response;




              } catch (MalformedURLException e) {
                  result = e.getMessage();
              } catch (IOException e) {
                  result = e.getMessage();
              }
          }



        return result;


    }
}

