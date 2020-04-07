package org.d3if4127.miwokapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import org.d3if4127.miwokapp.R
import org.d3if4127.miwokapp.databinding.FragmentDetailBinding
import org.d3if4127.miwokapp.ui.home.MiwokViewModel

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var detailVM: DetailViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val category = arguments?.getString("category")
        (activity as AppCompatActivity).supportActionBar?.title = category

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        detailVM = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewManager = LinearLayoutManager(context)
        detailVM.miwok.observe(viewLifecycleOwner, Observer {
            viewAdapter = DetailAdapter(it.filter { miwok -> miwok.category == category})
            recyclerView = binding.rvMiwokDetail.apply {
                layoutManager = viewManager
                adapter = viewAdapter
            }
        })

        detailVM.message.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        return binding.root
    }

}
