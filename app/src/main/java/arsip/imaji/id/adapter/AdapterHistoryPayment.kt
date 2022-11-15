package arsip.imaji.id.adapter

import android.os.CountDownTimer
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
        var tvExpiredPayment: TextView = itemView.findViewById(R.id.tvExpiredPayment)
        var price: TextView = itemView.findViewById(R.id.price)
        var ivImgProductCart: ImageView = itemView.findViewById(R.id.imgProduct)
        var icRoundedCheckStatus: ImageView = itemView.findViewById(R.id.icRoundedCheckStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listHistory.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var status = "Payment Pending"
        val historyPayment = listHistory[position]
        Glide.with(holder.itemView.context)
            .load(historyPayment.pathPhoto)
            .into(holder.ivImgProductCart)
        holder.tvBookingTimeCariPromo.text = historyPayment.tanggalDibutuhkan
        holder.tvPacketName.text = historyPayment.namaBarang
        holder.tvMoreProduct.text = historyPayment.lokasi
        holder.tvExpiredPayment.text = status
        holder.price.text = historyPayment.jumlahHarga.toString()
        val timer = object: CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                status = "Payment Success"
                holder.tvExpiredPayment.text = status
                holder.icRoundedCheckStatus.visibility = View.VISIBLE
            }
        }
        timer.start()
    }

}