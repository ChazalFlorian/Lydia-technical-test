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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import fr.florian.lydia.technicaltest.R
import fr.florian.lydia.technicaltest.databinding.FragmentUserListBinding
import fr.florian.lydia.technicaltest.ui.adapters.UserListAdapter
import fr.florian.lydia.technicaltest.ui.viewmodels.UserListViewModel

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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UserDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserDetailFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}