import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.google.gson.*;

class Chatbot {

    // ======================== API CONFIG ========================
    private static final String API_KEY = "AIzaSyC7l3wysjjSzsuSAHnUGyjfX3mEv91Zy8U";

    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + API_KEY;

    record Message(String role, String content) {

        @Override
        public String toString() {
            return (role.equals("user") ? "You: " : "Gemini: ") + content;
        }
    }

    static class ChatHistory {
        private final List<Message> messages = new ArrayList<>();

        public void add(String role, String content) {
            messages.add(new Message(role, content));
        }

        public void print() {
            System.out.println("\n--- Chat History ---");
            for (Message m : messages) {
                System.out.println(m);
            }
            System.out.println("--------------------\n");
        }
    }

    static class GeminiClient {

        public String sendMessage(String userMessage) {
            try {
                String jsonInput = "{"
                        + "\"contents\": [{\"parts\": [{\"text\": \"" + escapeJson(userMessage) + "\"}]}]"
                        + "}";

                URL url = new URI(API_URL).toURL();
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                try (OutputStream os = conn.getOutputStream()) {
                    os.write(jsonInput.getBytes("utf-8"));
                }

                InputStream is = conn.getResponseCode() >= 400 ? conn.getErrorStream() : conn.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
                StringBuilder responseBuilder = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    responseBuilder.append(line.trim());
                }

                String jsonResponse = responseBuilder.toString();

                // Parse with Gson
                JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
                JsonArray candidates = jsonObject.getAsJsonArray("candidates");

                if (candidates != null && candidates.size() > 0) {
                    JsonObject firstCandidate = candidates.get(0).getAsJsonObject();
                    JsonArray parts = firstCandidate.getAsJsonObject("content").getAsJsonArray("parts");
                    return parts.get(0).getAsJsonObject().get("text").getAsString();
                } else {
                    return "No response from Gemini.";
                }

            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        }

        private String escapeJson(String str) {
            return str.replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r");
        }
    }

    // ======================== MAIN METHOD ========================
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GeminiClient client = new GeminiClient();
        ChatHistory history = new ChatHistory();

        System.out.println("Gemini Chatbot (type 'exit' to quit, 'history' to view log)");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            } else if (userInput.equalsIgnoreCase("history")) {
                history.print();
                continue;
            }

            history.add("user", userInput);
            String reply = client.sendMessage(userInput);
            history.add("assistant", reply);

            System.out.println("Gemini: " + reply);
        }

        scanner.close();
    }
}
