package com.gmail.brandonvel04.android_views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class TabListFragment : Fragment() {
    // Genera 15 elementos iniciales
    private val items = MutableList(15) { "Elemento de lista ${it + 1}" }
    private lateinit var adapter: ListAdapter
    private lateinit var emptyStateLayout: View
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tab_list, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        emptyStateLayout = view.findViewById(R.id.emptyStateLayout)
        val swipeRefresh = view.findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)

        // Configura el adaptador y el clic de detalle
        adapter = ListAdapter(items) { selectedItem ->
            Toast.makeText(requireContext(), "Abriendo detalle de: $selectedItem", Toast.LENGTH_SHORT).show()
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // Funcionalidad: Deslizar para eliminar
        val swipeHandler = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(rv: RecyclerView, vh: RecyclerView.ViewHolder, t: RecyclerView.ViewHolder) = false
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                items.removeAt(position)
                adapter.notifyItemRemoved(position)
                checkEmptyState()
            }
        }
        ItemTouchHelper(swipeHandler).attachToRecyclerView(recyclerView)

        // Funcionalidad: Arrastrar hacia abajo para recargar
        swipeRefresh.setOnRefreshListener {
            items.clear()
            items.addAll(MutableList(15) { "Elemento Nuevo ${it + 1}" })
            adapter.notifyDataSetChanged()
            checkEmptyState()
            swipeRefresh.isRefreshing = false
        }

        checkEmptyState()
        return view
    }

    private fun checkEmptyState() {
        if (items.isEmpty()) {
            recyclerView.visibility = View.GONE
            emptyStateLayout.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            emptyStateLayout.visibility = View.GONE
        }
    }
}

// Adaptador de la lista
class ListAdapter(
    private val items: List<String>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvItemName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvName.text = item
        holder.itemView.setOnClickListener { onClick(item) }
    }

    override fun getItemCount() = items.size
}