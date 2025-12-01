package escahya.core.pinjamanservice.repositories;

import escahya.core.pinjamanservice.entities.PengajuanPinjaman;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PengajuanPinjamanRepository extends JpaRepository<PengajuanPinjaman, UUID> {
    List<PengajuanPinjaman> findAllByIdNasabah(UUID idNasabah);
}
