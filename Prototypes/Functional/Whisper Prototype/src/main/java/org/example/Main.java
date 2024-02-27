package org.example;

import io.github.stefanbratanov.jvm.openai.*;

import java.awt.*;
import java.util.Scanner;
import java.io.IOException;
import java.net.Socket;
import java.net.InetSocketAddress;
import java.util.Timer;
import java.util.TimerTask;


public class Main {
    public static Scanner input = new Scanner(System.in);

    public static boolean internetAvailable() {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("1.1.1.1", 80), 3000);
            return true;
        } catch (IOException e)
        {
            return false;
        }
    }

    public static void checkInternet() {
        if (internetAvailable()) {
        } else {
            while (true) {
                System.out.println("Failed to connect to the internet. Try again? (y/n): ");
                String response = input.nextLine();
                if (response.equalsIgnoreCase("n")) {
                    System.exit(0);
                }
                if (internetAvailable()) {
                    System.out.println("Successfully connected to the internet.");
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkInternet();
            }
        }, 0, 30 * 1000);

        OpenAI openAI = OpenAI.newBuilder("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")
                .organization("org-yg4acDkrKC7IOY6NyEpjZUTx")
                .build();

        ChatClient chatClient = openAI.chatClient();
        CreateChatCompletionRequest createChatCompletionRequest = CreateChatCompletionRequest.newBuilder()
                .model("gpt-3.5-turbo")
                .message(ChatMessage.userMessage("Who won the world series in 2020?"))
                .build();
        ChatCompletion chatCompletion = chatClient.createChatCompletion(createChatCompletionRequest);
        System.out.println(chatCompletion.choices().get(0));
    }
}