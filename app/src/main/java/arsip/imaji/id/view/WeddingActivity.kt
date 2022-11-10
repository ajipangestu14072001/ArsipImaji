package arsip.imaji.id.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import arsip.imaji.id.adapter.ProductAdapter
import arsip.imaji.id.databinding.ActivityWeddingBinding
import arsip.imaji.id.model.DataObject
import com.google.firebase.database.*

class WeddingActivity : AppCompatActivity() {
    lateinit var binding: ActivityWeddingBinding
    private lateinit var dbRef : DatabaseReference
    private lateinit var productList : ArrayList<DataObject>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeddingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.recycler1.layoutManager = GridLayoutManager(applicationContext, 2)
        binding.recycler1.setHasFixedSize(true)
        productList = arrayListOf()
        dbRef = FirebaseDatabase.getInstance().getReference("ArsipImaji")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (i in snapshot.children){
                        val type = i.child("kategori").value.toString()
                        if (type == "Prewed"){
                            val product = i.getValue(DataObject::class.java)
                            productList.add(product!!)
                        }
                    }
                    binding.recycler1.adapter = ProductAdapter(productList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}