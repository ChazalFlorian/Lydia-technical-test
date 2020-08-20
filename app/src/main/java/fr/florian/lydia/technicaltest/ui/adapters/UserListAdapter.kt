package fr.florian.lydia.technicaltest.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.florian.lydia.technicaltest.R
import fr.florian.lydia.technicaltest.data.models.User
import fr.florian.lydia.technicaltest.databinding.ViewUserItemBinding
import fr.florian.lydia.technicaltest.ui.interfacces.BottomListListener
import fr.florian.lydia.technicaltest.ui.viewmodels.UserViewModel

class UserListAdapter(private val users: ArrayList<UserViewModel>, private val listener: BottomListListener) :
    RecyclerView.Adapter<UserListAdapter.UserHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewUserItemBinding.inflate(inflater)
        binding.root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return UserHolder(binding)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(users[position], ((position % 2) == 0))

        if (position == users.size - 1) {
            listener.hasHitBottomList()
        }
    }


    fun addBatch(usersToAdd: List<User>) {
        usersToAdd.forEach {
            val userViewModel = UserViewModel(it)
            users.add(userViewModel)
        }
        notifyDataSetChanged()
    }

    fun resetUsers() {
        users.clear()
    }

    class UserHolder(val binding: ViewUserItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            userViewModel: UserViewModel,
            isEven: Boolean
        ) {
            binding.user = userViewModel
            if (isEven) {
                binding.root.setBackgroundColor(binding.root.resources.getColor(R.color.bckg_even))
            } else {
                binding.root.setBackgroundColor(binding.root.resources.getColor(R.color.bckg_odd))
            }

            binding.executePendingBindings()
        }
    }
}