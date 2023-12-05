package TrackEx.trackEx.dtos.request;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class AddExpensesRequest {

    private String category;
    private String description;
    private String amount;
    private String accountOwnerId;
}
