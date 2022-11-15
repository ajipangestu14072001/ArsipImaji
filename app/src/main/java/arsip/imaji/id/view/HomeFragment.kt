package arsip.imaji.id.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import arsip.imaji.id.adapter.AdapterHome
import arsip.imaji.id.adapter.AdapterMore
import arsip.imaji.id.adapter.SliderAdapter
import arsip.imaji.id.callback.FetchRecyclerViewItems
import arsip.imaji.id.databinding.FragmentHomeBinding
import arsip.imaji.id.helper.SavedPreference
import arsip.imaji.id.helper.SliderItems
import arsip.imaji.id.model.Cart
import arsip.imaji.id.model.DataObject
import com.google.firebase.database.*
import java.lang.Math.abs

class HomeFragment : Fragment(), FetchRecyclerViewItems {
    var adapter: AdapterHome? = null
    private val handler = Handler()
    private lateinit var dbRef : DatabaseReference
    private lateinit var cartRef : DatabaseReference
    private lateinit var productList : ArrayList<DataObject>
    private lateinit var cart : ArrayList<Cart>
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val imageList: MutableList<SliderItems> = ArrayList()
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = AdapterHome(requireContext())
        binding.recyclerView.adapter = adapter

        imageList.add(SliderItems("https://thumbs.dreamstime.com/b/camera-pencil-brush-banner-pencils-brushes-photography-design-art-concept-website-72389892.jpg"))
        imageList.add(SliderItems("https://thumbs.dreamstime.com/b/camera-pencil-brush-banner-pencils-brushes-photography-design-art-concept-website-72389892.jpg"))
        imageList.add(SliderItems("https://thumbs.dreamstime.com/b/camera-pencil-brush-banner-pencils-brushes-photography-design-art-concept-website-72389892.jpg"))
        imageList.add(SliderItems("https://thumbs.dreamstime.com/b/camera-pencil-brush-banner-pencils-brushes-photography-design-art-concept-website-72389892.jpg"))

        val imageAdapter = SliderAdapter(imageList, binding.viewPagerImageSlider)
        binding.viewPagerImageSlider.adapter = imageAdapter
        binding.viewPagerImageSlider.offscreenPageLimit = 3
        binding.viewPagerImageSlider.clipChildren = false
        binding.viewPagerImageSlider.clipToPadding = false
        binding.viewPagerImageSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(65))
        compositePageTransformer.addTransformer { page: View, position: Float ->
            val r = 1 - abs(position)
            page.scaleX = 0.90f + r * 0.25f
        }
        binding.viewPagerImageSlider.setPageTransformer(compositePageTransformer)
        binding.viewPagerImageSlider.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 5000)
            }
        })

        binding.pilihan.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false )
        binding.pilihan.setHasFixedSize(true)
        productList = arrayListOf()
        dbRef = FirebaseDatabase.getInstance().getReference("ArsipImaji")
        cartRef = FirebaseDatabase.getInstance().getReference("Cart")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (i in snapshot.children){
                        val product = i.getValue(DataObject::class.java)
                        productList.add(product!!)
                    }
                    binding.pilihan.adapter = AdapterMore(requireContext(), productList, this@HomeFragment)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        binding.title.setOnClickListener {
            startActivity(Intent(context, AddActivity::class.java))
        }

        binding.clyCart.setOnClickListener {
            startActivity(Intent(context, CartActivity::class.java))
        }
        cart = arrayListOf()
        getSize()
        return binding.root
    }
    private val runnable = Runnable {
        binding.viewPagerImageSlider.currentItem = binding.viewPagerImageSlider.currentItem + 1
    }

    override fun onResume() {
        super.onResume()
        if (SavedPreference.isBackFromList){
            fragmentManager?.beginTransaction()?.detach(this)?.attach(this)?.commit()
        }
    }

    private fun getSize(){
        val user = SavedPreference.getUsername(requireContext())
        cartRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (i in snapshot.children){
                        val type = i.child("user").value.toString()
                        if (type == user){
                            val product = i.getValue(Cart::class.java)
                            cart.add(product!!)
                        }
                    }
                }
                if (cart.size > 0)
                    binding.cvCart.visibility = View.VISIBLE
                binding.tvCountCart.text = cart.size.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onItemClicked(view: View, product: DataObject) {
        TODO("Not yet implemented")
    }

    override fun onIntent(product: DataObject) {
//        val intent = Intent(this, DetailPaymentActivity::class.java)
//        intent.putExtra("product", product);
//        startActivity(intent)
        val intent = Intent(activity, DetailPaymentActivity::class.java)
        intent.putExtra("product", product);
        startActivity(intent)
    }

}