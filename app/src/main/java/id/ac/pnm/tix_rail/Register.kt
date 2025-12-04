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

class Register : AppCompatActivity() {
    private lateinit var etName: EditText
    private lateinit var etPhone: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnRegister: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Inisialisasi Views (Menghubungkan variabel dengan ID di XML)
        etName = findViewById(R.id.et_name)
        etPhone = findViewById(R.id.et_phone)
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        btnRegister = findViewById(R.id.btn_register)

        // Menetapkan Listener untuk Tombol Daftar
        btnRegister.setOnClickListener {
            performRegistration()
        }
    }

    /**
     * Fungsi untuk menangani proses pendaftaran dan validasi input.
     */
    private fun performRegistration() {
        // Mengambil teks dari input field
        val name = etName.text.toString().trim()
        val phone = etPhone.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        // --- Validasi Input Sederhana ---
        if (name.isEmpty() || name.length < 3) {
            etName.error = "Nama minimal 5 karakter"
            etName.requestFocus()
            return
        }

        if (phone.isEmpty()) {
            etPhone.error = "Nomor Telepon harus diisi"
            etPhone.requestFocus()
            return
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.error = "Email tidak valid"
            etEmail.requestFocus()
            return
        }

        if (password.isEmpty() || password.length < 6) {
            etPassword.error = "Password minimal 6 karakter"
            etPassword.requestFocus()
            return
        }

        // --- Simulasi Proses Pendaftaran ---
        // Di sini Anda akan menambahkan logika sebenarnya untuk:
        // 1. Mengirim data ke API pendaftaran
        // 2. Menyimpan data pengguna

        Toast.makeText(this, "Pendaftaran berhasil untuk $email!", Toast.LENGTH_LONG).show()

        // Pindah ke Login Activity setelah Pendaftaran berhasil
        val intent = Intent(this, Login::class.java)
        // Tambahkan flag agar tumpukan Activity bersih setelah pendaftaran (opsional)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        // finish() tidak diperlukan karena Intent flag sudah menangani stack
    }
}