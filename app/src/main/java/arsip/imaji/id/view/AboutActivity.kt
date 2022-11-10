package arsip.imaji.id.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import arsip.imaji.id.adapter.AdapterAbout
import arsip.imaji.id.databinding.ActivityAboutBinding
import arsip.imaji.id.databinding.ActivityGraduationBinding
import arsip.imaji.id.helper.Constant
import arsip.imaji.id.model.Person

class AboutActivity : AppCompatActivity() {
    lateinit var binding: ActivityAboutBinding
    private var list: ArrayList<Person> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.rvPerson.setHasFixedSize(true)
        list.addAll(Constant.listData)
        binding.rvPerson.layoutManager = LinearLayoutManager(this)
        val foodAdapter = AdapterAbout(list)
        binding.rvPerson.adapter = foodAdapter
    }
}