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
package me.hoangchunghien.popularmovies.data.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.hoangchunghien.popularmovies.BuildConfig;
import me.hoangchunghien.popularmovies.anotation.ForApplication;
import retrofit.Endpoints;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

import static java.util.concurrent.TimeUnit.SECONDS;

@Module(
        library = true,
        complete = false
)
public class ApiModule {

    public static final String MOVIE_DB_API_URL = "http://api.themoviedb.org/3";

    public static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(@ForApplication Context app) {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(10, SECONDS);
        client.setReadTimeout(10, SECONDS);
        client.setWriteTimeout(10, SECONDS);

        // Install an HTTP cache in the application cache directory.
        File cacheDir = new File(app.getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
        client.setCache(cache);

        return client;
    }

    @Provides
    @Singleton
    RestAdapter provideRestAdapter(OkHttpClient client, Gson gson) {
        return new RestAdapter.Builder()
                .setClient(new OkClient(client))
                .setEndpoint(Endpoints.newFixedEndpoint(MOVIE_DB_API_URL))
                .setLogLevel(RestAdapter.LogLevel.NONE)
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addQueryParam("api_key", BuildConfig.THE_MOVIE_DB_API_KEY);
                    }
                })
                .setConverter(new GsonConverter(gson))
                .build();
    }

    @Provides
    @Singleton
    TheMovieDbApi provideMoviesApi(RestAdapter restAdapter) {
        return restAdapter.create(TheMovieDbApi.class);
    }

}
