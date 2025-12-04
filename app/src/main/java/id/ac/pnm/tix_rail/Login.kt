package id.ac.pnm.tix_rail

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {

    // Deklarasikan variabel untuk View
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inisialisasi Views (Menghubungkan kode dengan ID di XML)
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_login)

        // Menetapkan Listener untuk Tombol Masuk
        btnLogin.setOnClickListener {
            performLogin()
        }
    }

    /**
     * Fungsi untuk menangani proses validasi dan simulasi login.
     */
    private fun performLogin() {
        // Mengambil teks dari input field
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        // --- Validasi Input Sederhana ---
        if (email.isEmpty()) {
            etEmail.error = "Email harus diisi"
            etEmail.requestFocus()
            return
        }

        if (password.isEmpty()) {
            etPassword.error = "Password harus diisi"
            etPassword.requestFocus()
            return
        }

        // Asumsi: Jika lolos validasi input, lanjutkan ke proses API atau simulasi.

        // --- Simulasi Proses Login Sukses ---
        // Ganti bagian ini dengan panggilan API/otentikasi yang sebenarnya.
        Toast.makeText(this, "Login berhasil! Memproses data...", Toast.LENGTH_SHORT).show()

        // Pindah ke Home Activity setelah Login
        val intent = Intent(this, Login::class.java) // Ganti HomeActivity dengan Activity tujuan Anda
        startActivity(intent)
        finish() // Tutup LoginActivity agar pengguna tidak bisa kembali

        // Jika login gagal (misalnya karena API error), Anda bisa menggunakan:
        // Toast.makeText(this, "Kredensial tidak valid.", Toast.LENGTH_LONG).show()
    }
}