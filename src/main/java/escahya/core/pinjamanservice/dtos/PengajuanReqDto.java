package escahya.core.pinjamanservice.dtos;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class PengajuanReqDto {

    private String nik;

    private String alamat;

    private String noTelpon;

    private BigDecimal jumlahPinjaman;

}
