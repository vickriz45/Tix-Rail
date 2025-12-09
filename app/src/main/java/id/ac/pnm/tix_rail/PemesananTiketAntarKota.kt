package id.ac.pnm.tix_rail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import id.ac.pnm.tix_rail.databinding.FragmentPemesananTiketAntarKotaBinding

class PemesananTiketAntarKota : Fragment() {

    private var _binding: FragmentPemesananTiketAntarKotaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPemesananTiketAntarKotaBinding.inflate(inflater, container, false)
        setupToolbar()
        setupUI()

        return binding.root
    }

    private fun setupToolbar() {
        binding.iconBack.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun setupUI() {
        binding.buttonCariJadwal.setOnClickListener {
            val asal = binding.textStasiunAsal.text.toString().trim()
            val tujuan = binding.textStasiunTujuan.text.toString().trim()

            if (asal.isNotEmpty() && tujuan.isNotEmpty()) {
                val jadwalFragment = JadwalKeretaFragment.newInstance(asal, tujuan)
                val mainActivity = activity as? MainActivity
                mainActivity?.navigateToFragment(jadwalFragment, "Cari Jadwal")
            } else {
                Toast.makeText(requireContext(), "Harap isi stasiun asal dan stasiun tujuan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}