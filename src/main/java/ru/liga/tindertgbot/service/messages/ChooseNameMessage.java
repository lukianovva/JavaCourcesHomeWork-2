package ru.liga.tindertgbot.service.messages;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class ChooseNameMessage {
    public static void setPropertiesTo(SendMessage.SendMessageBuilder sendMessageBuilder) {
        sendMessageBuilder.text("Как вас величать?");
    }
}
