package com.devcluster.maker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*
import java.util.concurrent.atomic.AtomicInteger

class RecyclerAdapter(private val itemIndices: ArrayList<ItemIndex>, private val Flag:String) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun getItemCount() = itemIndices.size

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val item = itemIndices[position]
        val listener = View.OnClickListener { it ->
            Toast.makeText(it.context, "Clicked: ${item.title}", Toast.LENGTH_SHORT).show()

        }
        holder.apply {
            bind(listener, item, Flag)
            itemView.tag = item
        }
    }       
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerAdapter.ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return RecyclerAdapter.ViewHolder(inflatedView)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View = v

        fun bind(listener: View.OnClickListener, itemIndex: ItemIndex, flag : String) {

            if(flag=="0"){
                view.item_index_title.text = "title"
                view.item_index_subTitle.text = "subtitle"
                view.item_index_name.text = "name"
            }
            else{
                view.item_index_title.text = "title"
                view.item_index_subTitle.text = "subtitle"
                view.item_index_name.text = "name"
            }

            view.thumbnail.text = itemIndex.subTitle
            view.title.text = itemIndex.title
            view.date.text = itemIndex.date
            view.setOnClickListener(listener)
        }
    }
}