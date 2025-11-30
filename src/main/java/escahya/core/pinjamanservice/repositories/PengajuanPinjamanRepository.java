package escahya.core.pinjamanservice.repositories;

import escahya.core.pinjamanservice.entities.PengajuanPinjaman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PengajuanPinjamanRepository extends JpaRepository<PengajuanPinjaman, String> {
}
