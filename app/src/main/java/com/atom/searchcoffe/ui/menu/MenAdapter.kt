package com.atom.searchcoffe.ui.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.atom.searchcoffe.databinding.ItemForMenuBinding
import com.atom.searchcoffe.domain.dto.CartItem
import com.bumptech.glide.Glide

class MenAdapter(private val clickListener: OnCartClickListener) :
    ListAdapter<CartItem, MenAdapter.MenViewHolder>(CartItemDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemForMenuBinding.inflate(inflater, parent, false)
        return MenViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenViewHolder, position: Int) {
        val cartItem = getItem(position)
        holder.bind(cartItem, clickListener)
    }

    interface OnCartClickListener {
        fun onCartClick(item: CartItem)
        fun onIncreaseCoffeeClick(item: CartItem)
        fun onDecreaseCoffeeClick(item: CartItem)
    }

    class CartItemDiffCallback : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem.coffee.id == newItem.coffee.id
        }

        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem == newItem
        }

    }

    inner class MenViewHolder(private val binding: ItemForMenuBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        override fun onClick(view: View?) {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val cartItem = getItem(position)
                when (view) {
                    binding.imageIncreaseCoffee -> {
                        clickListener.onIncreaseCoffeeClick(cartItem)
                    }

                    binding.imageDecreaseCoffee -> {
                        clickListener.onDecreaseCoffeeClick(cartItem)
                    }
                }
            }
        }

        init {
            binding.imageIncreaseCoffee.setOnClickListener(this)
            binding.imageDecreaseCoffee.setOnClickListener(this)
        }


        fun bind(cartItem: CartItem, clickListener: OnCartClickListener) {
            binding.apply {
                val coffee = cartItem.coffee
                val quantity = cartItem.quantity
                binding.apply {
                    coffeeName.text = coffee.name
                    coffeePrice.text = coffee.price.toInt().toString() + " ₽"
                    textViewQuantity.text = "$quantity"
                    Glide.with(coffeeImage)
                        .load(coffee.imageURL)
                        .into(coffeeImage)

                    imageIncreaseCoffee.tag = coffee
                    imageDecreaseCoffee.tag = coffee
                }


            }
        }
    }


}