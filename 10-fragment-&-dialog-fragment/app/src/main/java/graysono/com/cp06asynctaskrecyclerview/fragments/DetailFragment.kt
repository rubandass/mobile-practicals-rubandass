package graysono.com.cp06asynctaskrecyclerview.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import graysono.com.cp06asynctaskrecyclerview.R
import graysono.com.cp06asynctaskrecyclerview.helpers.Album
import kotlinx.android.synthetic.main.web_view.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.web_view, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //get the Fragments 'arguments' into another variable
        val argument = arguments
        wbv.settings.javaScriptEnabled = true
        //get the url from the arguments, if no url then use default url as 'google.com'
        wbv.loadUrl(argument?.getString("url","www.google.com"))
    }
}
