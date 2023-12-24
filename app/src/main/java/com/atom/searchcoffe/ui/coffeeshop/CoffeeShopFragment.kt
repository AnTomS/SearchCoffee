package com.atom.searchcoffe.ui.coffeeshop

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.atom.searchcoffe.App
import com.atom.searchcoffe.data.network.ResponseState
import com.atom.searchcoffe.databinding.FragmentCoffeeShopBinding
import javax.inject.Inject

class CoffeeShopFragment : Fragment() {

    private var _binding: FragmentCoffeeShopBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var coffeeShopViewModel: CoffeeShopViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoffeeShopBinding.inflate(inflater, container, false)

        coffeeShopViewModel.loadLocations()
        coffeeShopViewModel.locations.observe(viewLifecycleOwner){value ->
            when(value){
                is ResponseState.Error -> {
                    Toast.makeText(requireActivity(), "some error", Toast.LENGTH_LONG).show()
                }
                is ResponseState.Loading -> {
                }
                is ResponseState.Success -> {
                    val adapter = CoffeeShopAdapter(
                        coffeeShops = value.data
                    )
                    adapter.setClickListener {id ->
                        findNavController().navigate(CoffeeShopFragmentDirections.actionCoffeeShopFragmentToMenuFragment(id))
                    }
                    binding.rcCoffeeShop.layoutManager = LinearLayoutManager(requireActivity())
                    binding.rcCoffeeShop.adapter = adapter
                    }
                }
            }

        return binding.root
    }

    private fun goToMenu() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}