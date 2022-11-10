package arsip.imaji.id.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorCompat
import arsip.imaji.id.R
import arsip.imaji.id.databinding.ActivitySplashBinding


class SplashActivity : AppCompatActivity() {
    var waktu = 4000
    lateinit var binding : ActivitySplashBinding
    private val animationStarted = false
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_CenterAnimation)
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Handler().postDelayed(Runnable {
            if (isConnected) {
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                finish()
                return@Runnable
            }
            Toast.makeText(applicationContext, "Lost Connection", Toast.LENGTH_SHORT).show()
        }, waktu.toLong())
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        if (!hasFocus || animationStarted) {
            return
        }
        animate()
        super.onWindowFocusChanged(true)
        hideSystemUI(window)
    }

    private fun animate() {
        val logoImageView: ImageView = findViewById<View>(R.id.img_logo) as ImageView
        val container = findViewById<View>(R.id.container) as ViewGroup
        ViewCompat.animate(logoImageView)
            .translationY(-180f)
            .setStartDelay(STARTUP_DELAY.toLong())
            .setDuration(ANIM_ITEM_DURATION.toLong()).setInterpolator(
                DecelerateInterpolator(1.2f)
            ).start()
        for (i in 0 until container.childCount) {
            val v: View = container.getChildAt(i)
            val viewAnimator: ViewPropertyAnimatorCompat = if (v !is Button) {
                ViewCompat.animate(v)
                    .translationY(50f).alpha(1f)
                    .setStartDelay((ITEM_DELAY * i + 500).toLong())
                    .setDuration(1000)
            } else {
                ViewCompat.animate(v)
                    .scaleY(1f).scaleX(1f)
                    .setStartDelay((ITEM_DELAY * i + 500).toLong())
                    .setDuration(500)
            }
            viewAnimator.setInterpolator(DecelerateInterpolator()).start()
        }
    }

    private val isConnected: Boolean
        get() {
            var connected = false
            try {
                val cm =
                    applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val nInfo = cm.activeNetworkInfo
                connected = nInfo != null && nInfo.isAvailable && nInfo.isConnected
                return connected
            } catch (e: Exception) {
                e.message?.let { Log.e("Connectivity Exception", it) }
            }
            return connected
        }

    companion object {
        const val STARTUP_DELAY = 300
        const val ANIM_ITEM_DURATION = 1000
        const val ITEM_DELAY = 300
        fun hideSystemUI(window: Window) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        }
    }
}