package com.hackathon.contractsimplifier.util;

import okhttp3.*;
import org.springframework.stereotype.Component;

@Component
public class OpenAIClient {

    private final OkHttpClient client = new OkHttpClient();
    private final String apiKey = System.getenv("OPENAI_API_KEY"); // set in environment

    public String callOpenAI(String contractText) throws Exception {
        String json = "{"
                + "\"model\": \"gpt-4.1-mini\","
                + "\"messages\": [{\"role\": \"system\", \"content\": \"You are ContractSimplifierGPT. Summarize and simplify legal contracts. Always output JSON with short_summary, clauses, and disclaimer.\"},"
                + "{\"role\": \"user\", \"content\": \"" + contractText.replace("\"", "\\\"") + "\"}]"
                + "}";

        RequestBody body = RequestBody.create(
                json,
                MediaType.get("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .header("Authorization", "Bearer " + apiKey)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("API call failed: " + response);
            return response.body().string();
        }
    }
}
