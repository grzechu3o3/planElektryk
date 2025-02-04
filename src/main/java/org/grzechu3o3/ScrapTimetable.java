package org.grzechu3o3;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;


public class ScrapTimetable {
    static String date;
    static String id; // "-34"

    static String url = "https://ckziu-elektryk.edupage.org/timetable/server/currenttt.js?__func=curentttGetData";


    public static void setDate(String _date) {
       date = _date;
    }
    public static void setId(String _id) {
        id = _id;
    }

    static String payload;

    public static void setPayload() {
        payload = String.format("""
                {
                    "__args": [
                        null,
                        {
                            "year": 2024,
                            "datefrom": "%s",
                            "dateto": "%s",
                            "table": "classes",
                            "id": "%s",
                            "showColors": true,
                            "showIgroupsInClasses": false,
                            "showOrig": true,
                            "log_module": "CurrentTTView"
                        }
                    ],
                    "__gsh": "00000000"
                }
            """, date, date, id);
    }

    public static Root post() {
        try {
            setPayload();
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json; charset=UTF-8")
                    .header("Referer", "https://ckziu-elektryk.edupage.org/")
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36")
                    .header("Cookie", "PHPSESSID=fbe1a7971886607615d3fc291d4456be")
                    .POST(HttpRequest.BodyPublishers.ofString(payload, StandardCharsets.UTF_8))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() != 200) {
                System.out.println("Error: " + response.statusCode());
                return null;
            }

            Gson gson = new Gson();
            Root root = gson.fromJson(response.body(), Root.class);

            return root;

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null;
    }
}
