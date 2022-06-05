package com.sekvenie.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.sekvenie.App
import com.sekvenie.R
import com.sekvenie.databinding.FragmentDetailBinding
import com.sekvenie.deteilpresentr.DeteilViewApi
import com.sekvenie.deteilpresentr.Deteilpresenter


class DetailFragment : Fragment(),DeteilViewApi {

    private val presenter by lazy { Deteilpresenter.getInstance() }
    private val binding: FragmentDetailBinding by lazy {
        FragmentDetailBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.init(this)
        binding.buttonBack.setOnClickListener{
            App.navController?.popBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun showDeteil() {
        val film = presenter.getChooseFilm()
        film?.let{
            val localName = getFormatString(getString(R.string.pacific_rim), film.localizedname)
            val name = getFormatString(getString(R.string.name), film.name)
            val year = getFormatString(getString(R.string.year), film.year.toString())
            val rating = getFormatString(getString(R.string.rating), film.rating.toString())
            binding.TVlocalName.text = film.localizedname
            binding.localizedName.text = localName
            binding.name.text = name
            binding.year.text = year
            binding.rating.text = rating
            binding.TVdescription.text = film.description
            binding.imageView.load(film.imageurl)
        }
    }

    private fun getFormatString(keyString: String, valueString: String): String {
        return String.format("%s: %s", keyString, valueString)
    }
}