package ru.liga.tindertgbot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.liga.tindertgbot.dicts.UserSex;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int chatId;
    private String name;
    private UserSex sex;
    private String header;
    private String description;
    private UserSex preference;
}