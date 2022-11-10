package arsip.imaji.id.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import arsip.imaji.id.R
import arsip.imaji.id.databinding.FragmentFavBinding
import arsip.imaji.id.databinding.FragmentHomeBinding

class FavFragment : Fragment() {
    private lateinit var binding: FragmentFavBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavBinding.inflate(inflater, container, false)
        return binding.root
    }
}