package ru.liga.tindertgbot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private long chatId;
    private String name;
    private Sex sex;
    private String header;
    private String description;
    private Sex preference;

    @JsonIgnore
    public String getNextEmptyField() {
        //todo ставь лучше null справа при сравнении, разницы нет, просто так принято и привычнее выглядит
        if (null == sex) {
            return "sex";
        }
        if (null == name) {
            return "name";
        }
        if (null == description) {
            return "description";
        }
        if (null == preference) {
            return "preference";
        }

        return null;
    }
}