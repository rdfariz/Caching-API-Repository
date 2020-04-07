package org.d3if4127.miwokapp.ui.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.miwok_item.view.*
import org.d3if4127.miwokapp.R
import org.d3if4127.miwokapp.data.Miwok

class MiwokAdapter(private val myDataset: List<Miwok>, val onClickListener: OnClickListener) :
    RecyclerView.Adapter<MiwokAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiwokAdapter.MyViewHolder {
        val miwok_item = LayoutInflater.from(parent.context).inflate(R.layout.miwok_item, parent, false)
        return MyViewHolder(miwok_item)
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }

    override fun onBindViewHolder(holder: MiwokAdapter.MyViewHolder, position: Int) {
        holder.view.item.setBackgroundColor(Color.parseColor( myDataset[position].background))
        holder.view.tv_category.text = myDataset[position].category
        holder.view.setOnClickListener {
            onClickListener.onClick(myDataset[position].category)
        }

    }

    class OnClickListener(val clickListener: (title: String) -> Unit) {
        fun onClick(title: String) = clickListener(title)
    }

}