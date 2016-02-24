/*
 * Copyright 2016.  Chung Hien, Hoang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.hoangchunghien.popularmovies.ui;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.hoangchunghien.popularmovies.PopModule;
import me.hoangchunghien.popularmovies.anotation.ForActivity;
import me.hoangchunghien.popularmovies.ui.common.BaseActivity;

@Module(
        injects = {
                MainActivity.class,
                MainFragment.class
        },
        addsTo = PopModule.class,
        library = true,
        complete = false
)
public class ActivityModule {

    private BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    @ForActivity
    Context provideContext() {
        return activity;
    }

}
