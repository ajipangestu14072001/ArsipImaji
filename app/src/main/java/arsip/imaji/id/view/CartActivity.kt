package arsip.imaji.id.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import arsip.imaji.id.MainActivity
import arsip.imaji.id.adapter.AdapterCart
import arsip.imaji.id.databinding.ActivityCartBinding
import arsip.imaji.id.helper.SavedPreference
import arsip.imaji.id.model.Cart
import com.google.firebase.database.*

class CartActivity : AppCompatActivity(), AdapterCart.HistoryAdapterCallback {
    lateinit var binding: ActivityCartBinding
    private lateinit var dbRef : DatabaseReference
    private lateinit var cart : ArrayList<Cart>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        val view = binding.root
        val user = SavedPreference.getUsername(applicationContext)
        setContentView(view)
        setSupportActionBar(binding.toolbar)
        binding.rvCart.layoutManager = LinearLayoutManager(applicationContext)
        binding.rvCart.setHasFixedSize(true)
        cart = arrayListOf()
        dbRef = FirebaseDatabase.getInstance().getReference("Cart")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (i in snapshot.children){
                        val type = i.child("user").value.toString()
                        if (type == user){
                            val product = i.getValue(Cart::class.java)
                            cart.add(product!!)
                        }

                    }
                    binding.rvCart.adapter = AdapterCart(cart, this@CartActivity)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        SavedPreference.isBackFromList = true
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onDeleteCart(id: String) {
        Toast.makeText(applicationContext, id, Toast.LENGTH_SHORT).show()
        dbRef.child("List$id").removeValue()
        startActivity(Intent(applicationContext, MainActivity::class.java))
        overridePendingTransition(0, 0)
        finish()
    }

    override fun getData(cart: Cart) {
        val intent = Intent(this, DetailPaymentActivity::class.java)
        intent.putExtra("cart", cart);
        startActivity(intent)
    }

}