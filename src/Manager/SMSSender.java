/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.HttpURLConnection;

/**
 *
 * @author I.O.I
 */
public class SMSSender {
    public static void SendSMS(String number, String text){
        try {
            // Construct data
            String apiKey = "apikey=" + "oFaS/NVQTv4-c90oZSmXhnSzI7U1T0t2oMhzSiNKSv";
            String message = "&message=" + text;
            String sender = "&sender=" + "Med Omar WALHA";
            String numbers = "&numbers=" + number;

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                    //stringBuffer.append(line);
                    System.out.println("message = "+line);
            }
            rd.close();
            //return stringBuffer.toString();
        } catch (Exception e) {
                System.out.println("Error SMS "+e);
                //return "Error "+e;
        }
    }
}
