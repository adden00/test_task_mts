package com.addisov00.testtaskmts.presentation.converter_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.addisov00.testtaskmts.R
import com.addisov00.testtaskmts.common.Constants
import com.addisov00.testtaskmts.common.customGetParcelable
import com.addisov00.testtaskmts.databinding.FragmentConverterBinding
import com.addisov00.testtaskmts.presentation.main_screen.models.CurrencyItem


class ConverterFragment : Fragment() {

    private var _binding: FragmentConverterBinding? = null
    private val binding: FragmentConverterBinding
        get() = _binding!!

    val currentCurrencyItem: CurrencyItem by lazy {
        arguments?.customGetParcelable(Constants.CURRENCY_ITEM_KEY) as? CurrencyItem
            ?: throw Exception("currency is null!!")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConverterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
    }

    private fun setUi() {
        binding.toolbar.title =
            "${requireContext().getString(R.string.convertation)} ${currentCurrencyItem.name}"
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().findNavController(R.id.fragmentContainerView).navigateUp()
        }
        val currentRateText =
            "1 ${currentCurrencyItem.name} = ${currentCurrencyItem.value} RUB"
        binding.textView.text = currentRateText

        binding.edTargetCurrency.hint = currentCurrencyItem.name
        binding.edBaseCurrency.addTextChangedListener {
            if (binding.edBaseCurrency.isFocused) {
                if (binding.edBaseCurrency.text.isNullOrEmpty())
                    binding.edTargetCurrency.setText("")
                else
                    binding.edTargetCurrency.setText(
                        (it?.toString()?.toFloat()
                            ?.div(currentCurrencyItem.value))?.toString() ?: ""
                    )
            }
        }

        binding.edTargetCurrency.addTextChangedListener {
            if (binding.edTargetCurrency.isFocused) {

                if (binding.edTargetCurrency.text.isNullOrEmpty())
                    binding.edBaseCurrency.setText("")
                else
                    binding.edBaseCurrency.setText(
                        (it?.toString()?.toFloat()
                            ?.times(currentCurrencyItem.value))?.toString() ?: ""
                    )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}