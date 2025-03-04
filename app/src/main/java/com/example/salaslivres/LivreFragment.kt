package com.example.salaslivres

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.salaslivres.Adapter.AdapterBestClasses
import com.example.salaslivres.Adapter.Salas
import com.example.salaslivres.databinding.FragmentLivreBinding

class LivreFragment : Fragment() {

    // Variável para armazenar o binding
    private var _binding: FragmentLivreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflar o layout usando View Binding
        _binding = FragmentLivreBinding.inflate(inflater, container, false)
        initRecycleView(arrayListOf())
        binding.comfirmarSL.setOnClickListener{
            showSL(it)
        }
        return binding.root
    }

    private fun initRecycleView(listNames: ArrayList<String?>) {
        binding.recyclerViewSL.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewSL.setHasFixedSize(true)
        binding.recyclerViewSL.adapter = AdapterBestClasses(listNames)
    }

    private fun changeRecycleView(list: ArrayList<String?>) {
        binding.recyclerViewSL.adapter = AdapterBestClasses(list)
    }

    private fun validate(dt: String): Boolean {
        if (dt.length != 3) return false

        val dia = dt[0].toString().toInt()
        val hTurno = dt[2].toString().toInt()
        val turno = dt[1].toString()

        if (dia > 6 || dia < 2 || hTurno < 1 || hTurno > 6) return false
        if (turno == "M" || turno == "T" || turno == "N") return true

        return false
    }

    private fun showSL(view: View) {
        val dt = binding.diaTurno.text.toString().uppercase()

        if (validate(dt)) {
            val salas = Salas(mapOf())
            salas.loadJson(requireContext())
            val result = salas.salasLivres(dt[0].toString(), dt.substring(1))
            changeRecycleView(result)
            return
        }
        Toast.makeText(requireContext(), "Formato Invalido", Toast.LENGTH_SHORT).show()
    }

}

