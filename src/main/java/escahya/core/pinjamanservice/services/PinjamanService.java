package escahya.core.pinjamanservice.services;

import escahya.core.pinjamanservice.dtos.ApprovePengajuanReqDto;
import escahya.core.pinjamanservice.dtos.PengajuanReqDto;
import escahya.core.pinjamanservice.entities.PengajuanPinjaman;
import escahya.core.pinjamanservice.entities.StatusPinjaman;
import escahya.core.pinjamanservice.entities.Users;
import escahya.core.pinjamanservice.repositories.PengajuanPinjamanRepository;
import escahya.core.pinjamanservice.repositories.UsersRepository;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PinjamanService {

    private final PengajuanPinjamanRepository pengajuanPinjamanRepository;
    private final UsersRepository usersRepository;

    public PengajuanPinjaman ajukanPinjaman (PengajuanReqDto dto){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = usersRepository.findByUsername(username) .orElseThrow(() -> new RuntimeException("User not found"));
        PengajuanPinjaman pengajuan = PengajuanPinjaman.builder()
            .nik(dto.getNik())
            .namaLengkap(user.getNamaLengkap())
            .alamat(dto.getAlamat())
            .jumlahPinjaman(dto.getJumlahPinjaman())
            .noTelpon(dto.getNoTelpon())
            .status(StatusPinjaman.Pending)
            .idNasabah(user.getId())
            .build();

       return pengajuanPinjamanRepository.save(pengajuan);
    }

    public List<PengajuanPinjaman> getAllByNasabah(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = usersRepository.findByUsername(username) .orElseThrow(() -> new RuntimeException("User not found"));
        return pengajuanPinjamanRepository.findAllByIdNasabah(user.getId());
    }

    public List<PengajuanPinjaman> getAllPinjaman(){
         return pengajuanPinjamanRepository.findAll();
    }


    public PengajuanPinjaman approvePinjaman(String id, ApprovePengajuanReqDto dto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = usersRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));

        UUID uuid = UUID.fromString(id);

        PengajuanPinjaman pengajuanPinjaman = pengajuanPinjamanRepository.findById(uuid)
            .orElseThrow(() -> new RuntimeException("Pinjaman not found"));

        pengajuanPinjaman.setStatus(StatusPinjaman.valueOf(String.valueOf(dto.getStatus())));
        pengajuanPinjaman.setCatatanAdmin(dto.getCatatanAdmin());
        pengajuanPinjaman.setApprovedByUser(user);
        pengajuanPinjaman.setApprovedAt(new Timestamp(System.currentTimeMillis()));

        return pengajuanPinjamanRepository.save(pengajuanPinjaman);
    }

}
