package com.addisov00.testtaskmts.presentation.main_screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.addisov00.testtaskmts.R
import com.addisov00.testtaskmts.common.ClickListener
import com.addisov00.testtaskmts.common.Constants
import com.addisov00.testtaskmts.common.di.ui.DaggerCurrencyListComponent
import com.addisov00.testtaskmts.databinding.FragmentMainBinding
import com.addisov00.testtaskmts.getAppComponent
import com.addisov00.testtaskmts.presentation.MainActivity
import com.addisov00.testtaskmts.presentation.main_screen.models.CurrencyItem
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!
    private val currencyAdapter by lazy {
        CurrencyAdapter(CurrencyItemClickListener())
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
        setupMenu()
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

        binding.toolbar.inflateMenu(R.menu.home_menu)

    }


    private fun render(state: ScreenState) {
        if (state.currentCurrencyList == null)
            viewModel.newEvent(ScreenEvent.SubscribeOnCurrencies)

        binding.pbCurrenciesLoading.visibility = if (state.isLoading) View.VISIBLE else View.GONE
        if (state.isSearching)
            currencyAdapter.submitList(state.searchingCurrencyList)
        else
            currencyAdapter.submitList(state.currentCurrencyList)
        val dateText = "${requireContext().getString(R.string.last_updated)} ${state.updatedDate}"
        binding.tvDate.text = dateText
    }


    private fun setupMenu() {

        (requireActivity() as MainActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.home_menu, menu)
                val searchView = menu.findItem(R.id.actionSearct).actionView as SearchView
                searchView.queryHint = "search..."
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        if (newText.isNullOrEmpty())
                            viewModel.newEvent(ScreenEvent.StopSearch)
                        else
                            viewModel.newEvent(ScreenEvent.SearchCurrency(newText))
                        return false
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }
        }, viewLifecycleOwner)
    }

    inner class CurrencyItemClickListener : ClickListener<CurrencyItem> {
        override fun onClick(item: CurrencyItem) {
            val bundle = bundleOf(Constants.CURRENCY_ITEM_KEY to item)
            requireActivity().findNavController(R.id.fragmentContainerView).navigate(
                R.id.action_mainFragment_to_converterFragment, bundle
            )
        }

    }

}