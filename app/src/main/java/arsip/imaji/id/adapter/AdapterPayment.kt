package arsip.imaji.id.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import arsip.imaji.id.R
import arsip.imaji.id.model.DataObject
import arsip.imaji.id.model.Payment
import arsip.imaji.id.model.Person
import arsip.imaji.id.view.DetailPaymentActivity
import com.bumptech.glide.Glide

class AdapterPayment(private val listPayment: ArrayList<Payment>) : RecyclerView.Adapter<AdapterPayment.ListViewHolder>() {
     class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tvTopProduct)
        var tvDetail: TextView = itemView.findViewById(R.id.tvTitleProduct)
        var tvLocation: TextView = itemView.findViewById(R.id.tvLocation)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_payment)
        var price: TextView = itemView.findViewById(R.id.tvPricePayment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_payment, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listPayment.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val food = listPayment[position]
        Glide.with(holder.itemView.context)
            .load(food.pathPhoto)
            .into(holder.imgPhoto)
        holder.tvName.text = food.lokasi
        holder.price.text =  food.harga.toString()
        holder.tvLocation.text = food.lokasi
        holder.tvDetail.text = food.namaBarang
    }

}