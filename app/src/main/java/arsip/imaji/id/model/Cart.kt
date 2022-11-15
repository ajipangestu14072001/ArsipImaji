package arsip.imaji.id.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cart(
    val id: String? = null,
    val nama: String? = null,
    val harga: Int? = null,
    val kategori : String? = null,
    val deskripsi: String? = null,
    val lokasi: String? = null,
    val path: String? = null,
    val user: String? = null
) : Parcelable
