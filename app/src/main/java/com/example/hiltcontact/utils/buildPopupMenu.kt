package com.example.hiltcontact.utils

import android.annotation.SuppressLint
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuPopupHelper
import androidx.fragment.app.FragmentActivity
import com.example.hiltcontact.R
import com.example.hiltcontact.model.ContactResponse

@SuppressLint("RestrictedApi")
fun buildPopupMenu(
    requireActivity: FragmentActivity,
    contactResponse: ContactResponse,
    view: View,
    function: (ContactResponse?, AlertDialog?, Int?) -> Unit
) {
    val menuBuilder = MenuBuilder(requireActivity)
    val menuInflater = MenuInflater(requireActivity)
    menuInflater.inflate(R.menu.popup_menu, menuBuilder)
    val menuPopupHelper = MenuPopupHelper(requireActivity, menuBuilder, view)
    menuPopupHelper.setForceShowIcon(true)
    menuBuilder.setCallback(object : MenuBuilder.Callback {
        override fun onMenuItemSelected(menu: MenuBuilder, item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.menu_edit -> {
                    buildEditDialog(
                        requireActivity,
                        contactResponse
                    ) { contactResponse, alertDialog ->
                        function.invoke(contactResponse, alertDialog, null)
                    }
                }

                R.id.menu_delete -> {
                    buildDeleteDialog(requireActivity, contactResponse) { id ->
                        function.invoke(null, null, id)
                    }
                }
            }
            return true
        }

        override fun onMenuModeChange(menu: MenuBuilder) {}
    })

    menuPopupHelper.setOnDismissListener {
        view.isClickable = true
    }

    menuPopupHelper.show()
}