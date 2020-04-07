package org.d3if4127.miwokapp.ui.detail

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.miwok_detail_item.view.*
import org.d3if4127.miwokapp.R
import org.d3if4127.miwokapp.data.Miwok

class DetailAdapter(private val myDataset: List<Miwok>) :
    RecyclerView.Adapter<DetailAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val miwok_item = LayoutInflater.from(parent.context).inflate(R.layout.miwok_detail_item, parent, false)
        return MyViewHolder(miwok_item)
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.view.iv_miwok.setImageDrawable(null);
        holder.view.item.setBackgroundColor(Color.parseColor(myDataset?.get(position)?.background))
        holder.view.tv_defaultmiwok.text = myDataset?.get(position)?.defaultWord.toString()
        holder.view.tv_miwokword.text = myDataset?.get(position)?.miwokWord.toString()
        if (myDataset!![position].image != null) {
            Glide.with(holder.view.context)
                .load("http://dif.indraazimi.com/miwok/${myDataset?.get(position)?.image}")
                .placeholder(R.drawable.ic_launcher_foreground)
                .dontAnimate()
                .into(holder.view.iv_miwok);
        }else {
            Glide.with(holder.view.context).clear(holder.view.iv_miwok)
            holder.view.iv_miwok.setImageDrawable(null);
            holder.view.iv_miwok.visibility = View.GONE
        }
    }

}