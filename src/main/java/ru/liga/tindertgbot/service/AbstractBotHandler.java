package ru.liga.tindertgbot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.liga.tindertgbot.service.messages.ChooseDescriptionMessage;
import ru.liga.tindertgbot.service.messages.ChooseNameMessage;
import ru.liga.tindertgbot.service.messages.ChoosePreferenceMessage;
import ru.liga.tindertgbot.service.messages.ChooseSexMessage;

public abstract class AbstractBotHandler {
    protected void buildFillUserDataResponse(String nextEmptyField, SendMessage.SendMessageBuilder sendMessageBuilder) {
        if (null == nextEmptyField) {
            sendMessageBuilder.text("Анкета заполнена!");
            return;
        }

        switch (nextEmptyField) {
            //todo строки для кейсов надо вынести в константы
            case "sex" -> {
                ChooseSexMessage.setPropertiesTo(sendMessageBuilder);
            }
            case "name" -> {
                ChooseNameMessage.setPropertiesTo(sendMessageBuilder);
            }
            case "description" -> {
                ChooseDescriptionMessage.setPropertiesTo(sendMessageBuilder);
            }
            case "preference" -> {
                ChoosePreferenceMessage.setPropertiesTo(sendMessageBuilder);
            }
        }
    }
}
