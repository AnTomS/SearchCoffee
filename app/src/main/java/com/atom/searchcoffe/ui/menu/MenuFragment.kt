package com.atom.searchcoffe.ui.menu

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.atom.searchcoffe.App
import com.atom.searchcoffe.databinding.FragmentMenuBinding
import com.atom.searchcoffe.domain.dto.CartItem
import com.atom.searchcoffe.domain.dto.Coffee
import javax.inject.Inject

class MenuFragment : Fragment(), MenAdapter.OnCartClickListener {


    private var _binding: FragmentMenuBinding? = null

    private val binding get() = _binding!!


    @Inject
    lateinit var menuViewModel: MenuViewModel

    private lateinit var menuAdapter: MenAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val appComponent = (requireActivity().application as App).appComponent
        appComponent.inject(this)

        val id = arguments?.getInt("id")
        _binding = FragmentMenuBinding.inflate(inflater, container, false)


        menuAdapter = MenAdapter(this)

        binding.recyclerView1.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = menuAdapter
        }

        binding.goToPayment.setOnClickListener {
            goToCart()
        }


        observeViewModel()

        menuViewModel.getMenu(id!!)
        Log.e("MenuFragment", id.toString())
        return binding.root
    }

    private fun observeViewModel() {
        menuViewModel.cartItems.observe(viewLifecycleOwner) { cartItems ->
            Log.e("MenuFragment", "cartItems observed: $cartItems")
            menuAdapter.submitList(cartItems) {
                menuAdapter.notifyDataSetChanged()
                Log.e("MenuFragment", "menuAdapter updated")
//                menuViewModel.updateSelectedItemsForPayment()
            }
        }

        menuViewModel.updatedCartItem.observe(viewLifecycleOwner) { updatedCartItem ->
            val position =
                menuAdapter.currentList.indexOfFirst { it.coffee.id == updatedCartItem.first.id }
            if (position != -1) {
                menuAdapter.notifyItemChanged(position)
                Log.e("MenuFragment", "Item at position $position updated")
 //               menuViewModel.updateSelectedItemsForPayment()
            }
        }
    }

    private fun goToCart() {
//        val selectedItems = menuViewModel.selectedItemsForPayment.value.orEmpty()
//        menuViewModel.setSelectedItemsForPayment(selectedItems)
        val order = menuViewModel.getOrderListJson()
        val action = MenuFragmentDirections.actionMenuFragmentToCartFragment(order)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCartClick(item: CartItem) {
        TODO("Not yet implemented")
    }

    override fun onIncreaseCoffeeClick(item: CartItem) {
        menuViewModel.increaseCoffeeQuantity(item.coffee)
    }

    override fun onDecreaseCoffeeClick(item: CartItem) {
        menuViewModel.decreaseCoffeeQuantity(item.coffee)
    }
}