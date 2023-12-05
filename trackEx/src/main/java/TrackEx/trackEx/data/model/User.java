package TrackEx.trackEx.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document("User")
public class User {

    @Id
    private String id;
    private String email;
    private String password;
    private boolean loggedIn;
    private BigDecimal income;
    private BigDecimal savingGoal;
    private BigDecimal balance;
}
