package arsip.imaji.id.view

import android.Manifest
import android.app.ProgressDialog
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
//noinspection SuspiciousImport
import android.R
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import android.widget.Toast
import arsip.imaji.id.databinding.ActivityAddBinding
import arsip.imaji.id.model.DataObject
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*

class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding
    private var imageUri: Uri? = null
    private var storageReference: StorageReference? = null
    private var databaseReference: DatabaseReference? = null
    private var storagePath = "Poto"
    private var progressDialog: ProgressDialog? = null
    private val kategori = arrayOf("Enangement", "Graduation", "Prewed")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path)
        storageReference = FirebaseStorage.getInstance().reference
        progressDialog = ProgressDialog(this)
        setSupportActionBar(binding.toolbar)
        val kategori =
            ArrayAdapter(this@AddActivity, R.layout.simple_list_item_1, kategori)
        binding.kategori.adapter = kategori

        binding.submit.setOnClickListener { uploadImageFileToFirebaseStorage() }
        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED || checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            == PackageManager.PERMISSION_DENIED
        ) {
            val permission =
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            requestPermissions(permission, 111)
        }

        binding.action2.setOnClickListener {
            val galleryIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE)
        }
        binding.action3.setOnClickListener { openCamera() }
    }
    private fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
        imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
    }
    private fun getFileExtension(uri: Uri?): String? {
        val c = contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(c.getType(uri!!))
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_CAPTURE_CODE && resultCode == RESULT_OK) {
            Glide.with(this).load(imageUri).into(binding.imgview)
        }
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.data
            Glide.with(this).load(imageUri).into(binding.imgview)
        }
    }
    private fun uploadImageFileToFirebaseStorage() {
        if (imageUri == null) {
            Toast.makeText(this, "Poto Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
            return
        }
        progressDialog!!.setTitle(" Data is Uploading...")
        progressDialog!!.setMessage(" Transfer Data Ke Server")
        progressDialog!!.isIndeterminate = false
        progressDialog!!.show()
        val storageReference2 = storageReference
        storageReference2!!.child(
            storagePath + System.currentTimeMillis() + "." + getFileExtension(
                imageUri
            )
        ).putFile(
            imageUri!!
        ).addOnSuccessListener { taskSnapshot ->
            val key = databaseReference!!.push().key
            progressDialog!!.dismiss()
            val downloadUrl = taskSnapshot.storage.downloadUrl
            do {
                println("Error")
            } while (!downloadUrl.isSuccessful)
            val imageUploadInfo = DataObject(
                binding.kategori.selectedItem.toString()+key,
                binding.desc.text.toString(),
                binding.harga.text.toString().toInt(),
                binding.kategori.selectedItem.toString(),
                binding.lokasi.text.toString(),
                binding.namaBarang.text.toString(),
                Objects.requireNonNull(downloadUrl.result.toString()),
                binding.stock.text.toString().toInt()
            )
            val databaseReference = databaseReference
            databaseReference!!.child("List$key").setValue(imageUploadInfo)
            Toast.makeText(applicationContext, "Data Berhasil Di Upload", Toast.LENGTH_SHORT).show()
            finish();
            startActivity(intent);
        }.addOnFailureListener { exc ->
            progressDialog!!.dismiss()
            Toast.makeText(applicationContext, exc.message, Toast.LENGTH_SHORT).show()
        }
            .addOnProgressListener { progressDialog!!.setTitle("Data is Uploading...") }
    }
    companion object {
        const val Database_Path = "ArsipImaji"
        private const val RESULT_LOAD_IMAGE = 123
        const val IMAGE_CAPTURE_CODE = 654
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}