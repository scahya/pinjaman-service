CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TYPE role_enum AS ENUM ('Nasabah', 'Admin');

CREATE TABLE users (
     id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
     username VARCHAR(100) NOT NULL unique ,
     password VARCHAR(100) NOT NULL,
     nama_lengkap VARCHAR(100) NOT NULL,
     role role_enum DEFAULT 'Nasabah',
     created_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP
);