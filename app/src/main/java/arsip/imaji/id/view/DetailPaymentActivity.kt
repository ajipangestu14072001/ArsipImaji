package arsip.imaji.id.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import arsip.imaji.id.R
import arsip.imaji.id.adapter.AdapterAbout
import arsip.imaji.id.adapter.AdapterPayment
import arsip.imaji.id.adapter.ProductAdapter
import arsip.imaji.id.databinding.ActivityDetailPaymentBinding
import arsip.imaji.id.helper.Constant
import arsip.imaji.id.model.DataObject
import arsip.imaji.id.model.Payment
import arsip.imaji.id.model.Person
import com.google.firebase.database.*

class DetailPaymentActivity : AppCompatActivity() {
    lateinit var binding:ActivityDetailPaymentBinding
    private lateinit var dbRef : DatabaseReference
    private lateinit var productList : ArrayList<DataObject>
    private  var list: ArrayList<Payment> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailPaymentBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.rvPricePayment.setHasFixedSize(true)
        productList = arrayListOf()
        list.addAll(Constant.listDataPayment)
        binding.rvPricePayment.layoutManager = LinearLayoutManager(this)
        val foodAdapter = AdapterPayment(list)
        binding.rvPricePayment.adapter = foodAdapter
//        dbRef = FirebaseDatabase.getInstance().getReference("ArsipImaji")
//        dbRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()){
//                    for (i in snapshot.children){
//                        val product = i.getValue(DataObject::class.java)
//                        productList.add(product!!)
//                    }
//                    binding.rvPricePayment.adapter = ProductAdapter(productList)
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        })
    }
}