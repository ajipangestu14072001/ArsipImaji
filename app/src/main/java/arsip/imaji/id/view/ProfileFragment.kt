package arsip.imaji.id.view

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import arsip.imaji.id.databinding.FragmentProfileBinding
import arsip.imaji.id.helper.Constant
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    lateinit var mGoogleSignInClient: GoogleSignInClient
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(Constant.clientID)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        val sharedPreferences = requireContext().getSharedPreferences("map", MODE_PRIVATE)
        val urlImage = sharedPreferences.getString("img", "")
        Glide.with(requireContext())
            .asBitmap()
            .load(urlImage)
            .into(binding.profileImage)

        binding.card1.setOnClickListener {
            startActivity(Intent(requireContext(), AccountActivity::class.java))
        }
        binding.card2.setOnClickListener {
            startActivity(Intent(requireContext(), HelpActivity::class.java))
        }
        binding.card3.setOnClickListener {
            mGoogleSignInClient.signOut().addOnCompleteListener {
                startActivity(Intent(requireContext(), LoginActivity::class.java))
                Toast.makeText(requireContext(), "Logging Out", Toast.LENGTH_SHORT).show()
                activity?.finish()
            }
        }
        return binding.root
    }
}