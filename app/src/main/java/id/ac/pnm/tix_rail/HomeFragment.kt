package id.ac.pnm.tix_rail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import id.ac.pnm.tix_rail.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var username: String? = null

    companion object {
        private const val ARG_USERNAME = "EXTRA_USERNAME"
        fun newInstance(username: String): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            args.putString(ARG_USERNAME, username)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(ARG_USERNAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            configureHeader()
            configureBannerSlider()
            configureTixpay()
            configureTransportIcons()
            configurePromoSection()
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun configureHeader() {
        try {
            binding.textUserName.text = username ?: "(NAMA PENGGUNA)"
            binding.textGreeting.text = "Selamat Datang"
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun configureBannerSlider() {
        try {
            val banners = listOf(
                BannerAdapter.BannerItem(R.drawable.ic_banner_1),
                BannerAdapter.BannerItem(R.drawable.ic_banner_2),
                BannerAdapter.BannerItem(R.drawable.ic_banner_3)
            )
            val adapter = BannerAdapter(banners)
            binding.bannerViewPager.adapter = adapter
            binding.indicator.setViewPager(binding.bannerViewPager)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun configureTixpay() {
        try {
            binding.actionTopup.setOnClickListener {
                try {
                    findNavController().navigate(R.id.topUpFragment)
                } catch (e: Exception) {
                    try {
                        findNavController().navigate(R.id.action_homeFragment_to_topUpFragment)
                    } catch (e2: Exception) {
                        Toast.makeText(requireContext(), "Gagal membuka halaman top up", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            binding.actionHistory.setOnClickListener {
                Toast.makeText(requireContext(), "Menuju halaman Riwayat Transaksi", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun configureTransportIcons() {
        try {
            binding.iconKereta.root.setOnClickListener {
                Toast.makeText(requireContext(), "Navigasi ke Jadwal Kereta", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun configurePromoSection() {
        try {
            binding.buttonLihatSemua.setOnClickListener {
                Toast.makeText(requireContext(), "Lihat Semua Promo", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}