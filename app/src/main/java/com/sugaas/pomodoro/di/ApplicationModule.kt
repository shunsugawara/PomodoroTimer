package com.sugaas.pomodoro.di

import com.sugaas.pomodoro.Foo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

    @Provides
    fun provideFoo(): Foo {
        return Foo()
    }


}
