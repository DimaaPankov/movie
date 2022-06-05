package com.sekvenie.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.sekvenie.R
import com.sekvenie.adapter.UserAdapter
import com.sekvenie.databinding.ButtonItemBinding
import com.sekvenie.databinding.FragmentMainBinding
import com.sekvenie.mainpresemter.ApiStatus
import com.sekvenie.mainpresemter.MainPresenter
import com.sekvenie.mainpresemter.MainViewApi
import com.sekvenie.model.network.dataimpl.DataImpl
import com.sekvenie.model.network.service.Film


class MainFragment : Fragment(), MainViewApi {

    private val presenter by lazy { MainPresenter(DataImpl())}
    private val binding: FragmentMainBinding by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.init(this)

        binding.options.visibility = View.INVISIBLE
        binding.Btitle.setOnClickListener{
            if(binding.options.visibility == View.INVISIBLE){
                binding.options.visibility = View.VISIBLE
            } else {
                binding.options.visibility = View.INVISIBLE
            }
        }
        }

    override fun showFilms(films: List<Film>) {
        binding.recyclerView.adapter = UserAdapter { presenter.openFilm() }
            .apply {
                clearList()
                setList(films)
            }
    }

    override fun showCategory(categories: Set<String>) {
        val layout = binding.constraintLayout
        val options = binding.options
        var selectedButton: View? = null
        categories.forEach{ text ->
            val bindingButtons = ButtonItemBinding.inflate(layoutInflater)
            bindingButtons.buttonItem.text = text
            layout.addView(bindingButtons.root)
            bindingButtons.buttonItem.setOnClickListener{
                selectedButton?.setBackgroundColor(resources.getColor(R.color.black))
                if (selectedButton != it) {
                    selectedButton = it
                    it.setBackgroundColor(resources.getColor(R.color.purple_200))
                    presenter.sortingFilmsFromCategory(text)
                    showFilms(presenter.getSortedList())
                } else{
                    selectedButton = null
                    val mainList = presenter.getMainList()
                    if(!mainList.isNullOrEmpty()){
                        showFilms(mainList)
                    }
                }
                options.visibility = View.INVISIBLE
            }
        }
    }

    override fun showStatus(status: ApiStatus) {
        when(status) {
            ApiStatus.DONE -> {}
            ApiStatus.ERROR -> {}
            ApiStatus.LOADING -> {}
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

}