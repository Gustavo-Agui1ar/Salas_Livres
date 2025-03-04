package com.example.salaslivres.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.salaslivres.R

class AdapterBestClasses(
    private val classList : ArrayList<String?>,
) : RecyclerView.Adapter<AdapterBestClasses.MyViewHolder>() {

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val className : TextView = itemView.findViewById(R.id.textClass)
        val tableClass : TableLayout = itemView.findViewById(R.id.tableClass)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterBestClasses.MyViewHolder {//cria o layout de cada item
        val classItem = LayoutInflater.from(parent.context).inflate(R.layout.item_sala, parent, false)
        return MyViewHolder(classItem)
    }

    override fun onBindViewHolder(holder: AdapterBestClasses.MyViewHolder, position: Int) {//cria a visualização do item

        val classData = classList[position]
        holder.className.text = classData ?: "-"
        holder.tableClass.visibility = View.GONE

    }

    override fun getItemCount(): Int {// passa a quantidade de items usados
        return classList.size
    }
}