package org.example.firstspringbootproject.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final ChatClient chatClient;
    private final SimpleVectorStore vectorStore;
    private final ToolCallbackProvider mcpTools;

    public AIController(
            ChatClient.Builder builder,
            SimpleVectorStore vectorStore,
            ToolCallbackProvider mcpTools) {

        this.chatClient = builder.build();
        this.vectorStore = vectorStore;
        this.mcpTools = mcpTools;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String question) {

        var documents = vectorStore.similaritySearch(question);

        String context = documents.isEmpty()
                ? ""
                : documents.get(0).getText();

        return chatClient
                .prompt()
                .system("Use the following reference information when relevant:\n" + context)
                .user(question)
                .tools(mcpTools)
                .call()
                .content();
    }
}