package TrackEx.trackEx.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@Document("Expenses")
public class Expenses {

    @Id
    private String id;
    private String category;
    private String description;
    private BigDecimal amount;
    private LocalDate dateCreated = LocalDate.now();

    @DBRef
    private User accountOwner;
}
