package com.example.salaslivres.Adapter

class ClassData(
    var nome: String? = null,
    var materias: ArrayList<String?> = arrayListOf(),
    var isVisible: Boolean = false
) {
    val inicioHorarios: ArrayList<String?> = arrayListOf()
    val terminoHorarios: ArrayList<String?> = arrayListOf()
    var qtdMaterias: Int = materias.count { it != null }
        private set // Impede alterações externas, mantém a consistência

    companion object {
        private val HORARIOS = mapOf(
            "M" to listOf(
                "7h30" to "8h20",
                "8h20" to "9h10",
                "9h30" to "10h20",
                "10h20" to "11h10",
                "11h10" to "12h00",
                "12h00" to "12h50"
            ),
            "T" to listOf(
                "13h00" to "13h50",
                "13h50" to "14h40",
                "14h40" to "15h30",
                "15h50" to "16h40",
                "16h40" to "17h30",
                "17h50" to "18h40"
            ),
            "N" to listOf(
                "18h40" to "19h30",
                "19h30" to "20h20",
                "20h20" to "21h10",
                "21h20" to "22h10",
                "22h10" to "23h00",
                "-" to "-"
            )
        )
    }

    fun inicializarHorarios(turno: String) {
        inicioHorarios.clear()
        terminoHorarios.clear()

        val horarios = HORARIOS[turno]
        if (horarios != null) {
            horarios.forEach { (inicio, termino) ->
                inicioHorarios.add(inicio)
                terminoHorarios.add(termino)
            }
        } else {
            println("Turno inválido: $turno") // Log ou tratamento de erro
        }
    }
}
