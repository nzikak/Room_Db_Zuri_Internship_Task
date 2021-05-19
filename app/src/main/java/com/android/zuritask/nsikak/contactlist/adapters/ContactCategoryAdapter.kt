package com.android.zuritask.nsikak.contactlist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.zuritask.nsikak.contactlist.databinding.CategoryListItemBinding
import com.android.zuritask.nsikak.contactlist.models.ContactCategory


class ContactCategoryAdapter(
    private val categories: List<ContactCategory>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<ContactCategoryAdapter.ContactCategoryViewHolder>() {

    inner class ContactCategoryViewHolder
        (private val binding: CategoryListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val category = categories[adapterPosition]
                listener.onItemClick(category)
            }
        }

        fun bind(contactCategory: ContactCategory) {
            binding.apply {
                categoryTitleTextView.text = contactCategory.name
                categoryIconImageView.setImageResource(contactCategory.icon)

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactCategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoryListItemBinding.inflate(inflater, parent, false)
        return ContactCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactCategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    interface OnItemClickListener {

        fun onItemClick(category: ContactCategory)
    }
}