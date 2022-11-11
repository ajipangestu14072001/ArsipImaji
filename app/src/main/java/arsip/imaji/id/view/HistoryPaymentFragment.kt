package arsip.imaji.id.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import arsip.imaji.id.adapter.AdapterHistoryPayment
import arsip.imaji.id.databinding.FragmentHistoryPaymentBinding
import arsip.imaji.id.helper.SavedPreference
import arsip.imaji.id.model.Buy
import com.google.firebase.database.*

class HistoryPaymentFragment : Fragment() {
    var adapter: AdapterHistoryPayment? = null
    private lateinit var dbRef : DatabaseReference
    private var buy: ArrayList<Buy> = arrayListOf()
    private lateinit var binding: FragmentHistoryPaymentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentHistoryPaymentBinding.inflate(layoutInflater)
        val user = SavedPreference.getUsername(requireContext())
        binding.historyPayment.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.historyPayment.setHasFixedSize(true)

        buy = arrayListOf()
        dbRef = FirebaseDatabase.getInstance().getReference("Transaksi")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (i in snapshot.children) {
                        val type = i.child("user").value.toString();
                        if (type == user) {
                            val product = i.getValue(Buy::class.java)
                            Log.i(product!!.toString(), "isinya");
                            buy.add(product!!)
                        }
                    }
                    binding.historyPayment.adapter = AdapterHistoryPayment(buy)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        return binding.root
    }
}