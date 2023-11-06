package com.ags.proyectofinal.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ags.proyectofinal.R
import com.ags.proyectofinal.data.util.OpcionMenu
import com.ags.proyectofinal.databinding.FragmentConfiguracionBinding



class ConfiguracionFragment : Fragment() {

    private var _binding : FragmentConfiguracionBinding? = null
    private val binding get() = _binding!!

    private var menuOpciones : List<OpcionMenu> = emptyList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentConfiguracionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuOpciones = arrayListOf(
            OpcionMenu("Configuración",R.drawable.ic_settings),
            OpcionMenu("Domicilios de entrega",R.drawable.ic_delivery_dining))


       binding.rvMenu.apply {
           layoutManager = LinearLayoutManager(requireContext())
           adapter = MenuOpcionesAdapter(menuOpciones){ opcion ->
               when(opcion){
                   0 -> {

                   }
                   1 -> {

                   }
               }
           }

       }
    }
}