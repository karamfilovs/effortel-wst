package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
public class Client {
    private String name;
    private String town;
    private String address;
    private String mol;
    private boolean is_reg_vat;
    private String bulstat;
}
