package org.d3if4127.miwokapp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.d3if4127.miwokapp.R
import org.d3if4127.miwokapp.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var miwokVM: MiwokViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Miwok - 6706184127"
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_home, container, false)
        miwokVM = ViewModelProviders.of(this).get(MiwokViewModel::class.java)
        binding.setLifecycleOwner(this)
        binding.miwokVM = miwokVM

        viewManager = LinearLayoutManager(context)
        miwokVM.miwok.observe(viewLifecycleOwner, Observer {
            viewAdapter = MiwokAdapter(
                it.distinctBy { miwok -> miwok.category },
                MiwokAdapter.OnClickListener { title ->
                    view?.findNavController()?.navigate(R.id.action_homeFragment_to_detailFragment,
                    bundleOf("category" to title))
                }
            )
            recyclerView = binding.rvMiwok.apply {
                layoutManager = viewManager
                adapter = viewAdapter
            }
        })

        miwokVM.message.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        return binding.root
    }

}
