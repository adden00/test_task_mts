package com.addisov00.testtaskmts.presentation.main_screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.addisov00.testtaskmts.R
import com.addisov00.testtaskmts.common.di.ui.DaggerCurrencyListComponent
import com.addisov00.testtaskmts.databinding.FragmentMainBinding
import com.addisov00.testtaskmts.getAppComponent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!
    private val currencyAdapter by lazy {
        CurrencyAdapter()
    }

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerCurrencyListComponent.factory().create(requireContext().getAppComponent())
            .inject(this)

    }

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUi() {
        binding.rcCurrencies.adapter = currencyAdapter
        binding.rcCurrencies.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.screenState
            .flowWithLifecycle(lifecycle)
            .onEach(::render)
            .launchIn(lifecycleScope)

    }


    private fun render(state: ScreenState) {
        binding.pbCurrenciesLoading.visibility = if (state.isLoading) View.VISIBLE else View.GONE
        currencyAdapter.submitList(state.currentCurrencyList)
        val dateText = "${requireContext().getString(R.string.last_updated)} ${state.updatedDate}"
        binding.tvDate.text = dateText
    }

}