package com.atom.searchcoffe.ui.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.atom.searchcoffe.R
import com.atom.searchcoffe.databinding.ItemForMenuBinding
import com.atom.searchcoffe.domain.dto.Coffee
import com.atom.searchcoffe.domain.dto.LocationRespondItem
import com.bumptech.glide.Glide

class MenuAdapter(
    private var menuItems: List<Coffee>,
    private val onAddClickListener: (Coffee) -> Unit,
    private val onRemoveClickListener: (Coffee) -> Unit
) : RecyclerView.Adapter<MenuAdapter.MenuItemViewHolder>() {

    inner class MenuItemViewHolder(binding: ItemForMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {


        val coffeeImage: ImageView = binding.coffeeImage
        val coffeeName: TextView = binding.coffeeName
        val coffeePrice: TextView = binding.coffeePrice
        val imageDecreaseDish: ImageView = binding.imageDecreaseCoffee
        val counter: TextView = binding.textViewQuantity
        val imageIncreaseDish: ImageView = binding.imageIncreaseCoffee
        init {
            imageDecreaseDish.setOnClickListener {
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onRemoveClickListener.invoke(menuItems[position])
                }
            }

           imageIncreaseDish.setOnClickListener {
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onAddClickListener.invoke(menuItems[position])
                }
            }
        }
    }

    fun updateMenuItems(newMenuItems: List<Coffee>) {
        menuItems = newMenuItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        val binding = ItemForMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        val menuItem = menuItems[position]

        holder.coffeeName.text = menuItem.name
        holder.coffeePrice.text = "${menuItem.price}"
        Glide.with(holder.coffeeImage.context)
            .load(menuItem.imageURL)
            .into(holder.coffeeImage)

 //       holder.counter.text = "${menuItem.quantity}"


        holder.imageDecreaseDish.setOnClickListener {
            onRemoveClickListener.invoke(menuItem)
        }

        holder.imageIncreaseDish.setOnClickListener {
            onAddClickListener.invoke(menuItem)
        }
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }
}