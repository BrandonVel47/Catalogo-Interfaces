package com.gmail.brandonvel04.android_views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TabGridFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tab_grid, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewGrid)

        // Configura la lista en 3 columnas
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.adapter = GridAdapter(List(30) { "Caja ${it + 1}" })

        return view
    }
}

class GridAdapter(private val items: List<String>) : RecyclerView.Adapter<GridAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvGridItem)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_grid, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = items[position]
    }
    override fun getItemCount() = items.size
}