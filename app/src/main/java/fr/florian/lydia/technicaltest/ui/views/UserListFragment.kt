package fr.florian.lydia.technicaltest.ui.views

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import fr.florian.lydia.technicaltest.R
import fr.florian.lydia.technicaltest.databinding.FragmentUserListBinding
import fr.florian.lydia.technicaltest.ui.adapters.UserListAdapter
import fr.florian.lydia.technicaltest.ui.interfacces.BottomListListener
import fr.florian.lydia.technicaltest.ui.viewmodels.UserListViewModel
import kotlinx.android.synthetic.main.fragment_user_list.view.*

class UserListFragment : Fragment(), BottomListListener {
    private val userListViewModel: UserListViewModel by viewModels()

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: UserListAdapter

    private var hasInternetConnection: Boolean = false
    private var hasLostInternetOnce: Boolean = false
    private var isFirstLaunch: Boolean = true

    private lateinit var displayNoInternetConnection: () -> Unit
    private lateinit var displayFoundAgainInternetConnection: () -> Unit

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
        adapter = UserListAdapter(arrayListOf(), this)
        binding.listUserRecycler.layoutManager = linearLayoutManager
        binding.listUserRecycler.adapter = adapter

        userListViewModel.users.observe(viewLifecycleOwner, Observer {
            adapter.addBatch(it)
        })

        val swipeRefreshLayout = binding.root.list_user_refresher
        swipeRefreshLayout.setColorSchemeResources(
            R.color.colorPrimary,
            android.R.color.holo_green_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_blue_dark
        )

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = true
            adapter.resetUsers()
            userListViewModel.retrieveBatch(hasInternetConnection, true)
            swipeRefreshLayout.isRefreshing = false
        }

        displayNoInternetConnection = {
            Snackbar.make(binding.root, "No Internet", Snackbar.LENGTH_SHORT)
                .show()
        }

        displayFoundAgainInternetConnection = {
            Snackbar.make(binding.root, "found again Internet", Snackbar.LENGTH_SHORT)
                .show()
        }

        return binding.root
    }

    override fun hasHitBottomList() {
        userListViewModel.retrieveBatch(hasInternetConnection)
    }

    /**
     *
     */
    private val networkChangeReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {
                it.extras?.let { bundle ->
                    val ni =
                        bundle[ConnectivityManager.EXTRA_NETWORK_INFO] as NetworkInfo?

                    if (ni != null && ni.state == NetworkInfo.State.CONNECTED) {

                        hasInternetConnection = true
                        if (hasLostInternetOnce) {
                            displayFoundAgainInternetConnection()
                            hasLostInternetOnce = false
                        }
                    }

                    if (bundle.getBoolean(
                            ConnectivityManager.EXTRA_NO_CONNECTIVITY,
                            false
                        )
                    ) {
                        hasInternetConnection = false
                        displayNoInternetConnection()
                        hasLostInternetOnce = true
                    }

                    if (isFirstLaunch) {
                        userListViewModel.retrieveBatch(hasInternetConnection)
                        isFirstLaunch = !isFirstLaunch
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        requireActivity().registerReceiver(networkChangeReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        requireActivity().unregisterReceiver(networkChangeReceiver)
    }
}