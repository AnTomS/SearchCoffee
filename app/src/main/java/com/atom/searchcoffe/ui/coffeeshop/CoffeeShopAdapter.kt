package com.atom.searchcoffe.ui.coffeeshop

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.atom.searchcoffe.R
import com.atom.searchcoffe.domain.dto.LocationRespondItem

class CoffeeShopAdapter(
    private var coffeeShops: List<LocationRespondItem>,
    private val clickListener: (Int) -> Unit
) : RecyclerView.Adapter<CoffeeShopAdapter.CoffeeShopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeShopViewHolder =
        CoffeeShopViewHolder(
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_for_list_coffeshop, parent, false)
        )

    override fun getItemCount(): Int = coffeeShops.size

    override fun onBindViewHolder(holder: CoffeeShopViewHolder, position: Int) {
        holder.bind(coffeeShops[position], clickListener)
    }

    class CoffeeShopViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val coffeeShopName = view.findViewById<TextView>(R.id.coffee_shop_name)

        fun bind(location: LocationRespondItem, clickListener: (Int) -> Unit) {
            coffeeShopName?.text = location.name
            itemView.setOnClickListener { clickListener(location.id) }
        }
    }

    fun updateData(newCoffeeShops: List<LocationRespondItem>) {
        coffeeShops = newCoffeeShops
        notifyDataSetChanged()
    }
}