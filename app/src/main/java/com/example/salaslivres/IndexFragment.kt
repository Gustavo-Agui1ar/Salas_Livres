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
import com.example.salaslivres.databinding.FragmentIndexBinding

class IndexFragment : Fragment() {

    // Variável para armazenar o binding
    private var _binding: FragmentIndexBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIndexBinding.inflate(inflater, container, false)
        initRecycleView(arrayListOf())
        binding.comfirmar.setOnClickListener{
            showIndex(it)
        }
        return binding.root
    }

    // Função para processar a entrada do usuário
    private fun showIndex(view: View) {
        val valueBloco = binding.campoBloco.text.toString().uppercase()
        val valueDT = binding.campoTurno.text.toString().uppercase()

        if (valueBloco.isEmpty() || valueDT.isEmpty() || valueDT.length != 2) {
            Toast.makeText(requireContext(), "Formato Inválido", Toast.LENGTH_SHORT).show()
            return
        }

        val salas = Salas(emptyMap())
        salas.loadJson(requireContext())

        val result = salas.index(valueBloco, valueDT[0].toString(), valueDT[1].toString())

        if (result.isEmpty()) {
            Toast.makeText(requireContext(), "Nenhuma classe encontrada para o bloco e turno especificados", Toast.LENGTH_SHORT).show()
            return
        }
        changeRecycleView(result, valueBloco, valueDT[1].toString())
    }

    private fun initRecycleView(listNames : ArrayList<ClassData>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = AdapterClassIndex(listNames)
    }

    private fun changeRecycleView(list : ArrayList<String?>, nameClass : String, turno : String) {
        val classData = ClassData(nameClass, list)
        classData.inicializarHorarios(turno)
        val listC : ArrayList<ClassData> = arrayListOf(classData)
        binding.recyclerView.adapter = AdapterClassIndex(listC)
    }


}
