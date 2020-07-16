package graysono.com.cp06asynctaskrecyclerview.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import graysono.com.cp06asynctaskrecyclerview.R
import graysono.com.cp06asynctaskrecyclerview.interfaces.IDataReceived
import kotlinx.android.synthetic.main.fragment_web.*


/**
 * A simple [Fragment] subclass.
 */
class RateUsDialogFragment(private val listener: IDataReceived) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            isCancelable = false

            return inflater.inflate(R.layout.fragment_web, container, false)
        }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnNoThanks.setOnClickListener{dismiss()}
        btnRateNow.isEnabled = false
        btnRateNow.isClickable = false
        ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromTouch ->
            when{
                ratingBar.rating > 0 -> {
                    btnRateNow.isEnabled = true
                    btnRateNow.isClickable = true
                }
                ratingBar.rating < 0.5 ->{
                    btnRateNow.isEnabled = false
                    btnRateNow.isClickable = false
                }
            }
        }

        btnRateNow.setOnClickListener{
            listener.onDataReceived("Thank you! Your feedback is important for us")
            dismiss()
        }
    }
}
