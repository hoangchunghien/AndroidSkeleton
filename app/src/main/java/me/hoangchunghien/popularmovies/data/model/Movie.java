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
package me.hoangchunghien.popularmovies.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    public static final class Response {

        @Expose
        public int page;

        @Expose @SerializedName("total_pages")
        public int totalPages;

        @Expose @SerializedName("total_results")
        public int totalMovies;

        @Expose @SerializedName("results")
        public List<Movie> movies = new ArrayList<>();
    }

}
