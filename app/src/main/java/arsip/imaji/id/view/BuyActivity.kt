package arsip.imaji.id.view

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import arsip.imaji.id.callback.FetchRecyclerViewItems
import arsip.imaji.id.databinding.ActivityBuyBinding
import arsip.imaji.id.helper.Constant
import arsip.imaji.id.helper.SavedPreference
import arsip.imaji.id.model.Buy
import arsip.imaji.id.model.DataObject
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*


class BuyActivity : AppCompatActivity(), FetchRecyclerViewItems {
    lateinit var binding: ActivityBuyBinding
    val myCalendar: Calendar = Calendar.getInstance()
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Constant.databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path)
        val date =
            OnDateSetListener { _, year, month, day ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = month
                myCalendar[Calendar.DAY_OF_MONTH] = day
                val myFormat = "MM/dd/yy"
                val dateFormat = SimpleDateFormat(myFormat, Locale.US)
                binding.tanggal.setText(dateFormat.format(myCalendar.time))
            }
        binding.tanggal.setOnClickListener {
            DatePickerDialog(
                this@BuyActivity,
                date,
                myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        }
        binding.buyButton.setOnClickListener {
            val dateNow = Calendar.getInstance().time
            val key = Constant.databaseReference?.push()?.key
            val buy = Buy(
                key.toString(),
                "data.namaBarang",
                10000,
                SavedPreference.getUsername(applicationContext),
                dateNow.toString(),
                "data.lokasi",
                "",
                binding.alamat.text.toString(),
                binding.phone.text.toString(),
                binding.tanggal.text.toString(),
                binding.note.text.toString()
            )
            Constant.databaseReference?.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    Constant.databaseReference?.child("List$key")?.setValue(buy)
                    Toast.makeText(
                        this@BuyActivity,
                        "Hehehe Transaksi Berhasi Dibuat..",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@BuyActivity, "Waduh gagal gan..", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }
    }
    companion object {
        const val Database_Path = "Transaksi"
    }

    override fun onItemClicked(view: View, product: DataObject) {
        TODO("Not yet implemented")
    }

}