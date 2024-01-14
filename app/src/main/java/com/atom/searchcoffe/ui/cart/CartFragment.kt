package com.atom.searchcoffe.ui.cart

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.atom.searchcoffe.App
import com.atom.searchcoffe.databinding.FragmentCartBinding
import com.atom.searchcoffe.domain.dto.CartItem
import com.atom.searchcoffe.ui.menu.MenuViewModel
import javax.inject.Inject


class CartFragment : Fragment(), CartAdapter.OnCartClickListener {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var menuViewModel: MenuViewModel

    private lateinit var cartAdapter: CartAdapter


    private val args:CartFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
        Log.e("CartFragment", "MenuViewModel attached: $menuViewModel")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val appComponent = (requireActivity().application as App).appComponent
        appComponent.inject(this)

        _binding = FragmentCartBinding.inflate(inflater, container, false)

        cartAdapter = CartAdapter(this)

        val args = args.order
        menuViewModel.setOrderListJson(args)



        binding.rcCart.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cartAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        menuViewModel.orderList.observe(viewLifecycleOwner) { orderList ->
            if (orderList.isNotEmpty()) {
                showRecyclerView()
                cartAdapter.submitList(orderList)
            } else {
                showEmptyCartMessage()
            }
        }
    }

    private fun showRecyclerView() {
        binding.rcCart.visibility = View.VISIBLE
        Log.e("CartFragment", "showRecyclerView")
    }

    private fun showEmptyCartMessage() {
        binding.rcCart.visibility = View.GONE
        Log.e("CartFragment", "showEmptyCartMessage")
    }
    override fun onCartClick(item: CartItem) {
    }

    override fun onIncreaseCoffeeClick(item: CartItem) {
        menuViewModel.increaseCoffeeQuantity(item.coffee)
    }

    override fun onDecreaseCoffeeClick(item: CartItem) {
        menuViewModel.decreaseCoffeeQuantity(item.coffee)
    }
}
