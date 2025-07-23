package com.example.textword.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class AIService {

    @Value("${cohere.api.key}")
    private String cohereApiKey;

    private static final String COHERE_CHAT_ENDPOINT = "https://api.cohere.ai/chat";

    public String improveText(String originalText) {
        // Vérifier si le texte est null ou vide
        if (originalText == null || originalText.trim().isEmpty()) {
            return originalText; // Retourner le texte original sans appeler l'API
        }

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(cohereApiKey);

        // Construction du corps de la requête selon l'API chat Cohere
        Map<String, Object> body = Map.of(
                "model", "command-xlarge-nightly",
                "message", originalText, // Format simplifié pour l'API Cohere
                "max_tokens", 100,
                "temperature", 0.7
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(COHERE_CHAT_ENDPOINT, entity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response.getBody());

                // Récupération du texte amélioré dans la réponse de Cohere
                if (root.has("text")) {
                    return root.get("text").asText().trim();
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur API Cohere : " + e.getMessage());
            e.printStackTrace();
        }

        // En cas d'erreur, retourner le texte original
        return originalText;
    }
}