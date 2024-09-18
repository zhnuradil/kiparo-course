package com.kiparo.newsappdagger.di

import com.kiparo.newsappdagger.presentation.news.di.NewsComponent
import dagger.Module

@Module(subcomponents = [NewsComponent::class])
class SubModule