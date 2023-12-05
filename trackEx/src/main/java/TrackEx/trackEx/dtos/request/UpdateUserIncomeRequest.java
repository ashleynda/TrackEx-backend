package TrackEx.trackEx.dtos.request;

import lombok.Data;

@Data
public class UpdateUserIncomeRequest {

    private String accountOwnerId;
    private String amount;
}
