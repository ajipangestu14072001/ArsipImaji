package arsip.imaji.id.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import arsip.imaji.id.adapter.AdapterPayment
import arsip.imaji.id.callback.FetchRecyclerViewItems
import arsip.imaji.id.databinding.ActivityDetailPaymentBinding
import arsip.imaji.id.helper.SavedPreference
import arsip.imaji.id.model.Cart
import arsip.imaji.id.model.DataObject
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.firebase.database.*

class DetailPaymentActivity : AppCompatActivity() {
    lateinit var binding:ActivityDetailPaymentBinding
    private lateinit var productList : ArrayList<DataObject>
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailPaymentBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)
        productList = arrayListOf()
        val intent: Intent = intent
        val data : DataObject? = intent.getParcelableExtra("product")
        val cart : Cart? = intent.getParcelableExtra("cart")
        if (data != null){
            binding.amount.text = "RP."+ data.harga.toString()
            Glide.with(applicationContext)
                .asBitmap()
                .load(data.pathPhoto)
                .into(binding.imgPayment)
            binding.pay.setOnClickListener {
                val i = Intent(this, BuyActivity::class.java)
                i.putExtra("namaBarang", data.namaBarang)
                i.putExtra("pathPhoto", data.pathPhoto)
                i.putExtra("lokasi", data.lokasi)
                i.putExtra("harga", data.harga)
                startActivity(i)
            }
            binding.lokasi.text = data.lokasi
            binding.tvPricePayment.text = data.harga.toString()
            binding.tvTitleProduct.text = data.namaBarang
            binding.tvLocation.text = data.kategori
            binding.desc.text = data.deskripsi
        }else{
            binding.amount.text = "RP."+ cart?.harga.toString()
            Glide.with(applicationContext)
                .asBitmap()
                .load(cart?.path)
                .into(binding.imgPayment)
            binding.pay.setOnClickListener {
                val i = Intent(this, BuyActivity::class.java)
                i.putExtra("namaBarang", cart?.nama)
                i.putExtra("pathPhoto", cart?.path)
                i.putExtra("lokasi", cart?.lokasi)
                i.putExtra("harga", cart?.harga)
                startActivity(i)
            }
            binding.lokasi.text = cart?.lokasi
            binding.tvPricePayment.text = cart?.harga.toString()
            binding.tvTitleProduct.text = cart?.nama
            binding.tvLocation.text = cart?.kategori
            binding.desc.text = cart?.deskripsi
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}