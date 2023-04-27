package ru.liga.tindertgbot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.liga.tindertgbot.entity.User;

@Service
public class BotTextHandlerService extends AbstractBotHandler {

    private UserService userService;

    public BotTextHandlerService(UserService userService) {
        this.userService = userService;
    }

    public SendMessage handle(long chatId, String value) {
        SendMessage.SendMessageBuilder sendMessageBuilder = SendMessage.builder();
        sendMessageBuilder.chatId(chatId).text("Обработчик отсутсвует");

        User user = userService.get(chatId);

        String nextEmptyField = user.getNextEmptyField();

        switch (nextEmptyField) {
            //todo "name" и "description" лучше вынести в константы и назвать по базнесу
            case "name" -> {
                setName(chatId, value, sendMessageBuilder);
            }
            case "description" -> {
                setDescription(chatId, value, sendMessageBuilder);
            }
        }

        return sendMessageBuilder.build();
    }

    private void setName(long chatId, String name, SendMessage.SendMessageBuilder response) {
        User user = userService.setName(chatId, name);

        buildFillUserDataResponse(user.getNextEmptyField(), response);
    }

    private void setDescription(long chatId, String description, SendMessage.SendMessageBuilder response) {
        User user = userService.setDescription(chatId, description);

        buildFillUserDataResponse(user.getNextEmptyField(), response);
    }
}
