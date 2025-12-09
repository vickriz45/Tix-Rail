package id.ac.pnm.tix_rail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.pnm.tix_rail.databinding.FragmentJadwalAntarkotaBinding

class JadwalAntarkotaFragment : Fragment(), JadwalAdapter.OnItemClickListener {

    private var _binding: FragmentJadwalAntarkotaBinding? = null
    private val binding get() = _binding!!
    private var stasiunAsal: String? = null
    private var stasiunTujuan: String? = null
    private val availableDates = listOf(
        "Sen, 9 Des", "Sel, 10 Des", "Rab, 11 Des", "Kam, 12 Des", "Jum, 13 Des"
    )

    companion object {
        private const val ARG_ASAL = "stasiun_asal"
        private const val ARG_TUJUAN = "stasiun_tujuan"

        fun newInstance(asal: String, tujuan: String): JadwalAntarkotaFragment {
            val fragment = JadwalAntarkotaFragment()
            val args = Bundle()
            args.putString(ARG_ASAL, asal)
            args.putString(ARG_TUJUAN, tujuan)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            stasiunAsal = it.getString(ARG_ASAL)
            stasiunTujuan = it.getString(ARG_TUJUAN)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJadwalAntarkotaBinding.inflate(inflater, container, false)

        setupToolbar()
        displayRouteHeader()
        setupDateFiltersSimple()

        if (availableDates.isNotEmpty()) {
            loadJadwalForDate(availableDates.first())
        }
        return binding.root
    }

    override fun onDetailClick(item: JadwalItem) {
        Toast.makeText(requireContext(), "Membuka Detail Kereta: ${item.namaKereta}", Toast.LENGTH_SHORT).show()
    }

    private fun setupToolbar() {
        binding.iconBack.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun displayRouteHeader() {
        val asal = stasiunAsal ?: ""
        val tujuan = stasiunTujuan ?: ""
        binding.textRutePlaceholder.text = "$asal → $tujuan"
    }

    private fun setupDateFiltersSimple() {
        binding.layoutTanggalButtons.removeAllViews()

        for (date in availableDates) {
            val dateButton = createDateButtonSimple(date)

            dateButton.setOnClickListener {
                loadJadwalForDate(date)
                Toast.makeText(requireContext(), "Jadwal diperbarui untuk: $date", Toast.LENGTH_SHORT).show()
            }
            binding.layoutTanggalButtons.addView(dateButton)
        }
    }

    private fun createDateButtonSimple(date: String): TextView {
        val context = requireContext()
        val button = TextView(context)

        button.text = date
        button.textSize = 14f
        button.setPadding(30, 15, 30, 15)
        button.setTextColor(ContextCompat.getColor(context, R.color.black))
        button.background = ContextCompat.getDrawable(context, R.drawable.rounded_white)

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.marginEnd = 16
        button.layoutParams = params

        return button
    }

    private fun loadJadwalForDate(date: String) {
        val asalSingkat = stasiunAsal ?: ""
        val tujuanSingkat = stasiunTujuan ?: ""
        val finalJadwalList = if (date == "Sen, 9 Des") {
            listOf(
                JadwalItem("Gatotkaca Express",
                    "908",
                    "600000",
                    "12:00",
                    "20:00",
                    "08j 00m",
                    asalSingkat,
                    tujuanSingkat,
                    "Sen, 9 Des",
                    "Sen, 9 Des"
                ),
                JadwalItem("Gajah Wong",
                    "770",
                    "150000",
                    "18:00",
                    "23:00",
                    "05j 00m",
                    asalSingkat,
                    tujuanSingkat,
                    "Sen, 9 Des",
                    "Sen, 9 Des"
                ),
                JadwalItem("Laksana Cemara",
                    "967",
                    "310000",
                    "10:30",
                    "14:05",
                    "03j 35m",
                    asalSingkat,
                    tujuanSingkat,
                    "Sen, 9 Des",
                    "Sen, 9 Des"
                ),
            )
        }else if (date == "Sel, 10 Des") {
            listOf(
                JadwalItem("Argo Parahyangan",
                    "354",
                    "250000",
                    "08:00",
                    "11:30",
                    "03j 30m",
                    asalSingkat,
                    tujuanSingkat,
                    "Sel, 10 Des",
                    "Sel, 10 Des"
                ),
                JadwalItem("Taksaka Siang",
                    "156",
                    "450000",
                    "14:00",
                    "17:30",
                    "03j 30m",
                    asalSingkat,
                    tujuanSingkat,
                    "Sel, 10 Des",
                    "Sel, 10 Des"
                ),
            )
        } else if(date == "Rab, 11 Des") {
            listOf(
                JadwalItem("Bima Malam",
                    "665",
                    "520000",
                    "20:00",
                    "23:45",
                    "03j 45m",
                    asalSingkat,
                    tujuanSingkat,
                    "Rab, 11 Des",
                    "Rab, 11 Des"
                ),
            )
        } else if (date == "Kam, 12 Des"){
            listOf(
                JadwalItem("Bima Pagi",
                    "565",
                    "520000",
                    "08:00",
                    "13:00",
                    "03j 45m",
                    asalSingkat,
                    tujuanSingkat,
                    "Rab, 12 Des",
                    "Rab, 12 Des"
                ),
            )
        } else {
            listOf(
                JadwalItem("Kereta Senja",
                    "721",
                    "120000",
                    "16:00",
                    "18:00",
                    "02j 00m",
                    asalSingkat,
                    tujuanSingkat,
                    "Jum, 13 Des",
                    "Jum, 13 Des"
                )
            )
        }
        setupJadwalList(finalJadwalList)
    }
    private fun setupJadwalList(list: List<JadwalItem>) {
        val adapter = JadwalAdapter(list)
        adapter.setOnItemClickListener(this)
        binding.recyclerJadwal.layoutManager = LinearLayoutManager(context)
        binding.recyclerJadwal.adapter = adapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}