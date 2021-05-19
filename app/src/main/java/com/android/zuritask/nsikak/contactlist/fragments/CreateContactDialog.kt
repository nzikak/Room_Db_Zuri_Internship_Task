package com.android.zuritask.nsikak.contactlist.fragments

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.android.zuritask.nsikak.contactlist.databinding.CreateContactDialogBinding
import com.android.zuritask.nsikak.contactlist.models.Contact

class CreateContactDialog: DialogFragment() {

    private lateinit var binding: CreateContactDialogBinding
    private val textWatcher by lazy {
        object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.saveContactButton.isEnabled = s?.length == 11
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val layoutInflater = LayoutInflater.from(requireContext())
        binding = CreateContactDialogBinding.inflate(layoutInflater)
        val view = binding.root
        builder.setView(view)
        binding.apply {
            phoneNumberField.addTextChangedListener(textWatcher)
            saveContactButton.setOnClickListener {
                val name = nameField.text.toString()
                val number = phoneNumberField.text.toString()
                val contact = Contact(name, number)
                val listener = activity as OnSaveContactListener
                listener.onSave(contact)
                dismiss()
            }
        }

        return builder.create()
    }


    interface OnSaveContactListener {
        fun onSave(contact: Contact)
    }
}