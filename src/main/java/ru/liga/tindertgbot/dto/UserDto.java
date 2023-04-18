package ru.liga.tindertgbot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int chatId;
    private String name;
    private String sex;
    private String header;
    private String description;
    private String preference;
}