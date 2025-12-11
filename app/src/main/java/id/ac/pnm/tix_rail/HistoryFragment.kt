package id.ac.pnm.tix_rail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewHistory)
        val btnBack = view.findViewById<View>(R.id.btnBack)
        val btnFilter = view.findViewById<View>(R.id.btnFilter)
        val tvTotal = view.findViewById<TextView>(R.id.tvTotalTransaksi)

        // Setup RecyclerView dengan data
        val historyList = listOf(
            HistoryItem("TRX001", "15 Mar 2024 14:30", "Top Up", 500000, "Berhasil", "QRIS"),
            HistoryItem("TRX002", "14 Mar 2024 10:15", "Pembelian Tiket", -250000, "Berhasil", "Saldo TixPay"),
            HistoryItem("TRX003", "13 Mar 2024 16:45", "Top Up", 1000000, "Berhasil", "Transfer Bank"),
            HistoryItem("TRX004", "12 Mar 2024 09:20", "Pembelian Tiket", -150000, "Gagal", "Saldo TixPay"),
            HistoryItem("TRX005", "11 Mar 2024 11:30", "Top Up", 200000, "Berhasil", "QRIS"),
            HistoryItem("TRX006", "10 Mar 2024 13:45", "Pembelian Tiket", -350000, "Berhasil", "Saldo TixPay"),
            HistoryItem("TRX007", "09 Mar 2024 15:10", "Top Up", 750000, "Pending", "Transfer Bank"),
            HistoryItem("TRX008", "08 Mar 2024 17:25", "Pembelian Tiket", -180000, "Berhasil", "Saldo TixPay")
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = SimpleHistoryAdapter(historyList)

        tvTotal.text = "Total ${historyList.size} Transaksi"

        // Click listeners
        btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        btnFilter.setOnClickListener {
            showFilterDialog()
        }

        return view
    }

    private fun showFilterDialog() {
        val filterOptions = arrayOf("Semua", "Top Up", "Pembelian Tiket", "Berhasil", "Gagal", "Pending")

        AlertDialog.Builder(requireContext())
            .setTitle("Filter Riwayat")
            .setItems(filterOptions) { dialog, which ->
                dialog.dismiss()
                val filter = filterOptions[which]
                Toast.makeText(requireContext(), "Filter: $filter diterapkan", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Batal", null)
            .show()
    }
}

data class HistoryItem(
    val id: String,
    val date: String,
    val type: String,
    val amount: Long,
    val status: String,
    val method: String
)

class SimpleHistoryAdapter(private val items: List<HistoryItem>) :
    RecyclerView.Adapter<SimpleHistoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvId: TextView = view.findViewById(R.id.tvTransactionId)
        val tvDate: TextView = view.findViewById(R.id.tvDate)
        val tvType: TextView = view.findViewById(R.id.tvType)
        val tvAmount: TextView = view.findViewById(R.id.tvAmount)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
        val tvMethod: TextView = view.findViewById(R.id.tvMethod)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.tvId.text = "ID: ${item.id}"
        holder.tvDate.text = item.date
        holder.tvType.text = item.type
        holder.tvMethod.text = "Metode: ${item.method}"

        // Format jumlah dengan warna berbeda
        if (item.amount > 0) {
            holder.tvAmount.text = "+Rp ${formatRupiah(item.amount)}"
            holder.tvAmount.setTextColor(0xFF4CAF50.toInt()) // Hijau untuk masuk
        } else {
            holder.tvAmount.text = "-Rp ${formatRupiah(-item.amount)}"
            holder.tvAmount.setTextColor(0xFFF44336.toInt()) // Merah untuk keluar
        }

        holder.tvStatus.text = item.status

        // Warna status
        when (item.status) {
            "Berhasil" -> holder.tvStatus.setTextColor(0xFF4CAF50.toInt())
            "Gagal" -> holder.tvStatus.setTextColor(0xFFF44336.toInt())
            "Pending" -> holder.tvStatus.setTextColor(0xFFFF9800.toInt())
        }

        // Click untuk detail
        holder.itemView.setOnClickListener {
            showDetailDialog(holder.itemView.context, item)
        }
    }

    override fun getItemCount(): Int = items.size

    private fun formatRupiah(amount: Long): String {
        return String.format("%,d", amount).replace(",", ".")
    }

    private fun showDetailDialog(context: android.content.Context, item: HistoryItem) {
        AlertDialog.Builder(context)
            .setTitle("Detail Transaksi")
            .setMessage(
                """
                ID: ${item.id}
                Tanggal: ${item.date}
                Jenis: ${item.type}
                Jumlah: ${if (item.amount > 0) "+" else ""}Rp ${formatRupiah(kotlin.math.abs(item.amount))}
                Status: ${item.status}
                Metode: ${item.method}
                """.trimIndent()
            )
            .setPositiveButton("OK", null)
            .show()
    }
}