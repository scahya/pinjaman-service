package escahya.core.pinjamanservice.dtos;

import escahya.core.pinjamanservice.entities.Role;
import lombok.Data;

@Data
public class RegisterReqDto {
    private String username;
    private String password;
    private String namaLengkap;

    private Role role;
}
