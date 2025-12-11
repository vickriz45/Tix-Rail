package id.ac.pnm.tix_rail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import id.ac.pnm.tix_rail.databinding.FragmentTopupBinding

class TopUpFragment : Fragment() {

    private var _binding: FragmentTopupBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btn50k.setOnClickListener {
            binding.etNominal.setText("50000")
        }

        binding.btn100k.setOnClickListener {
            binding.etNominal.setText("100000")
        }

        binding.btn200k.setOnClickListener {
            binding.etNominal.setText("200000")
        }

        binding.btn500k.setOnClickListener {
            binding.etNominal.setText("500000")
        }

        binding.btnLanjut.setOnClickListener {
            val nominal = binding.etNominal.text.toString().trim()
            val nomorAkun = binding.etNomorAkun.text.toString().trim()

            if (validateInput(nominal, nomorAkun)) {
                showConfirmationDialog(nominal, nomorAkun)
            }
        }

        binding.btnBatal.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun validateInput(nominal: String, nomorAkun: String): Boolean {
        if (nominal.isEmpty()) {
            showToast("Masukkan Nominal...")
            return false
        }

        try {
            val amount = nominal.toLong()
            if (amount <= 0) {
                showToast("Nominal harus lebih dari 0")
                return false
            }
        } catch (e: NumberFormatException) {
            showToast("Nominal harus berupa angka")
            return false
        }

        if (nomorAkun.isEmpty()) {
            showToast("Masukkan nomor akun")
            return false
        }

        if (!binding.rbQris.isChecked && !binding.rbTransfer.isChecked) {
            showToast("Pilih metode pembayaran")
            return false
        }

        return true
    }

    private fun showConfirmationDialog(nominal: String, nomorAkun: String) {
        val metode = if (binding.rbQris.isChecked) "Qris" else "Transfer Bank"

        val amount = try {
            nominal.toLong()
        } catch (e: NumberFormatException) {
            showToast("Nominal tidak valid")
            return
        }

        AlertDialog.Builder(requireContext())
            .setTitle("Konfirmasi Pembayaran")
            .setMessage(
                """
                Nominal: Rp ${formatRupiah(amount)}
                Nomor Akun: $nomorAkun
                Metode: $metode
                
                Lanjutkan pembayaran?
                """.trimIndent()
            )
            .setPositiveButton("Ya") { dialog, _ ->
                dialog.dismiss()
                processPayment(nominal, nomorAkun, metode)
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun processPayment(nominal: String, nomorAkun: String, metode: String) {
        showToast("Mengarahkan ke pembayaran $metode...")

        view?.postDelayed({
            showToast("Pembayaran berhasil! Saldo bertambah Rp ${formatRupiah(nominal.toLong())}")
            requireActivity().supportFragmentManager.popBackStack()
        }, 2000)
    }

    private fun formatRupiah(amount: Long): String {
        return String.format("%,d", amount).replace(",", ".")
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}