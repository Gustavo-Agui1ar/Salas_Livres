package com.example.salaslivres.Adapter

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader
import java.util.PriorityQueue

class Salas(
    private var sMap : Map<String,Map<String,Map<String,ArrayList<String?>>>>,
) {
    fun loadJson(context : Context) {

        val gson = Gson()
        val contextAsset = context.assets
        val inputStream = contextAsset.open("salas.json")
        val json = InputStreamReader(inputStream)

        val type = object : TypeToken<Map<String, Map<String, Map<String, ArrayList<String?>>>>>() {}.type

        try {
            val data: Map<String, Map<String, Map<String, ArrayList<String?>>>> = gson.fromJson(json, type)
            sMap = data
        } catch (e: Exception) {
            e.printStackTrace() // Isso pode ajudar a ver o erro exato.
        }
    }

    fun index(salaBloco : String, dia : String, turno : String) : ArrayList<String?> {//Retorna as materias da sala escolhida
        return sMap[salaBloco]?.get(dia)?.get(turno) ?: arrayListOf()
    }

    fun salasLivres(dia : String, turno :String) : ArrayList<String?> {//uma lista de salas livres
        var list = ArrayList<String?>()
        sMap.forEach{(sala, diaf) ->
            val listStr = diaf[dia]?.get(turno[0].toString())
            val index = turno[1].toString().toInt()
            if (listStr != null && index < listStr.size) {
                if (listStr[index - 1] == null && !sala.contains("*") && sala.contains("E")) {
                    list.add(sala)
                }
            }
        }
        return list
    }

    fun bestClasses(bloco : String, dia : String, turno : String) : ArrayList<ClassData> {
        val minHeap = PriorityQueue<ClassData> { a, b -> a.qtdMaterias - b.qtdMaterias }
        sMap.forEach{(sala, diaf) ->
            if (!sala.contains("*") && sala.startsWith(bloco)) {
                val listStr = diaf[dia]?.get(turno[0].toString())
                if (listStr != null) {
                    val classData = ClassData(sala, listStr)
                    minHeap.add(classData)
                }
            }
        }
        val arrayL : ArrayList<ClassData> = arrayListOf()

        for(i in 1..5) {
            val item = minHeap.remove()
            item.inicializarHorarios(turno[0].toString())
            arrayL.add(item)
        }
        return arrayL
    }
}


