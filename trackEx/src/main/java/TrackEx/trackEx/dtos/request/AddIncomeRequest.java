package TrackEx.trackEx.dtos.request;


import lombok.Data;

@Data
public class AddIncomeRequest {

    private String accountId;
    private String amount;
}
