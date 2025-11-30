package escahya.core.pinjamanservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pengajuan_pinjaman")
public class PengajuanPinjaman {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "id_nasabah")
    private UUID idNasabah;

    @Column(name = "approved_by")
    private UUID approvedBy;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "approved_at")
    private Timestamp approvedAt;

    @Column(name = "nama_lengkap")
    private String namaLengkap;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "no_telepon")
    private String noTelpon;

    @Column(name = "jumlah_pinjaman")
    private BigDecimal jumlahPinjaman;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusPinjaman status;

    @Column(name = "catatan_admin")
    private String catatanAdmin;

}

