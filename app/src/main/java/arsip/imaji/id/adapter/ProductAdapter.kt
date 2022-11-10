package arsip.imaji.id.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import arsip.imaji.id.R
import arsip.imaji.id.callback.FetchRecyclerViewItems
import arsip.imaji.id.model.DataObject
import arsip.imaji.id.view.DetailActivity
import com.bumptech.glide.Glide

class ProductAdapter(
    private val product: ArrayList<DataObject>,
    var listener: FetchRecyclerViewItems
): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    class ProductViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val tvPlacePromo : TextView = itemView.findViewById(R.id.tvPlacePromo)
        val titleProductPromo : TextView = itemView.findViewById(R.id.titleProductPromo)
        val tvpriceAfterPromo : TextView = itemView.findViewById(R.id.tvpriceAfterPromo)
        val tvLocationPromo : TextView = itemView.findViewById(R.id.tvLocationPromo)
        val clyProductCariPromo : ConstraintLayout = itemView.findViewById(R.id.clyProductCariPromo)
        val image : ImageView = itemView.findViewById(R.id.ivProductCaripromo)
        val button : CardView = itemView.findViewById(R.id.cvAddToCart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_product,parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentTime = product[position]
        holder.tvPlacePromo.text = currentTime.lokasi
        holder.titleProductPromo.text = currentTime.namaBarang
        holder.tvpriceAfterPromo.text = currentTime.harga.toString()
        holder.tvLocationPromo.text = currentTime.lokasi
        Glide.with(holder.image.context)
            .asBitmap()
            .load(currentTime.pathPhoto)
            .into(holder.image)
        holder.clyProductCariPromo.setOnClickListener{
            it.context.startActivity(Intent(it.context, DetailActivity::class.java))
        }
        holder.button.setOnClickListener {
            listener.onItemClicked(it, currentTime)
        }
    }

    override fun getItemCount(): Int {
       return product.size
    }
}