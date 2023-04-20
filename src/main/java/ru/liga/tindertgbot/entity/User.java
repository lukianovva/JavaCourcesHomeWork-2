package ru.liga.tindertgbot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.liga.tindertgbot.dict.Sex;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private int chatId;
    private String name;
    private Sex sex;
    private String header;
    private String description;
    private Sex preference;

    public String getHeader() {
        return "";
    }
}