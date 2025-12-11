package id.ac.pnm.tix_rail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.pnm.tix_rail.databinding.FragmentPromoBinding

class PromoFragment : Fragment() {

    private var _binding: FragmentPromoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPromoBinding.inflate(inflater, container, false)

        configurePromoSection()

        return binding.root
    }

    private fun configurePromoSection() {

        val promos = listOf(
            AllPromoAdapter.PromoItem(
                R.drawable.ic_promo_1,
                "Diskon Spesial Akhir Tahun! Berlaku untuk semua rute favorit."
            ),
            AllPromoAdapter.PromoItem(
                R.drawable.ic_promo_2,
                "Spesial Diskon 12.12: Kereta Istimewa dan Lawang Sewu !!"
            ),
            AllPromoAdapter.PromoItem(
                R.drawable.ic_promo_3,
                "Waktunya Eksplorasi Dunia Kereta Api! KAI EXPO 2024 Hadir di Kota Anda !"
            ),
            AllPromoAdapter.PromoItem(
                R.drawable.ic_promo_4,
                "SIAP-SIAP MUDIK 2024! Dapatkan Informasi Penjualan Tiket Kereta Api Lebaran 2024 resmi dari KAI. Cek tanggal penting dan cara pemesanan selengkapnya di sini!"
            ),
            AllPromoAdapter.PromoItem(
                R.drawable.ic_promo_5,
                "Pake BRImo Travel, Beli Tiket Kereta Api Dapat CASHBACK Rp 100 Ribu! Periode promo: 11 Oktober - 5 November 2023. Hadiahnya bikin Happy!"
            ),
            AllPromoAdapter.PromoItem(
                R.drawable.ic_promo_6,
                "BANDUNG GREAT SALE! ⚡ Diskon Tiket Kereta 20%! Jangan sampai kehabisan, rencanakan perjalanan Anda sekarang!"
            )
        )

        val promoAdapter = AllPromoAdapter(promos)

        binding.recyclerAllPromo.layoutManager =
            LinearLayoutManager(requireContext())

        binding.recyclerAllPromo.adapter = promoAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
