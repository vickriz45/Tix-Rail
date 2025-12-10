package id.ac.pnm.tix_rail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.pnm.tix_rail.databinding.ItemJadwalResultBinding

class JadwalAdapter(private val jadwalList: List<JadwalItem>) :
    RecyclerView.Adapter<JadwalAdapter.JadwalViewHolder>() {
    interface OnItemClickListener {
        fun onDetailClick(item: JadwalItem)
    }
    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    class JadwalViewHolder(private val binding: ItemJadwalResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: JadwalItem, listener: OnItemClickListener?) {
            binding.textKeretaNama.text = item.namaKereta
            binding.textKodeKereta.text = item.kodePemesanan

            binding.textHargaMulai.text = "Harga Mulai Dari :"
            binding.textHarga.text = "Rp ${item.hargaMulai}"

            binding.textStasiunAsal.text = item.stasiunAsal
            binding.textStasiunTujuan.text = item.stasiunTujuan

            binding.textWaktuBerangkat.text = item.waktuBerangkat
            binding.textTanggalBerangkat.text = item.tanggalBerangkat
            binding.textWaktuTiba.text = item.waktuTiba
            binding.textTanggalTiba.text = item.tanggalTiba
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JadwalViewHolder {
        val binding = ItemJadwalResultBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return JadwalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JadwalViewHolder, position: Int) {
        holder.bind(jadwalList[position], listener)
    }

    override fun getItemCount(): Int = jadwalList.size
}