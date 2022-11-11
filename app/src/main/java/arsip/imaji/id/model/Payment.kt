package arsip.imaji.id.model

data class Payment(
    var price: String? = null,
    var deskripsi: String? = null,
    var harga: Int? = null,
    var lokasi: String? = null,
    var namaBarang: String? = null,
    var pathPhoto: String? = null,
)