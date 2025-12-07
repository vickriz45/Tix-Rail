package id.ac.pnm.tix_rail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.pnm.tix_rail.databinding.FragmentHomeBinding
// Asumsi BannerAdapter, PromoAdapter, dan AntarKotaSearchFragment sudah didefinisikan

class HomeFragment : Fragment() {

    // Variabel binding yang aman (safe)
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        configureHeader()
        configureBannerSlider()
        configureTixpay()
        configureTransportIcons()
        configurePromoSection()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun configureHeader() {
        binding.textUserName.text = "USERNAME"
    }

    private fun configureBannerSlider() {
        val banners = listOf(
            BannerAdapter.BannerItem(R.drawable.ic_banner_1),
            BannerAdapter.BannerItem(R.drawable.ic_banner_2),
            BannerAdapter.BannerItem(R.drawable.ic_banner_3)
        )
        val adapter = BannerAdapter(banners)
        binding.bannerViewPager.adapter = adapter
        binding.indicator.setViewPager(binding.bannerViewPager)
    }

    private fun configureTixpay() {
        binding.textSaldoValue.text = "Rp 15.000"

        binding.actionTopup.setOnClickListener {
            // Menggunakan requireContext() untuk Toast di Fragment
            Toast.makeText(requireContext(), "Menuju halaman Top Up", Toast.LENGTH_SHORT).show()
        }
        binding.actionHistory.setOnClickListener {
            Toast.makeText(requireContext(), "Menuju halaman Riwayat Transaksi", Toast.LENGTH_SHORT).show()
        }
    }

    private fun configureTransportIcons() {
        binding.iconAntarKota.textIconLabel.text = "Antar Kota"
        binding.iconAntarKota.imageIcon.setImageResource(R.drawable.antarKota_logo)
        binding.iconAntarKota.root.setOnClickListener {
            val mainActivity = activity as? MainActivity
            mainActivity?.navigateToFragment(AntarKotaFragment(), "AntarKota")
        }

        binding.iconLokal.textIconLabel.text = "Lokal"
        binding.iconLokal.imageIcon.setImageResource(R.drawable.lokal_logo)
        binding.iconLokal.root.setOnClickListener {
            Toast.makeText(requireContext(), "Lokal diklik", Toast.LENGTH_SHORT).show()
        }

        binding.iconKomuter.textIconLabel.text = "Komuter"
        binding.iconKomuter.imageIcon.setImageResource(R.drawable.komuter_logo)
        binding.iconKomuter.root.setOnClickListener {
            //code ke activity
        }

        binding.iconMrt.textIconLabel.text = "MRT"
        binding.iconMrt.imageIcon.setImageResource(R.drawable.mrt_logo)
        binding.iconMrt.root.setOnClickListener {
            Toast.makeText(requireContext(), "MRT diklik", Toast.LENGTH_SHORT).show()
        }

        binding.iconWhoosh.textIconLabel.text = "Whoosh"
        binding.iconWhoosh.imageIcon.setImageResource(R.drawable.whoosh_logo)
        binding.iconWhoosh.root.setOnClickListener {
            Toast.makeText(requireContext(), "Whoosh diklik", Toast.LENGTH_SHORT).show()
        }
    }

    private fun configurePromoSection() {
        val promos = listOf(
            PromoAdapter.PromoItem(
                R.drawable.ic_promo_1,
                "Diskon Spesial Akhir Tahun! Berlaku untuk semua rute favorit."),
            PromoAdapter.PromoItem(
                R.drawable.ic_promo_2,
                "Spesial Diskon 12.12: Kereta Istimewa dan Lawang Sewu !!"),
            PromoAdapter.PromoItem(
                R.drawable.ic_promo_3,
                "Waktunya Eksplorasi Dunia Kereta Api! KAI EXPO 2024 Hadir di Kota Anda !")
        )

        val promoAdapter = PromoAdapter(promos)

        binding.recyclerPromo.layoutManager =
            LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )

        binding.recyclerPromo.adapter = promoAdapter

        binding.buttonLihatSemua.setOnClickListener {
            Toast.makeText(requireContext(), "Menuju halaman semua Promo", Toast.LENGTH_SHORT).show()
        }
    }
}