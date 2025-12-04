package id.ac.pnm.tix_rail

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Inisialisasi Views
        val btnMasuk: Button = findViewById(R.id.btn_masuk)
        val btnDaftar: Button = findViewById(R.id.btn_daftar)

        // Menetapkan Listener untuk Tombol Masuk
        btnMasuk.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }

        // Menetapkan Listener untuk Tombol Daftar
        btnDaftar.setOnClickListener {
            // Membuat Intent untuk pindah ke RegisterActivity
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
            finish()
        }
    }
}