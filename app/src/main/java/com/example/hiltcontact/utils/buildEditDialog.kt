package com.example.hiltcontact.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import com.example.hiltcontact.databinding.AddDialogBinding
import com.example.hiltcontact.model.ContactResponse

fun buildEditDialog(
    requireActivity: FragmentActivity,
    contactResponse: ContactResponse,
    function: (ContactResponse, AlertDialog) -> Unit
) {

    val alertDialog = AlertDialog.Builder(requireActivity)
    val dialogBinding = AddDialogBinding.inflate(requireActivity.layoutInflater)

    dialogBinding.edtFirstname.setText(contactResponse.name)
    dialogBinding.edtLastname.setText(contactResponse.surname)
    dialogBinding.edtNumber.setText(contactResponse.number)

    alertDialog.setView(dialogBinding.root)

    val dialog = alertDialog.create()

    dialogBinding.lyDone.setOnClickListener {
        val firstname = dialogBinding.edtFirstname.text.toString().trim()
        val lastname = dialogBinding.edtLastname.text.toString().trim()
        val number = dialogBinding.edtNumber.text.toString().trim()
        if (firstname.isNotEmpty() && lastname.isNotEmpty() && number.isNotEmpty()) {
            dialogBinding.imageDone.visibility = View.INVISIBLE
            dialogBinding.progressCircular.visibility = View.VISIBLE
            function.invoke(
                ContactResponse(contactResponse.id, firstname, lastname, number),
                dialog
            )
        }
    }

    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.show()
}