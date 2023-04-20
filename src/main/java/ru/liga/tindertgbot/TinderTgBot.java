package ru.liga.tindertgbot;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TinderTgBot {
    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(TinderTgBot.class, args);
    }
}