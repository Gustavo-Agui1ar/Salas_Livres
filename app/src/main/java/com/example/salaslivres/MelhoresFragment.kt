package com.example.salaslivres

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.salaslivres.Adapter.AdapterClassIndex
import com.example.salaslivres.Adapter.ClassData
import com.example.salaslivres.Adapter.Salas
import com.example.salaslivres.databinding.FragmentMelhoresBinding

class MelhoresFragment : Fragment() {

    // Variável para armazenar o binding
    private var _binding: FragmentMelhoresBinding? = null
    private val binding get() = _binding!! // Acessa o binding de forma segura

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar o layout usando View Binding
        _binding = FragmentMelhoresBinding.inflate(inflater, container, false)
        initRecycleView()
        binding.comfirmarM.setOnClickListener{
            showBest(it)
        }
        return binding.root
    }

    public fun showBest(view: View) {
        val valueBloco = binding.campoBlocoM.text.toString().uppercase()
        val valueDT = binding.campoTurnoM.text.toString().uppercase()

        if (validate(valueDT, valueBloco)) {
            val salas = Salas(emptyMap())
            salas.loadJson(requireContext())
            val result = salas.bestClasses(valueBloco, valueDT[0].toString(), valueDT[1].toString())
            changeRecycleView(result)
        }
    }


    private fun initRecycleView() {
        binding.recyclerViewM.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewM.setHasFixedSize(true)
        binding.recyclerViewM.adapter = AdapterClassIndex(arrayListOf())
    }

    private fun changeRecycleView(list: ArrayList<ClassData>) {
        binding.recyclerViewM.adapter = AdapterClassIndex(list)
    }

    private fun validate(dt: String, bloco: String): Boolean {
        if (dt.length != 2 || bloco.length != 2) {
            Toast.makeText(requireContext(), "Formato do bloco ou turno está incorreto", Toast.LENGTH_SHORT).show()
            return false
        }

        val dia = dt[0].digitToIntOrNull()
        val turno = dt[1].uppercaseChar()

        if (dia == null || dia !in 2..6) {
            Toast.makeText(requireContext(), "O dia deve estar entre 2 e 6", Toast.LENGTH_SHORT).show()
            return false
        }

        if (turno !in listOf('M', 'T', 'N')) {
            Toast.makeText(requireContext(), "O turno deve ser M, T ou N", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    fun showIndex(view: View) {}
}
