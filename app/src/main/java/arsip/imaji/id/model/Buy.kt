package arsip.imaji.id.model

data class Buy(
    val id: String? = null,
    val namaBarang: String? = null,
    val jumlahHarga : Int? = null,
    val user: String? = null,
    val date: String? = null,
    val lokasi: String? = null,
    val paymentMethod: String? = null,
    val address: String? = null,
    val phone: String? = null,
    val tanggalDibutuhkan: String? = null,
    val note: String? = null,
    val pathPhoto: String? = null
)
