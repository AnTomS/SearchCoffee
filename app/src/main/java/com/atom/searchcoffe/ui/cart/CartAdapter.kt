package com.atom.searchcoffe.ui.cart

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.atom.searchcoffe.databinding.ItemForCartBinding
import com.atom.searchcoffe.domain.dto.CartItem

class CartAdapter(private val clickListener: OnCartClickListener) :
    ListAdapter<CartItem, CartAdapter.CartViewHolder>(CartItemDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemForCartBinding.inflate(inflater, parent, false)
        Log.e("CartAdapter", "onCreateViewHolder")
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.CartViewHolder, position: Int) {
        val cartItem = getItem(position)
        holder.bind(cartItem, clickListener)
        Log.e("CartAdapter", "onBindViewHolder: $cartItem")
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

    inner class CartViewHolder(private val binding: ItemForCartBinding) :
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

                    imageIncreaseCoffee.tag = coffee
                    imageDecreaseCoffee.tag = coffee
                }


            }
        }
    }


}