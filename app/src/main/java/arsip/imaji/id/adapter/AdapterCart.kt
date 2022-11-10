package arsip.imaji.id.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import arsip.imaji.id.R
import arsip.imaji.id.model.Cart
import com.bumptech.glide.Glide

class AdapterCart(
    private val listPerson: ArrayList<Cart>,
    var adapterCallback: HistoryAdapterCallback,
) : RecyclerView.Adapter<AdapterCart.ListViewHolder>() {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitleProductCart: TextView = itemView.findViewById(R.id.tvTitleProductCart)
        var lokasi: TextView = itemView.findViewById(R.id.lokasi)
        var price: TextView = itemView.findViewById(R.id.price)
        var ivImgProductCart: ImageView = itemView.findViewById(R.id.ivImgProductCart)
        var ivDeleteProduct: ImageView = itemView.findViewById(R.id.ivDeleteProduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.items_cart, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listPerson.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val cart = listPerson[position]
        Glide.with(holder.itemView.context)
            .load(cart.path)
            .into(holder.ivImgProductCart)
        holder.tvTitleProductCart.text = cart.nama
        holder.lokasi.text = cart.lokasi
        holder.price.text = cart.harga.toString()
        holder.ivDeleteProduct.setOnClickListener {
            cart.id?.let { it1 -> adapterCallback.onDeleteCart(it1) }
        }
    }
    interface HistoryAdapterCallback {
        fun onDeleteCart(id: String)
    }

}