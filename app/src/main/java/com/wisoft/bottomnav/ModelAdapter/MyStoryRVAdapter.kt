package com.wisoft.bottomnav.ModelAdapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wisoft.bottomnav.DataClass.MyStoryClass
import com.wisoft.bottomnav.ViewHolder.MyStoryRecyclerViewHolder
import com.wisoft.bottomnav.databinding.ListMystoryEditBinding

class MyStoryRVAdapter(private var myStoryDataMain: ArrayList<MyStoryClass>,
                       private var activity: Activity,
                       private val delegate: MyStoryRecyclerViewHolder.Delegate
) : RecyclerView.Adapter<MyStoryRecyclerViewHolder>(){
    private var context: Context? = null
    var myStoryData: ArrayList<MyStoryClass>

    init {
        myStoryData = myStoryDataMain
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyStoryRecyclerViewHolder {
        context = parent.context
        return MyStoryRecyclerViewHolder.ListMyStoryEditHolder(
            ListMystoryEditBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }
    override fun onBindViewHolder(holder: MyStoryRecyclerViewHolder, position: Int) {
        var holderItem: MyStoryRecyclerViewHolder

        if (myStoryDataMain.size > 0)
        {
            holderItem = holder as MyStoryRecyclerViewHolder.ListMyStoryEditHolder
            holderItem.checkBox.text = myStoryDataMain[position].nIndex.toString()
            holderItem.checkBox.isChecked = myStoryDataMain[position].getSelecteds()
            holderItem.idNumber.text = myStoryDataMain[position].IDNumber

            val newsItems = myStoryDataMain[position]
            holderItem.apply {
                itemView.run {
                    idNumber.text = myStoryDataMain[position].IDNumber
                    firstName.text = myStoryDataMain[position].FirstName
                    checkBox.tag = position
                    checkBox.setOnClickListener {
                        val pos = holder.checkBox.tag as Int
                        if (myStoryDataMain[pos].getSelecteds()) {
                            myStoryDataMain[pos].setSelecteds(false)
                        } else {
                            myStoryDataMain[pos].setSelecteds(true)
                        }
                    }
                    setOnClickListener { delegate.onItemClickListener(this, newsItems) }
                }
            }
        }
    }
    override fun getItemCount(): Int {
        return if (myStoryDataMain.isNotEmpty()) myStoryDataMain.size else 1
    }

    override fun getItemViewType(position: Int): Int {
          return 1
    }

    fun loadNewData(newItems: ArrayList<MyStoryClass>) {
        myStoryDataMain = newItems
        myStoryData = myStoryDataMain
        notifyDataSetChanged()
    }
}