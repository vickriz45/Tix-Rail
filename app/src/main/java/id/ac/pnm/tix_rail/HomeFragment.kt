package id.ac.pnm.tix_rail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.pnm.tix_rail.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var username: String? = null

    companion object {
        private const val  ARG_USERNAME = "EXTRA_USERNAME"
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
    )
            : View {
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
        binding.textUserName.text = username ?: "(NAMA PENGGUNA)"
        binding.textGreeting.text = "Selamat Datang"
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
            Toast.makeText(requireContext(), "Menuju halaman Top Up", Toast.LENGTH_SHORT).show()
        }
        binding.actionHistory.setOnClickListener {
            Toast.makeText(requireContext(), "Menuju halaman Riwayat Transaksi", Toast.LENGTH_SHORT).show()
        }
    }

    private fun configureTransportIcons() {
        binding.iconAntarKota.textIconLabel.text = "Antar Kota"
        binding.iconAntarKota.imageIcon.setImageResource(R.drawable.antarkota_logo)
        binding.iconAntarKota.root.setOnClickListener {
            val mainActivity = activity as? MainActivity
            mainActivity?.navigateToFragment(PemesananTiketAntarKotaFragment(), "AntarKota")
        }

        binding.iconLokal.textIconLabel.text = "Lokal"
        binding.iconLokal.imageIcon.setImageResource(R.drawable.lokal_logo)
        binding.iconLokal.root.setOnClickListener {
            val mainActivity = activity as? MainActivity
            mainActivity?.navigateToFragment(PemesananTiketLokalFragment(), "Lokal")
        }

        binding.iconKomuter.textIconLabel.text = "Komuter"
        binding.iconKomuter.imageIcon.setImageResource(R.drawable.komuter_logo)
        binding.iconKomuter.root.setOnClickListener {
            val mainActivity = activity as? MainActivity
            mainActivity?.navigateToFragment(PemesananTiketKomuterFragment(), "Lokal")
        }

        binding.iconMrt.textIconLabel.text = "MRT"
        binding.iconMrt.imageIcon.setImageResource(R.drawable.mrt_logo)
        binding.iconMrt.root.setOnClickListener {
            val mainActivity = activity as? MainActivity
            mainActivity?.navigateToFragment(PemesananTiketMRTFragment(), "Lokal")
        }

        binding.iconWhoosh.textIconLabel.text = "Whoosh"
        binding.iconWhoosh.imageIcon.setImageResource(R.drawable.whoosh_logo)
        binding.iconWhoosh.root.setOnClickListener {
            val mainActivity = activity as? MainActivity
            mainActivity?.navigateToFragment(PemesananTiketWhooshFragment(), "Komuter")
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