package com.example.newsapplication

import android.app.Application

import dagger.hilt.android.HiltAndroidApp
import okio.Path.Companion.toOkioPath
@HiltAndroidApp
class NewsApplication : Application()
