package arsip.imaji.id

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import arsip.imaji.id.databinding.ActivityMainBinding
import arsip.imaji.id.view.FavFragment
import arsip.imaji.id.view.HistoryPaymentFragment
import arsip.imaji.id.view.HomeFragment
import arsip.imaji.id.view.ProfileFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()
        loadFragment(HomeFragment())
        binding.menuBottom.setItemSelected(R.id.home)
        binding.menuBottom.setOnItemSelectedListener {
            when (it) {

                R.id.home -> {
                    loadFragment(HomeFragment())
                    return@setOnItemSelectedListener
                }
                R.id.historyPayment -> {
                    loadFragment(HistoryPaymentFragment())
                    return@setOnItemSelectedListener
                }
                R.id.person -> {
                    loadFragment(ProfileFragment())
                    return@setOnItemSelectedListener
                }
            }
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}