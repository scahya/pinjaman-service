CREATE TYPE status_pinjaman_enum AS ENUM ('Pending', 'Approved', 'Rejected');
create table if not exists pengajuan_pinjaman
(
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    id_nasabah UUID ,
    created_at                          timestamp(6) DEFAULT CURRENT_TIMESTAMP,
    approved_at                         timestamp(6),
    approved_by                         UUID,
    nama_lengkap                        varchar(255),
    alamat                              varchar(255),
    no_telpon                           varchar(255),
    jumlah_pinjaman                     bigint,
    status                              status_pinjaman_enum DEFAULT 'Pending',
    catatan_admin                       varchar(255),

    CONSTRAINT fk_nasabah FOREIGN KEY (id_nasabah) REFERENCES users (id),
    CONSTRAINT fk_approved_by FOREIGN KEY (approved_by) REFERENCES users (id)
);

