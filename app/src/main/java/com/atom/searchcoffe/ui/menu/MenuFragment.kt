package com.atom.searchcoffe.ui.menu

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.atom.searchcoffe.App
import com.atom.searchcoffe.databinding.FragmentMenuBinding
import com.atom.searchcoffe.ui.coffeeshop.CoffeeShopViewModel
import javax.inject.Inject

class MenuFragment : Fragment() {


    private var _binding: FragmentMenuBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var coffeeShopViewModel: CoffeeShopViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val appComponent = (requireActivity().application as App).appComponent
        appComponent.inject(this)
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}