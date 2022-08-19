package com.mutakinxdicoding.mobilelegendsheroes.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.mutakinxdicoding.mobilelegendsheroes.R
import com.mutakinxdicoding.mobilelegendsheroes.core.data.Resource
import com.mutakinxdicoding.mobilelegendsheroes.core.domain.model.DetailHero
import com.mutakinxdicoding.mobilelegendsheroes.core.domain.model.Hero
import com.mutakinxdicoding.mobilelegendsheroes.core.ui.SkillsAdapter
import com.mutakinxdicoding.mobilelegendsheroes.databinding.ActivityDetailHeroBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailHeroActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailHeroViewModel: DetailHeroViewModel by viewModel()
    private lateinit var binding: ActivityDetailHeroBinding
    private val skillsAdapter: SkillsAdapter by lazy {
        SkillsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.detailToolbar)

        val detailHero = intent.getParcelableExtra<Hero>(EXTRA_DATA)
        binding.content.rvSkills.adapter = skillsAdapter
        binding.content.rvSkills.setHasFixedSize(true)


        detailHero?.let {
            supportActionBar?.title = detailHero.heroName

            var statusFavorite = detailHero.isFavorite

            setStatusFavorite(statusFavorite)

            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailHeroViewModel.setFavoriteHero(detailHero, statusFavorite)
                setStatusFavorite(statusFavorite)
            }

            fetchDetailHero(detailHero)
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun fetchDetailHero(detailHero: Hero) {
        detailHeroViewModel.getDetailHero(detailHero.heroId).observe(this) { result ->
            if (result != null) {
                when (result) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        result.data?.let {
                            showDetailHero(it)
                        }
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        showSnackBar(result.message ?: getString(R.string.something_wrong))
                    }
                }
            }
        }
    }

    private fun showDetailHero(detailHero: DetailHero) {
        supportActionBar?.title = detailHero.name
        binding.content.tvRole.text = detailHero.type
        Glide.with(this@DetailHeroActivity)
            .load(detailHero.coverPicture)
            .into(binding.ivDetailImage)


        skillsAdapter.submitList(detailHero.skills)
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_white
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_not_favorite_white
                )
            )
        }
    }
}
