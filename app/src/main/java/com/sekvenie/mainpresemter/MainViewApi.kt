package com.sekvenie.mainpresemter

import com.sekvenie.model.network.service.Film

interface MainViewApi {

    fun showFilms(films: List<Film>)

    fun showStatus(status: ApiStatus)

    fun showCategory(categories: Set<String>)

}