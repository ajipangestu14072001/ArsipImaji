package arsip.imaji.id.view

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import arsip.imaji.id.R
import arsip.imaji.id.databinding.ActivityHelpBinding

class HelpActivity : AppCompatActivity() {
    lateinit var binding: ActivityHelpBinding
    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)
        binding.emailBantuan.setOnClickListener {
            val subject = "Hubungi Arsip Imaji"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "dabelempat14072001@gmail.com"))
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            startActivity(intent)
        }

        binding.waBantuan.setOnClickListener{
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.type = "text/plain"
            val phoneNumberWithCountryCode = "+6281345602416"
            val message = "Halo saya ingin Menghubungi Arsip Imaji"
            val apiWa =  "https://api.whatsapp.com/send?phone=%s&text=%s"
            if (sendIntent.resolveActivity(packageManager) != null) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(
                            String.format(
                                apiWa,
                                phoneNumberWithCountryCode,
                                message
                            )
                        )
                    )
                )
            } else {
                Toast.makeText(this, "Install WhatsApp Terlebih Dahulu", Toast.LENGTH_SHORT).show()
                val appPackageName = "com.whatsapp"
                try {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
                } catch (anfe: ActivityNotFoundException) {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
                }
            }
        }
        binding.phoneBantuan.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + "081345602416")
            startActivity(intent)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}