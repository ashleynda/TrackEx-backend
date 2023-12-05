package TrackEx.trackEx.dtos.response;

import lombok.Data;

@Data
public class UpdateUserIncomeResponse {

    private String message;
    private String id;
    private String newIncome;
}
