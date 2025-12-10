package id.ac.pnm.tix_rail

data class JadwalItem(
    val namaKereta: String,
    val kodePemesanan: String,
    val hargaMulai: String,
    val waktuBerangkat: String,
    val waktuTiba: String,
    val durasi: String,
    val stasiunAsal: String,
    val stasiunTujuan: String,
    val tanggalBerangkat: String,
    val tanggalTiba: String
)