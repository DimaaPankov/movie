package com.sekvenie.mainpresemter

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.sekvenie.App
import com.sekvenie.R
import com.sekvenie.model.network.service.Data
import com.sekvenie.model.network.service.Film
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


enum class ApiStatus{  LOADING,DONE,ERROR }

class MainPresenter(private val api: DataApi<Data>): ViewModel() {
    private var mainList: List<Film>? = null
    private var view: MainViewApi? = null
    private var sortedList: List<Film> = mutableListOf()
    private val categoryList = mutableSetOf<String>()

    private fun sortingFilmsFromABC(){
        mainList = mainList?.sortedWith(compareBy(Film::localizedname))
    }

    fun getMainList() = mainList

    fun getSortedList() = sortedList

    private fun setCategory(){
        mainList?.forEach { film ->
            film.genres.forEach{
                categoryList.add(it)
            }
        }
    }

    fun init(view: MainViewApi) {
        this.view = view
        getFilms()
    }

    private fun getFilms() {
        GlobalScope.launch(Dispatchers.IO) {
            view?.showStatus(ApiStatus.LOADING)
            try {
                GlobalScope.launch(Dispatchers.Main) {
                    mainList = api.getData().films
                    setCategory()
                    sortingFilmsFromABC()
                    view?.showFilms(mainList!!)
                    view?.showCategory(categoryList)
                    view?.showStatus(ApiStatus.DONE)
                }
            } catch (e: Exception) {
                GlobalScope.launch(Dispatchers.Main) {
                    view?.showStatus(ApiStatus.ERROR)
                }
            }
        }
    }

    fun sortingFilmsFromCategory(chooseCategory: String?){
        if(!chooseCategory.isNullOrEmpty()){
            val resultList = mutableListOf<Film>()
            mainList?.forEach { film ->
                film.genres.forEach { category ->
                    if(category == chooseCategory){
                        resultList.add(film)
                    }
                }
            }
            sortedList = resultList
        }
    }

    fun destroy() {
        view = null
    }

    fun openFilm() {
        App.navController?.navigate(R.id.action_mainFragment_to_detailFragment)
    }

}