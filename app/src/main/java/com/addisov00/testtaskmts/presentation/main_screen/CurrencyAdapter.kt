package com.addisov00.testtaskmts.presentation.main_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.addisov00.testtaskmts.R
import com.addisov00.testtaskmts.common.utills.ClickListener
import com.addisov00.testtaskmts.databinding.CurrencyItemBinding
import com.addisov00.testtaskmts.presentation.main_screen.models.CurrencyItem

class CurrencyAdapter(private val onClickListener: ClickListener<CurrencyItem>) :
    ListAdapter<CurrencyItem, CurrencyAdapter.ItemHolder>(object :
        DiffUtil.ItemCallback<CurrencyItem>() {
        override fun areItemsTheSame(oldItem: CurrencyItem, newItem: CurrencyItem): Boolean =
            oldItem.name == newItem.name


        override fun areContentsTheSame(oldItem: CurrencyItem, newItem: CurrencyItem): Boolean =
            oldItem == newItem
    }) {


    inner class ItemHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun setData(item: CurrencyItem) {
            val binding = CurrencyItemBinding.bind(view)
            binding.tvCurrencyName.text = item.name
            binding.tvCurrencyValue.text = item.value.toString()
            itemView.setOnClickListener {
                onClickListener.onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder =
        ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.currency_item, parent, false))

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(getItem(position))
    }
}