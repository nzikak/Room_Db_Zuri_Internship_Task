package com.android.zuritask.nsikak.contactlist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.zuritask.nsikak.contactlist.databinding.ContactListItemBinding
import com.android.zuritask.nsikak.contactlist.models.Contact

class ContactsAdapter(private val contacts: List<Contact>): RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ContactListItemBinding.inflate(inflater, parent, false)
        return ContactsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bind(contact)
    }

    override fun getItemCount() = contacts.size

    inner class ContactsViewHolder(private val binding: ContactListItemBinding):
        RecyclerView.ViewHolder(binding.root) {


            fun bind(contact: Contact) {
                binding.apply {
                    contactNameTextView.text = contact.name
                    contactNumberTextView.text = contact.number
                }
            }
        }
}