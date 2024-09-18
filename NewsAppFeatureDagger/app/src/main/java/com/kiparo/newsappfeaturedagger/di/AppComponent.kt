package com.kiparo.newsappfeaturedagger.di

import android.content.Context
import com.kiparo.newsappfeaturedagger.presentation.navigation.NavigatorFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        RepositoriesModule::class,
        FeaturesModule::class]
)
interface AppComponent {

    fun inject(fragment: NavigatorFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}