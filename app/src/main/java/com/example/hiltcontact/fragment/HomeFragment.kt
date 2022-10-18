package com.example.hiltcontact.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.hiltcontact.adapter.RVContactsAdapter
import com.example.hiltcontact.databinding.FragmentHomeBinding
import com.example.hiltcontact.model.ContactResponse
import com.example.hiltcontact.utils.buildAddDialog
import com.example.hiltcontact.utils.buildPopupMenu
import com.example.hiltcontact.utils.makeCall
import com.example.hiltcontact.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var rvContactsAdapter: RVContactsAdapter
    private lateinit var dialog: AlertDialog
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.swipeRefreshLayout.isEnabled = false

        binding.imageAdd.setOnClickListener {
            if (it.isClickable) {
                it.isClickable = false
                buildAddDialog(requireActivity(), binding.imageAdd) { contactRequest, alertDialog ->
                    dialog = alertDialog
                    viewModel.addContact(contactRequest, binding.swipeRefreshLayout)
                }
            }
        }

        buildRV()

        responses()

        viewModel.getAllContact(binding.swipeRefreshLayout)

        return binding.root
    }

    private fun responses() {
        viewModel.getResponse.observe(requireActivity()) {
            binding.swipeRefreshLayout.isRefreshing = false
            rvContactsAdapter.setData(it as ArrayList<ContactResponse>)
        }

        viewModel.audResponse.observe(requireActivity()) {
            binding.swipeRefreshLayout.isRefreshing = false
            try {
                dialog.cancel()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT).show()
            if (!it.error) {
                viewModel.getAllContact(binding.swipeRefreshLayout)
            }
        }

    }

    private fun buildRV() {
        rvContactsAdapter = RVContactsAdapter(object : RVContactsAdapter.RVClickContactsAdapter {
            override fun click(contactResponse: ContactResponse) {
                makeCall(requireActivity(), contactResponse.number)
            }

            override fun menu(contactResponse: ContactResponse, view: View) {
                if (view.isClickable) {
                    view.isClickable = false
                    buildPopupMenu(
                        requireActivity(),
                        contactResponse,
                        view
                    ) { response, alertDialog, i ->
                        if (response != null && alertDialog != null) {
                            dialog = alertDialog
                            viewModel.updateContact(response, binding.swipeRefreshLayout)
                        } else {
                            viewModel.deleteContact(i!!, binding.swipeRefreshLayout)
                        }
                    }
                }
            }
        })
        binding.rvContact.adapter = rvContactsAdapter
    }
}