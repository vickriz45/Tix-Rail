package id.ac.pnm.tix_rail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import id.ac.pnm.tix_rail.databinding.FragmentPemesananBinding

class PemesananFragment : Fragment() {

    private lateinit var binding: FragmentPemesananBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPemesananBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val namaKereta = arguments?.getString("namaKereta")
        val kode = arguments?.getString("kode")
        val harga = arguments?.getString("harga")

        binding.textNamaKereta.text = namaKereta
        binding.textKodeKereta.text = kode
        binding.textHarga.text = "Rp $harga"

        binding.btnPesan.setOnClickListener {
            val namaUser = binding.inputNama.text.toString()
            val teleponUser = binding.inputTelepon.text.toString()

            if (namaUser.isEmpty() || teleponUser.isEmpty()) {
                Toast.makeText(requireContext(), "Isi semua data!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(requireContext(), "Pemesanan Berhasil!", Toast.LENGTH_SHORT).show()

            // ❗ KEMBALI KE HOME
            parentFragmentManager.popBackStack() // tutup fragment pemesanan
            parentFragmentManager.popBackStack() // kembali ke home
        }
    }
}
