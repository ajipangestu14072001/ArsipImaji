package arsip.imaji.id.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import arsip.imaji.id.adapter.ProductAdapter
import arsip.imaji.id.callback.FetchRecyclerViewItems
import arsip.imaji.id.databinding.ActivityAllProductBinding
import arsip.imaji.id.helper.Constant
import arsip.imaji.id.helper.SavedPreference
import arsip.imaji.id.model.Cart
import arsip.imaji.id.model.DataObject
import com.google.firebase.database.*

class AllProductActivity : AppCompatActivity(), FetchRecyclerViewItems {
    lateinit var binding : ActivityAllProductBinding
    private lateinit var dbRef : DatabaseReference
    private lateinit var productList : ArrayList<DataObject>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllProductBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Constant.databaseReference = FirebaseDatabase.getInstance().getReference(EnangementActivity.Database_Path)
        setSupportActionBar(binding.toolbar)
        binding.recycler1.layoutManager = GridLayoutManager(applicationContext, 2)
        binding.recycler1.setHasFixedSize(true)
        productList = arrayListOf()
        dbRef = FirebaseDatabase.getInstance().getReference("ArsipImaji")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (i in snapshot.children){
                            val product = i.getValue(DataObject::class.java)
                            productList.add(product!!)
                    }
                    binding.recycler1.adapter = ProductAdapter(productList, this@AllProductActivity)
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

    override fun onItemClicked(view: View, product: DataObject) {
        val key = Constant.databaseReference?.push()?.key
        val cart = Cart(
            key.toString(),
            product.namaBarang,
            product.harga,
            product.lokasi,
            product.pathPhoto,
            SavedPreference.getUsername(applicationContext)
        )
        Constant.databaseReference?.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Constant.databaseReference?.child("List$key")?.setValue(cart)
                Toast.makeText(
                    this@AllProductActivity,
                    "Hehehe Cart Berhasil ditambahkan..",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@AllProductActivity, "Waduh gagal gan..", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

}