package org.example;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        String newCustomerId = null;

        try {
            URL url = new URL("http://localhost:8080/api/v1/customerdb/2");

            HttpURLConnection getConnection = (HttpURLConnection) url.openConnection();
            getConnection.setRequestMethod("GET");
            getConnection.connect();

            int code = getConnection.getResponseCode();
            if (code > 199 && code < 300) {
                BufferedReader buffIn = new BufferedReader(new InputStreamReader(getConnection.getInputStream()));
                String inputLine;
                StringBuilder jsonString = new StringBuilder();

                while ((inputLine = buffIn.readLine()) != null) {
                    jsonString.append(inputLine);
                }
                buffIn.close();

                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(String.valueOf(jsonString));
                System.out.println("name: " + jsonObject.get("name"));
                System.out.println("lastName: " + jsonObject.get("lastName"));
                System.out.println("emailId: " + jsonObject.get("emailId"));
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }


        try {
            URL url = new URL("http://localhost:8080/api/v1/customerdb");
            String json = "{\"name\": \"Turry\", \"lastName\": \"Runker\",\"emailId\":\"Turry@gmail.com\"}";
            HttpURLConnection getConnection = (HttpURLConnection) url.openConnection();
            getConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            getConnection.setDoOutput(true);
            getConnection.setDoInput(true);
            getConnection.setRequestMethod("POST");
            OutputStream os = getConnection.getOutputStream();
            os.write(json.getBytes(StandardCharsets.UTF_8));
            os.close();

            int code = getConnection.getResponseCode();
            if (code > 199 && code < 300) {
                System.out.println("The post was a success");
                BufferedReader buffIn = new BufferedReader(new InputStreamReader(getConnection.getInputStream()));
                String inputLine;
                StringBuilder jsonString = new StringBuilder();

                while ((inputLine = buffIn.readLine()) != null) {
                    jsonString.append(inputLine);
                }
                buffIn.close();

                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(String.valueOf(jsonString));
                newCustomerId = jsonObject.get("id").toString();

            } else {
                System.out.println("the post failed");
            }

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        try {
            URL url = new URL("http://localhost:8080/api/v1/customerdb/" + newCustomerId);
            String json = "{\"name\": \"Turry\", \"lastName\": \"Brunker\",\"emailId\":\"Turry@gmail.com\"}";
            HttpURLConnection getConnection = (HttpURLConnection) url.openConnection();
            getConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            getConnection.setDoOutput(true);
            getConnection.setDoInput(true);
            getConnection.setRequestMethod("PUT");
            OutputStream os = getConnection.getOutputStream();
            os.write(json.getBytes(StandardCharsets.UTF_8));
            os.close();


            int code = getConnection.getResponseCode();

            if (code > 199 && code < 300) {
                System.out.println("The put was a success");
                BufferedReader buffIn = new BufferedReader(new InputStreamReader(getConnection.getInputStream()));
                String inputLine;
                StringBuilder jsonString = new StringBuilder();

                while ((inputLine = buffIn.readLine()) != null) {
                    jsonString.append(inputLine);
                }
                buffIn.close();
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(String.valueOf(jsonString));
                var newLastName = jsonObject.get("lastName").toString();
                System.out.println("Was the last name changed: " + (Objects.equals(newLastName, "Brunker")));

            } else {
                System.out.println("The put failed");
            }

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        try {
            URL url = new URL("http://localhost:8080/api/v1/customerdb/" + newCustomerId);

            HttpURLConnection getConnection = (HttpURLConnection) url.openConnection();
            getConnection.setRequestMethod("DELETE");
            getConnection.connect();

            int code = getConnection.getResponseCode();

            if (code > 199 && code < 300) {
                System.out.println("The delete was a success");
            } else {
                System.out.println("The delete failed");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}