package com.example.hiltcontact.app

import android.app.Application
import com.example.hiltcontact.data.repository.MyRepository
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApp : Application()