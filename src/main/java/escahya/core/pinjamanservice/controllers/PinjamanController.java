package escahya.core.pinjamanservice.controllers;

import escahya.core.pinjamanservice.dtos.ApprovePengajuanReqDto;
import escahya.core.pinjamanservice.dtos.JwtResponse;
import escahya.core.pinjamanservice.dtos.LoginReqDto;
import escahya.core.pinjamanservice.dtos.PengajuanReqDto;
import escahya.core.pinjamanservice.dtos.RegisterReqDto;
import escahya.core.pinjamanservice.entities.PengajuanPinjaman;
import escahya.core.pinjamanservice.services.PinjamanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pinjaman")
public class PinjamanController {

    private final PinjamanService pinjamanService;

    @PostMapping("/ajukan")
    public ResponseEntity<Object> ajukanPinjaman(@RequestBody PengajuanReqDto dto) {

        return new ResponseEntity<>(pinjamanService.ajukanPinjaman(dto), HttpStatus.OK);
    }


    @GetMapping("/nasabah")
    public ResponseEntity<Object> getAllPinjamanByNasabah() {

        return new ResponseEntity<>( pinjamanService.getAllByNasabah(), HttpStatus.OK);

    }


    @PreAuthorize("hasAnyRole('Admin')")
    @GetMapping("/list")
    public ResponseEntity<Object> getAllPinjaman() {

        return new ResponseEntity<>( pinjamanService.getAllPinjaman(), HttpStatus.OK);

    }

    @PreAuthorize("hasAnyRole('Admin')")
    @PutMapping("/{id}/approve")
    public ResponseEntity<Object> getAllPinjaman(@PathVariable("id") String id, @RequestBody ApprovePengajuanReqDto dto) {
        PengajuanPinjaman pengajuanPinjaman = pinjamanService.approvePinjaman(id, dto);
        return new ResponseEntity<>( pengajuanPinjaman, HttpStatus.OK);

    }

}
