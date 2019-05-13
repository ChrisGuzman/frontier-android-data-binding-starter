package com.example.databinding.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.databinding.R
import com.example.databinding.data.model.MenuItem
import kotlinx.android.synthetic.main.view_menu_item.view.*

class MenuItemAdapter :
    RecyclerView.Adapter<MenuItemAdapter.ViewHolder>() {
    var menuItems = emptyList<MenuItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_menu_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = menuItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindMenuItem(menuItems[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindMenuItem(menuItem: MenuItem) {
            val context = itemView.context
            itemView.menuItemName.text = context.getString(
                R.string.menu_item_name_format,
                menuItem.quantity,
                menuItem.name
            )
            itemView.menuItemCost.text = context.getString(
                R.string.currency_usd_format,
                menuItem.cost / 100f
            )
        }
    }
}
