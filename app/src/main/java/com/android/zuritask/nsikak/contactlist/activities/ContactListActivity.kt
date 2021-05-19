package com.android.zuritask.nsikak.contactlist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.zuritask.nsikak.contactlist.adapters.ContactsAdapter
import com.android.zuritask.nsikak.contactlist.databinding.ActivityContactListBinding
import com.android.zuritask.nsikak.contactlist.fragments.CreateContactDialog
import com.android.zuritask.nsikak.contactlist.models.Contact

class ContactListActivity : AppCompatActivity(), CreateContactDialog.OnSaveContactListener {

    private lateinit var binding: ActivityContactListBinding
    private lateinit var contacts: MutableList<Contact>
    private lateinit var adapter: ContactsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categoryName = intent.extras?.getString(CATEGORY_NAME_INTENT_EXTRA_KEY)
        categoryName?.let {
            title = "$it Contacts"
        }
        contacts = mutableListOf()
        adapter = ContactsAdapter(contacts)
        binding.contactListRecyclerView.adapter = adapter

        binding.addNewContactFab.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val fragmentManager = supportFragmentManager
        val createContactAlertDialog = CreateContactDialog()
        createContactAlertDialog.show(fragmentManager, null)
    }

    override fun onSave(contact: Contact) {
        contacts.add(contact)
        adapter.notifyItemInserted(contacts.size - 1)
    }
}