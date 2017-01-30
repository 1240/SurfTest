package com.l24o.surftest

import ConvertToWords
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * @author Alexander Popov on 30/01/2017.
 */
class SurfTestTaskAdapter(val lines: List<String>) : RecyclerView.Adapter<SurfTestTaskAdapter.VH>() {

    //не приватное т.к. в котлине отходят от понятия гетеров сеттеров если они ничего не делаю а просто выдают либо напрямую сетят значения
    var isNumbers: Boolean = true

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(lines[position], isNumbers)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return VH(view)
    }

    override fun getItemCount(): Int {
        return lines.size
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: String, isNumbers: Boolean) {
            itemView.textView.text = if (isNumbers) item else ConvertToWords.convertTowords(item)
        }
    }

}