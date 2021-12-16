package nl.hu.bep3.groep2.overviewmanger;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomMessage {
    private String messageId;
    private String message;
    private Date messageDate;

    @JsonCreator
    public CustomMessage(String messageId, String message, Date messageDate) {
        this.messageId = messageId;
        this.message = message;
        this.messageDate = messageDate;
    }
}
