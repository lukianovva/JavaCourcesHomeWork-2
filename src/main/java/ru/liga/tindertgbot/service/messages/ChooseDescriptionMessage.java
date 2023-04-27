package ru.liga.tindertgbot.service.messages;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

//todo не нужен отдельный класс для одного метода, который просто меняет поле
//todo попробуй объеденить все классы в этом пакете в один с несколькими методами
public class ChooseDescriptionMessage {
    public static void setPropertiesTo(SendMessage.SendMessageBuilder sendMessageBuilder) {
        sendMessageBuilder.text("Опишите себя.");
    }
}
