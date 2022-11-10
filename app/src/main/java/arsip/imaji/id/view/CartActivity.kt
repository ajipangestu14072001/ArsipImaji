package arsip.imaji.id.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import arsip.imaji.id.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    lateinit var binding: ActivityCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}