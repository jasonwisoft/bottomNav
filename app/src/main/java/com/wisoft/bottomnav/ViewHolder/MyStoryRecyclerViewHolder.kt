package com.wisoft.bottomnav.ViewHolder

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.wisoft.bottomnav.DataClass.MyStoryClass
import com.wisoft.bottomnav.databinding.ListMystoryEditBinding

sealed class MyStoryRecyclerViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root)
{
    interface Delegate {
        fun onItemClickListener(view: View, saveItems: MyStoryClass)
    }

    class ListMyStoryEditHolder(binding: ListMystoryEditBinding): MyStoryRecyclerViewHolder(binding)
    {
        val checkBox: CheckBox = binding.checkboxNIndex
        val idNumber: TextView = binding.IDNumber
        val firstName: TextView = binding.FirstName
        val lastName: TextView = binding.lastName
        val address: TextView = binding.Address
        val signatureFileName: TextView = binding.SignatureFileName
    }
}
