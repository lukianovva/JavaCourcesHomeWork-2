package ru.liga.tindertgbot.service.messages;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class ChooseDescriptionMessage {
    public static void setPropertiesTo(SendMessage.SendMessageBuilder sendMessageBuilder) {
        sendMessageBuilder.text("Опишите себя.");
    }
}
