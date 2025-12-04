package id.ac.pnm.tix_rail // Ganti dengan nama paket Anda

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.pnm.tix_rail.databinding.ItemPromoBinding

class PromoAdapter(private val promoItems: List<PromoItem>) :
    RecyclerView.Adapter<PromoAdapter.PromoViewHolder>() {

    data class PromoItem(val imageResId: Int)

    inner class PromoViewHolder(private val binding: ItemPromoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PromoItem) {
            binding.imagePromo.setImageResource(item.imageResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoViewHolder {
        val binding = ItemPromoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PromoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PromoViewHolder, position: Int) {
        holder.bind(promoItems[position])
    }

    override fun getItemCount() = promoItems.size
}