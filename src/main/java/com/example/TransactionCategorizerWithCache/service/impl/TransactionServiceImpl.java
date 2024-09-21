package com.example.TransactionCategorizerWithCache.service.impl;

import com.example.TransactionCategorizerWithCache.model.ChatMessageRole;
import com.example.TransactionCategorizerWithCache.model.OpenAIModels;
import com.example.TransactionCategorizerWithCache.model.Transaction;
import com.example.TransactionCategorizerWithCache.service.TransactionServices;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import lombok.AllArgsConstructor;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionServices {
    private final Environment env;
    @Override
    public List<Transaction> categorizeTransactions(List<Transaction> transactions) {
        String openaiAPIKey = env.getProperty("openaiAPIKey");
        assert openaiAPIKey != null;
        OpenAiService openAIService = new OpenAiService(openaiAPIKey);
        List<ChatMessage> message = new ArrayList<>();

        List<String> transactionNames = transactions.stream().map(Transaction::getDescription).toList();

        String PROMPT = "Please convert the list of bank transaction names into categories. " +
                "Here is the transaction names: " + transactionNames +
                "Please format the response into JSON format of transactionName : category key value pair list.";

//        ChatMessage transactionMessage = new ChatMessage(ChatMessageRole.USER.value(), PROMPT);
//        message.add(transactionMessage);
//
//        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
//                .builder()
//                .model(OpenAIModels.GPT_4_o_mini.getName())
//                .maxTokens(250)
//                .messages(message)
//                .build();
        return transactions;
    }
}
