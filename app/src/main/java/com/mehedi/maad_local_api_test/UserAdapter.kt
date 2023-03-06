package com.mehedi.maad_local_api_test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mehedi.maad_local_api_test.databinding.ItemUserBinding

class UserAdapter : ListAdapter<ResponseUser, UserAdapter.UserViewHolder>(comparator) {


    class UserViewHolder(var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)


    companion object {
        val comparator = object : DiffUtil.ItemCallback<ResponseUser>() {
            override fun areItemsTheSame(oldItem: ResponseUser, newItem: ResponseUser): Boolean {

                return oldItem.email == newItem.email

            }

            override fun areContentsTheSame(oldItem: ResponseUser, newItem: ResponseUser): Boolean {

                return oldItem == newItem

            }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        return UserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position).let {

            holder.binding.userNamTv.text = it.name
            holder.binding.userEmailTv.text = it.email
            holder.binding.useridTv.text = "${it.id}"

        }
    }


}