package org.myorg.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.port;

public class AppLbRoundRobin {
    private static final List<Server> servers = List.of(
            new Server("log-service-1", "http://172.31.57.116:35001"),
            new Server("log-service-2", "http://172.31.5.194:35002"),
            new Server("log-service-3", "http://172.31.14.214:35003"));
    private static int currentServer = 0;

    public static void main(String[] args) {
        port(getPort());
//        get("/hello", (request, response) -> Files.readAllBytes(Path.of("src/main/resources/index.html")));
        get("/", (request, response) -> "<!DOCTYPE html>\n" +
                "//                <html>\n" +
                "//                  <head>\n" +
                "//                    <link rel=\"icon\" href=\"data:,\">\n" +
                "//                    <title>Form Example</title>\n" +
                "//                    <meta charset=\"UTF-8\" />\n" +
                "//                    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "//                  </head>\n" +
                "//                  <body>\n" +
                "//                    <h1>Form with GET</h1>\n" +
                "//                    <form action=\"/json\">\n" +
                "//                      <label for=\"name\">Name:</label><br/>\n" +
                "//                      <input type=\"text\" id=\"name\" name=\"name\" value=\"london\" /><br /><br />\n" +
                "//                      <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\"/>\n" +
                "//                    </form>\n" +
                "//                    <div id=\"getrespmsg\"></div>\n" +
                "//\n" +
                "//                    <script>\n" +
                "//                      function loadGetMsg(){\n" +
                "//                        let name = document.getElementById(\"name\");\n" +
                "//                        let url = \"/json?q=\" + name.value;\n" +
                "//                        console.log(url);\n" +
                "//                        fetch(url, { method: \"GET\" })\n" +
                "//                          .then((x) => x.text())\n" +
                "//                          .then((y) => (document.getElementById(\"getrespmsg\").innerHTML = y));\n" +
                "//                      }\n" +
                "//                    </script>\n" +
                "//                  </body>\n" +
                "//                </html>");
        get("/json", ((request, response) -> {
            response.type("application/json");
            URL obj = new URL(getNextServer() +
                    "/consume/" + request.queryString().split("=")[1]);
            HttpURLConnection con;
            try {
                con = (HttpURLConnection) obj.openConnection();
            } catch (IOException ex) {
                response.status(404);
                return "404 Not Found";
            }
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder res = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                res.append(inputLine);
            }
            in.close();

            return res.toString();
        }));
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    public static String getNextServer() {
        Server server = servers.get(currentServer);
        currentServer = (currentServer + 1) % servers.size();
        System.out.println("Request sent to " + server.getName());
        return server.getUrl();
    }
}


class Server {
    private final String name;
    private final String url;

    public Server(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
