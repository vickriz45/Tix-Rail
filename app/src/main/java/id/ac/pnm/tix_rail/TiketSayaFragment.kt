package id.ac.pnm.tix_rail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class TiketSayaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tiket_saya, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnBack = view.findViewById<android.widget.ImageView>(R.id.btn_back)
        btnBack.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

        val trainName = "Gatotkaca Express ( 908 )"
        val bookingCode = "A1B2C3D4"
        val departureTime = "08:00"
        val departureDate = "Sen, 9 Juni 2025"
        val arrivalTime = "16:37"
        val arrivalDate = "Sen, 9 Juni 2025"

        view.findViewById<android.widget.TextView>(R.id.tv_train_name).text = trainName
        view.findViewById<android.widget.TextView>(R.id.tv_booking_code).text = "Kode pemesanan : $bookingCode"
        view.findViewById<android.widget.TextView>(R.id.tv_departure_time).text = departureTime
        view.findViewById<android.widget.TextView>(R.id.tv_departure_date).text = departureDate
        view.findViewById<android.widget.TextView>(R.id.tv_arrival_time).text = arrivalTime
        view.findViewById<android.widget.TextView>(R.id.tv_arrival_date).text = arrivalDate

        val passengerName = "Alvin Hutapea"

        view.findViewById<android.widget.TextView>(R.id.tv_passenger_name).text = passengerName
    }

    companion object {
        @JvmStatic
        fun newInstance() = TiketSayaFragment()
    }
}