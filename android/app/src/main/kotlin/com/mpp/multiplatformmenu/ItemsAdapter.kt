package com.mpp.multiplatformmenu

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mpp.multiplatformmenu.data.Item

/**
 * Created by ihor_kucherenko on 2/17/18.
 * https://github.com/KucherenkoIhor
 */
class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    var dataSource: List<Item>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemViewHolder =
        ItemViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.list_item, parent, false))

    override fun getItemCount() = dataSource?.size ?: 0

    override fun onBindViewHolder(holder: ItemViewHolder?, position: Int) {
        holder?.item = dataSource?.get(position)
    }

    class ItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        private val nameTv = itemView?.findViewById<TextView>(R.id.tv)

        var item: Item? = null
            set(value) {
                field = item
                nameTv?.text = value?.name
            }

    }
}