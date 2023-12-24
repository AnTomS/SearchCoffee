package com.atom.searchcoffe.ui.coffeeshop

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.atom.searchcoffe.R
import com.atom.searchcoffe.databinding.ItemForListCoffeshopBinding
import com.atom.searchcoffe.domain.dto.LocationRespondItem

class CoffeeShopAdapter(
    private val coffeeShops:List<LocationRespondItem>
):RecyclerView.Adapter<CoffeeShopAdapter.CoffeeShopViewHolder>() {

    private var clickListener: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeShopViewHolder =
        CoffeeShopViewHolder(
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_coffee_shop,parent,false)
        )

    override fun getItemCount(): Int = coffeeShops.size

    override fun onBindViewHolder(holder: CoffeeShopViewHolder, position: Int) {
        holder.bind(
            name = coffeeShops[position].name,
            cardClick = { clickListener(coffeeShops[position].id) }
        )
    }

    fun setClickListener(clickListener:(Int)->Unit){
        this.clickListener = clickListener
    }

    class CoffeeShopViewHolder(view: View):RecyclerView.ViewHolder(view) {

        private val coffeeShopName = view.findViewById<TextView>(R.id.coffee_shop_name)
        private val card = view.findViewById<CardView>(R.id.coffee_shop_card)

        fun bind(
            name:String,
            cardClick:() -> Unit
        ){
            coffeeShopName.text = name
            card.setOnClickListener {
                cardClick()
            }
        }

    }
}