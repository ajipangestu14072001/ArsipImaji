package arsip.imaji.id.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import arsip.imaji.id.R
import arsip.imaji.id.model.Buy
import arsip.imaji.id.model.Person
import com.bumptech.glide.Glide

class AdapterHistoryPayment(private val listHistory: ArrayList<Buy>) : RecyclerView.Adapter<AdapterHistoryPayment.ListViewHolder>() {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvBookingTimeCariPromo: TextView = itemView.findViewById(R.id.tvBookingTimeCariPromo)
        var tvPacketName: TextView = itemView.findViewById(R.id.tvPacketName)
        var tvMoreProduct: TextView = itemView.findViewById(R.id.tvMoreProduct)
        var price: TextView = itemView.findViewById(R.id.price)
        var ivImgProductCart: ImageView = itemView.findViewById(R.id.imgProduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listHistory.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val historyPayment = listHistory[position]
        Glide.with(holder.itemView.context)
            .load("")
            .into(holder.ivImgProductCart)
        holder.tvBookingTimeCariPromo.text = historyPayment.tanggalDibutuhkan
        holder.tvPacketName.text = historyPayment.namaBarang
        holder.tvMoreProduct.text = historyPayment.lokasi
        holder.price.text = historyPayment.jumlahHarga.toString()
    }

}