package it.univpm.progetto_febbraio2020.services;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

// TODO: Auto-generated Javadoc
/**
 * The Class Downloader.
 * @author Matteo e Federico
 */
public class Downloader {

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {

        String url = "";
        String fileName = "";
        if (args.length == 2) {
            url = args[0];
            fileName = args[1];
        } else return;
        try {
            URLConnection openConnection = new URL(url).openConnection();
            InputStream in = openConnection.getInputStream();

            StringBuilder data = new StringBuilder();
            String line = "";
            try {
                InputStreamReader inR = new InputStreamReader( in );
                BufferedReader buf = new BufferedReader(inR);
                while ((line = buf.readLine()) != null) { 
                    data.append(line);
                    System.out.println(line);
                }
            } finally { 
                in .close();
            }
            JSONObject obj = (JSONObject) JSONValue.parseWithException(String.valueOf(data));
            JSONObject objI = (JSONObject)(obj.get("result"));
            JSONArray objA = (JSONArray)(objI.get("resources"));

            for (Object o: objA) {
                if (o instanceof JSONObject) {
                    JSONObject o1 = (JSONObject) o;
                    String format = (String) o1.get("format");
                    String urlD = (String) o1.get("url");
                    System.out.println(format + " | " + urlD);
                    if (format.equals("http://publications.europa.eu/resource/authority/file-type/TSV")) {
                     
                        download(urlD, fileName);
                    }
                }
            }
            System.out.println("Download completato!");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Download.
     *
     * @param url the url
     * @param fileName the file name
     * @throws Exception the exception
     */
    public static void download(String url, String fileName) throws Exception {
        HttpURLConnection openConnection = (HttpURLConnection) new URL(url).openConnection();
        InputStream in = openConnection.getInputStream();
        try {
            if (openConnection.getResponseCode() >= 300 && openConnection.getResponseCode() < 400) { 
                download(openConnection.getHeaderField("Location"), fileName); in.close();
                openConnection.disconnect();
                return;
            }
            Files.copy( in , Paths.get(fileName)); 
            System.out.println("Download Completato! \nScaricati " + Files.size(Paths.get(fileName))+" byte.");
        } finally { in.close();
        }
    }
}