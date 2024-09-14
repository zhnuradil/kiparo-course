/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsappdagger.presentation.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.kiparo.newsappdagger.presentation.navigation.Navigator
import com.kiparo.newsappdagger.presentation.news.NewsFragment
import com.kiparo.newsappdagger.R

class MainActivity : AppCompatActivity(), Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigateTo(fragment = NewsFragment())
        }
    }

    override fun navigateTo(fragment: Fragment, addToBackStack: Boolean) {
        when {
            addToBackStack ->
                supportFragmentManager.commit {
                    addToBackStack(null)
                    replace(R.id.main_fragment_container, fragment)
                }

            else -> supportFragmentManager.commit {
                replace(R.id.main_fragment_container, fragment)
            }
        }
    }

    override fun navigateTo(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    override fun back() {
        supportFragmentManager.popBackStack()
    }
}