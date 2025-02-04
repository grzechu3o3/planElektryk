package org.grzechu3o3;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class SubjectShortcuts {
    static String url = "https://ckziu-elektryk.edupage.org/rpr/server/maindbi.js?__func=mainDBIAccessor";
    static String payload = """
            {
              "__args": [
                null,
                2024,
                {
                  "vt_filter": {
                    "datefrom": "2025-01-27",
                    "dateto": "2025-02-02"
                  }
                },
                {
                  "op": "fetch",
                  "needed_part": {
                    "subjects": [
                         "short",
                         "name",
                         "firstname",
                         "lastname",
                         "callname",
                         "subname",
                         "code"
                     ]
                  },
                  "needed_combos": {}
                }
              ],
              "__gsh": "00000000"
            }
            """;
    public static Subjects get() {
        try {
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
            Subjects root = gson.fromJson(response.body(), Subjects.class);

            return root;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
