package fr.florian.lydia.technicaltest.ui.views

import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import fr.florian.lydia.technicaltest.R
import fr.florian.lydia.technicaltest.databinding.FragmentUserListBinding
import fr.florian.lydia.technicaltest.ui.adapters.UserListAdapter
import fr.florian.lydia.technicaltest.ui.viewmodels.UserListViewModel
import kotlinx.android.synthetic.main.fragment_user_list.view.*

class UserListFragment : Fragment() {
    private val userListViewModel: UserListViewModel by viewModels()

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: UserListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentUserListBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_user_list,
            container,
            false
        )
        binding.userListViewModel = userListViewModel
        binding.lifecycleOwner = this
        binding.listUserRecycler.setHasFixedSize(true)
        val swipeRefreshLayout = binding.root.list_user_refresher

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = true
            adapter.resetUsers()
            userListViewModel.retrieveBatch(true)
            swipeRefreshLayout.isRefreshing = false
        }

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
            android.R.color.holo_green_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_blue_dark);

        linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.isAutoMeasureEnabled = false
        adapter = UserListAdapter(arrayListOf())
        binding.listUserRecycler.layoutManager = linearLayoutManager
        binding.listUserRecycler.adapter = adapter

        userListViewModel.users.observe(viewLifecycleOwner, Observer {
            adapter.addBatch(it)
        })

        return binding.root
    }
}