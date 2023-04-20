package ru.liga.tindertgbot.service.messages;


import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChooseSexMessage {
    public static void setPropertiesTo(SendMessage.SendMessageBuilder sendMessageBuilder) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text("Сударъ").callbackData("SET_SEX:MALE").build(),
                InlineKeyboardButton.builder().text("Сударыня").callbackData("SET_SEX:FEMALE").build()
        ));

        sendMessageBuilder
                .text("Вы сударь иль сударыня?")
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build());
    }
}
