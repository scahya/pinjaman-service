package escahya.core.pinjamanservice.dtos;

import escahya.core.pinjamanservice.entities.StatusPinjaman;
import lombok.Data;

@Data
public class ApprovePengajuanReqDto {

    private String catatanAdmin;
    private StatusPinjaman status;
}
