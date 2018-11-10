package prolog;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Client{

    public Client() {

    }

    public static void request(String requestString) throws IOException {

        URL myURL = new URL("http://localhost:8081/" + requestString);
        HttpURLConnection connection = (HttpURLConnection) myURL.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder results = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            results.append(line);
        }
        connection.disconnect();
        System.out.println(results.toString());
    }
}
