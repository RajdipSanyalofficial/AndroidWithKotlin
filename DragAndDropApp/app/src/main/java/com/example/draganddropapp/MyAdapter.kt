package com.example.draganddropapp;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private var itemList: MutableList<String>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textView: TextView = view.findViewById(R.id.itemTextView)
}

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
    return ViewHolder(view)
}

override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.textView.text = itemList[position]
}

override fun getItemCount(): Int {
    return itemList.size
}

fun moveItem(fromPosition: Int, toPosition: Int) {
    val item = itemList.removeAt(fromPosition)
    itemList.add(toPosition, item)
    notifyItemMoved(fromPosition, toPosition)
}

fun removeItem(position: Int) {
    itemList.removeAt(position)
    notifyItemRemoved(position)
}
}
