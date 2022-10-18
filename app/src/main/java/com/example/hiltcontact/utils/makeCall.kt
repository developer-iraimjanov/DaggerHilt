package com.example.hiltcontact.utils

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.FragmentActivity

fun makeCall(requireActivity: FragmentActivity, number: String) {
    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${number}"))
    requireActivity.startActivity(intent)
}