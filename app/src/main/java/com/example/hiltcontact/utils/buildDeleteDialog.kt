package com.example.hiltcontact.utils

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import com.example.hiltcontact.model.ContactResponse

fun buildDeleteDialog(
    requireActivity: FragmentActivity,
    contactResponse: ContactResponse,
    function: (id: Int) -> Unit
) {
    val builder = AlertDialog.Builder(requireActivity)
    builder.setMessage("Are you sure you want to delete")
        .setPositiveButton("Delete") { _, _ ->
            function.invoke(contactResponse.id)
        }
        .setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

    val alert: AlertDialog = builder.create()
    alert.show()
}