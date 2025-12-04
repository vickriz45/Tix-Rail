package id.ac.pnm.tix_rail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.ac.pnm.tix_rail.databinding.FragmentSearchAntarkotaBinding
class AntarKotaFragment : Fragment() {
    private var _binding: FragmentSearchAntarkotaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchAntarkotaBinding.inflate(inflater, container, false)
        // Code
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}