package com.gmail.brandonvel04.android_views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TabHeadersFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tab_headers, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewHeaders)

        // Lista mixta: Encabezados y Contenido
        val items = listOf(
            ListItem.Header("Android Views"),
            ListItem.Content("TextView"),
            ListItem.Content("Button"),
            ListItem.Header("Jetpack Compose"),
            ListItem.Content("Text"),
            ListItem.Content("MaterialButton"),
            ListItem.Header("Flutter"),
            ListItem.Content("Text"),
            ListItem.Content("ElevatedButton")
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = HeaderAdapter(items)

        return view
    }
}

// Estructura de datos para diferenciar tipos
sealed class ListItem {
    data class Header(val title: String) : ListItem()
    data class Content(val text: String) : ListItem()
}

class HeaderAdapter(private val items: List<ListItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_CONTENT = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ListItem.Header -> TYPE_HEADER
            is ListItem.Content -> TYPE_CONTENT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_HEADER) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
            HeaderViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
            ContentViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (holder is HeaderViewHolder && item is ListItem.Header) {
            holder.tvTitle.text = item.title
        } else if (holder is ContentViewHolder && item is ListItem.Content) {
            holder.tvText.text = item.text
        }
    }

    override fun getItemCount() = items.size

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvHeaderName)
    }
    class ContentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvText: TextView = view.findViewById(R.id.tvItemName)
    }
}