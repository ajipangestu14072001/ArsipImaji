package arsip.imaji.id.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataObject(
    val idProduct: String? = null,
    val deskripsi: String? = null,
    val harga: Int? = null,
    val kategori: String? = null,
    val lokasi: String? = null,
    val namaBarang: String? = null,
    val pathPhoto: String? = null,
    val stock: Int? = null
) : Parcelable