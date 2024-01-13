package com.atom.searchcoffe.ui.coffeeshop

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
        val appComponent = (requireActivity().application as App).appComponent
        appComponent.inject(this)

        _binding = FragmentCoffeeShopBinding.inflate(inflater, container, false)

        val adapter = CoffeeShopAdapter(coffeeShops = emptyList()) { id ->
            coffeeShopViewModel.setSelectedLocationId(id)
            findNavController().navigate(
                CoffeeShopFragmentDirections.actionCoffeeShopFragmentToMenuFragment(
                    id
                )
            )
        }

        binding.rcCoffeeShop.layoutManager = LinearLayoutManager(requireContext())
        binding.rcCoffeeShop.adapter = adapter

        coffeeShopViewModel.loadLocations()
        coffeeShopViewModel.locations.observe(viewLifecycleOwner) { value ->
            when (value) {
                is ResponseState.Error -> {
                    Toast.makeText(requireContext(), "Error loading data", Toast.LENGTH_LONG).show()
                    Log.e("CoffeeShopFragment", "Error loading data")
                }

                is ResponseState.Loading -> {
                    Log.d("CoffeeShopFragment", "Loading data...")
                }

                is ResponseState.Success -> {
                    Log.d("CoffeeShopFragment", "Data loaded successfully: ${value.data}")
                    // Обновляем данные в существующем адаптере
                    adapter.updateData(value.data)
                }
            }
        }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}