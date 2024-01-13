package com.atom.searchcoffe.ui.menu

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.atom.searchcoffe.App
import com.atom.searchcoffe.databinding.FragmentMenuBinding
import javax.inject.Inject

class MenuFragment : Fragment() {


    private var _binding: FragmentMenuBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var menuViewModel: MenuViewModel

    private lateinit var menuAdapter: MenuAdapter

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

        val id = arguments?.getInt("id")
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        menuAdapter = MenuAdapter(
            menuItems = emptyList(),
            onAddClickListener = { menuItem -> menuViewModel.addToCart(menuItem) },
            onRemoveClickListener = { menuItem -> menuViewModel.removeFromCart(menuItem) }
        )

        binding.recyclerView1.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = menuAdapter
        }

        observeViewModel()

        menuViewModel.getMenu(id!!)
        Log.e("MenuFragment", id.toString())
        return binding.root
    }

    private fun observeViewModel() {
        menuViewModel.menuItems.observe(viewLifecycleOwner) { menuItems ->
            Log.e("MenuFragment", menuItems.toString())
            menuAdapter.updateMenuItems(menuItems)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}