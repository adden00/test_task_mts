package com.addisov00.testtaskmts.presentation.main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.addisov00.testtaskmts.databinding.FragmentMainBinding
import com.addisov00.testtaskmts.presentation.main_screen.model.CurrencyItem

class MainFragment: Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!
    private val currencyAdapter by lazy {
        CurrencyAdapter()
    }
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUi()
    }

    private fun setUi() {
        binding.rcCurrencies.adapter = currencyAdapter
        binding.rcCurrencies.layoutManager = GridLayoutManager(requireContext(), 2)
        val stubList = listOf(
            CurrencyItem("AED", "0.0456"),
            CurrencyItem("EUR", "0.0456")
        )
        currencyAdapter.submitList(stubList)
    }

}