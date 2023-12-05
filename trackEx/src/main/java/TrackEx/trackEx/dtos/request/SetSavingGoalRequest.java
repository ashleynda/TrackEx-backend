package TrackEx.trackEx.dtos.request;

import lombok.Data;

@Data
public class SetSavingGoalRequest {

    private String accountOwnerId;
    private String amount;

}
