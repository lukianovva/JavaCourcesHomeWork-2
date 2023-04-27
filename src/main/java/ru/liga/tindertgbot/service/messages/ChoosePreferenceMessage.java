package ru.liga.tindertgbot.service.messages;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChoosePreferenceMessage {
    public static void setPropertiesTo(SendMessage.SendMessageBuilder sendMessageBuilder) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

        buttons.add(Arrays.asList(
                //todo создание объекта можно вынести в метод
                InlineKeyboardButton.builder().text("Сударъ").callbackData("SET_PREFERENCE:MALE").build(),
                InlineKeyboardButton.builder().text("Сударыня").callbackData("SET_PREFERENCE:FEMALE").build(),
                InlineKeyboardButton.builder().text("Всех").callbackData("SET_PREFERENCE:ALL").build()
        ));

        sendMessageBuilder
                .text("Кого вы ищите?")
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build());
    }
}
