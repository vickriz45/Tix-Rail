package id.ac.pnm.tix_rail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.pnm.tix_rail.databinding.ItemBannerBinding

class BannerAdapter(private val bannerItems: List<BannerItem>) :
    RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    data class BannerItem(val imageResId: Int)

    inner class BannerViewHolder(private val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BannerItem) {
            binding.imageBanner.setImageResource(item.imageResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val binding = ItemBannerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(bannerItems[position])
    }

    override fun getItemCount() = bannerItems.size
}