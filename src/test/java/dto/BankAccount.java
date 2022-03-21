package dto;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BankAccount {

    private String id;
    private String bank_en;
    private String alias;
    private String bank;
    private String iban;
    private String bic;
    private String currency;
    private String address;
    @Expose(serialize = false, deserialize = true)
    private boolean deleted;
}
