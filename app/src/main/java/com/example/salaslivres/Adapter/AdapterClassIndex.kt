package com.example.salaslivres.Adapter

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.salaslivres.R

class AdapterClassIndex(
    private val classList : ArrayList<ClassData>,
    ) : RecyclerView.Adapter<AdapterClassIndex.MyViewHolder>() {

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val className : TextView = itemView.findViewById(R.id.textClass)
        val tableClass : TableLayout = itemView.findViewById(R.id.tableClass)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterClassIndex.MyViewHolder {//cria o layout de cada item
        val classItem = LayoutInflater.from(parent.context).inflate(R.layout.item_sala, parent, false)
        return MyViewHolder(classItem)
    }

    override fun onBindViewHolder(holder: AdapterClassIndex.MyViewHolder, position: Int) {//cria a visualização do item
        if (position < classList.size) {
            val classData = classList[position]
            holder.className.text = classData.nome
            holder.tableClass.visibility = if (classData.isVisible) View.VISIBLE else View.GONE

            holder.itemView.setOnClickListener {
                classData.isVisible = !classData.isVisible
                notifyItemChanged(position)
            }

            if (holder.tableClass.childCount == 0) {
                val textViewParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
                for (i in 0..6) {
                    val inicioText = if (i == 0) "Inicio" else classData.inicioHorarios[i - 1]
                    val terminoText = if (i == 0) "Termino" else classData.terminoHorarios[i - 1]
                    val materiaText = if (i == 0) "Disciplina" else classData.materias[i - 1] ?: "Livre"

                    val inicioTextView = createTextView(holder.itemView.context, inicioText, textViewParams)
                    val terminoTextView = createTextView(holder.itemView.context, terminoText, textViewParams)
                    val materiaTextView = createTextView(holder.itemView.context, materiaText, textViewParams)

                    val tableRow = TableRow(holder.itemView.context).apply {
                        addView(inicioTextView)
                        addView(terminoTextView)
                        addView(materiaTextView)
                    }

                    holder.tableClass.addView(tableRow)
                }
            }
        } else {
            Log.e("AdapterClassIndex", "Índice fora dos limites: $position")
        }

    }

    private fun createTextView(context: Context, text: String?, layoutParams: TableRow.LayoutParams): TextView {
        return TextView(context).apply {
            this.text = text
            setPadding(16, 16, 16, 16)
            textAlignment = View.TEXT_ALIGNMENT_CENTER
            setBackgroundResource(R.drawable.cell_border)
            this.layoutParams = layoutParams
            maxLines = 1
            ellipsize = TextUtils.TruncateAt.END
        }
    }

    override fun getItemCount(): Int {// passa a quantidade de items usados
        return classList.size
    }
}