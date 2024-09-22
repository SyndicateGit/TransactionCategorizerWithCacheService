package com.example.TransactionCategorizerWithCache.service.impl;


import com.example.TransactionCategorizerWithCache.model.OpenAIModels;
import com.example.TransactionCategorizerWithCache.model.Transaction;
import com.example.TransactionCategorizerWithCache.service.TransactionServices;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import lombok.AllArgsConstructor;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;


import java.time.Duration;
import java.util.*;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionServices {
    private final Environment env;
    @Override
    public List<Transaction> categorizeTransactions(List<Transaction> transactions) {
        String AIToken = env.getProperty("openai.api.Key");
        assert AIToken != null;

        List<String> transactionNames = transactions.stream().map(Transaction::getDescription).toList();

        String prompt = "Please convert the list of bank transaction names into categories. " +
                "Here are the transaction names: " + transactionNames +
                "\n\nYour response should be a JSON format of {\"transaction name\": \"category\"}. " +
                "Return JSON in a single line without whitespace." +
                "Categories should be 1 word max, capitalize the first letter, and if unable to categorize, return 'Other'.";

        OpenAiService openAIService = new OpenAiService(AIToken, Duration.ofSeconds(30));
        List<ChatMessage> messages = new ArrayList<>();
        messages.add(new ChatMessage("user", prompt));

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model(OpenAIModels.GPT_4_o_mini.getName())
                .maxTokens(1000)
                .messages(messages)
                .build();

        ChatCompletionResult result = openAIService.createChatCompletion(chatCompletionRequest);
        String jsonResponse = result.getChoices().get(0).getMessage().getContent();

        jsonResponse = jsonResponse.replaceAll("```", "").trim();

        if (jsonResponse.startsWith("json")) {
            jsonResponse = jsonResponse.substring(4).trim(); // Remove the `json` keyword
        }

        Map<String, String> categorizedTransactions;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            categorizedTransactions = objectMapper.readValue(jsonResponse, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse the JSON response from OpenAI", e);
        }

        for (Transaction transaction : transactions) {
            String key = transaction.getDescription();
            transaction.setCategory(categorizedTransactions.getOrDefault(key.trim(), "Other"));
        }

        return transactions;
    }
}