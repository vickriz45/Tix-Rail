package id.ac.pnm.tix_rail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
// Ganti nama Binding sesuai nama XML: FragmentTiketSayaBinding, FragmentPromoBinding, dll.
import id.ac.pnm.tix_rail.databinding.FragmentTiketSayaBinding

class TiketSayaFragment : Fragment() {
    private var _binding: FragmentTiketSayaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTiketSayaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}