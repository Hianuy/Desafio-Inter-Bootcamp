package br.com.dio.businesscard.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.dio.businesscard.data.BusinessCard
import br.com.dio.businesscard.databinding.ItemBusinessCardBinding

class BusinessCardAdapter :
    ListAdapter<BusinessCard, BusinessCardAdapter.ViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBusinessCardBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))


    var listenerShare: (View) -> Unit = {}


    inner class ViewHolder(
        private val binding: ItemBusinessCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BusinessCard) {
            binding.txtName.text = item.name
            binding.txtPhone.text = item.phone
            binding.txtCompany.text = item.company
            binding.cardContent.setBackgroundColor(Color.parseColor(item.backgroundColor))
            binding.cardContent.setOnClickListener {
                listenerShare(it)
            }

        }
    }


}

class DiffCallBack : DiffUtil.ItemCallback<BusinessCard>() {
    override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard) = oldItem == newItem

    override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard) =
        oldItem.id == newItem.id

}