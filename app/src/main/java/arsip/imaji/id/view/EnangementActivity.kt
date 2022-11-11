package arsip.imaji.id.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import arsip.imaji.id.adapter.ProductAdapter
import arsip.imaji.id.callback.FetchRecyclerViewItems
import arsip.imaji.id.databinding.ActivityEnangementBinding
import arsip.imaji.id.helper.Constant
import arsip.imaji.id.helper.SavedPreference
import arsip.imaji.id.model.Buy
import arsip.imaji.id.model.Cart
import arsip.imaji.id.model.DataObject
import com.google.firebase.database.*


class EnangementActivity : AppCompatActivity(), FetchRecyclerViewItems {
    lateinit var binding: ActivityEnangementBinding
    private lateinit var dbRef : DatabaseReference
    private lateinit var productList : ArrayList<DataObject>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnangementBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)
        Constant.databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path)
        binding.recycler1.layoutManager = GridLayoutManager(applicationContext, 2)
        binding.recycler1.setHasFixedSize(true)
        productList = arrayListOf()
        dbRef = FirebaseDatabase.getInstance().getReference("ArsipImaji")
        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (i in snapshot.children){
                        val type = i.child("kategori").value.toString()
                        if (type == "Enangement"){
                            val product = i.getValue(DataObject::class.java)
                            productList.add(product!!)
                        }
                    }
                    binding.recycler1.adapter = ProductAdapter(productList, this@EnangementActivity)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

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
                    this@EnangementActivity,
                    "Hehehe Cart Berhasil ditambahkan..",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@EnangementActivity, "Waduh gagal gan..", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    companion object {
        const val Database_Path = "Cart"
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}