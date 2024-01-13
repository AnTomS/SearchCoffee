package com.atom.searchcoffe.ui.cart

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.atom.searchcoffe.App
import com.atom.searchcoffe.databinding.FragmentCartBinding


class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!


//    @Inject
//    lateinit var cartViewModel: CartViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val appComponent = (requireActivity().application as App).appComponent
        appComponent.inject(this)
        // Inflate the layout for this fragment

        _binding = FragmentCartBinding.inflate(inflater, container, false)


        return binding.root
    }



}
