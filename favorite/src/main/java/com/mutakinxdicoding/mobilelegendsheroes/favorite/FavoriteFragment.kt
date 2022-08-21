package com.mutakinxdicoding.mobilelegendsheroes.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.mutakinxdicoding.mobilelegendsheroes.core.domain.model.Hero
import com.mutakinxdicoding.mobilelegendsheroes.core.ui.HeroAdapter
import com.mutakinxdicoding.mobilelegendsheroes.detail.DetailHeroActivity
import com.mutakinxdicoding.mobilelegendsheroes.favorite.databinding.FragmentFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(favoriteModule)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val heroAdapter = HeroAdapter { selectedData ->
                val intent = Intent(activity, DetailHeroActivity::class.java)
                intent.putExtra(DetailHeroActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            favoriteViewModel.favoriteHero.observe(viewLifecycleOwner) { dataHero : List<Hero> ->
                heroAdapter.submitList(dataHero)
                binding.viewEmpty.root.visibility =
                    if (dataHero.isNotEmpty()) View.GONE else View.VISIBLE
            }

            with(binding.rvHeroes) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = heroAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
