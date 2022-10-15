package com.example.week_4_assignment.input

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.week_4_assignment.R
import com.example.week_4_assignment.databinding.FragmentInputBinding

class InputFragment : Fragment() {
    private lateinit var binding: FragmentInputBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        binding.btnSend.setOnClickListener {
            onClicked()
        }
    }

    //kullanicinin girdigi verileri gondermek icin onClick fun olusturuldu.
    private fun onClicked() {
        navController.navigate(R.id.action_inputFragment_to_resultFragment, Bundle().apply {
            var lat = "34"
            if (binding.etLat.text.toString().trim() != "") {
                lat = binding.etLat.text.toString().trim()
            }
            var lon = "42"
            if (binding.etLon.text.toString().trim() != "") {
                lon = binding.etLon.text.toString().trim()
            }
            //degerler bos ise varsayılan olarak deger gonderiliyor
            putString("lat", lat)
            putString("lon", lon)
        })
    }
}