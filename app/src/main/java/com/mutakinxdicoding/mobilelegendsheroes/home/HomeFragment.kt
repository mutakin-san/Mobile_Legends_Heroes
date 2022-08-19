package com.mutakinxdicoding.mobilelegendsheroes.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.mutakinxdicoding.mobilelegendsheroes.R
import com.mutakinxdicoding.mobilelegendsheroes.core.data.Resource
import com.mutakinxdicoding.mobilelegendsheroes.core.ui.HeroAdapter
import com.mutakinxdicoding.mobilelegendsheroes.databinding.FragmentHomeBinding
import com.mutakinxdicoding.mobilelegendsheroes.detail.DetailHeroActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
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

            homeViewModel.heroes.observe(viewLifecycleOwner) { heroes ->
                if (heroes != null) {
                    when (heroes) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            heroAdapter.submitList(heroes.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                heroes.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
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
