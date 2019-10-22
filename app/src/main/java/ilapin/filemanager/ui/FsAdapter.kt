package ilapin.filemanager.ui

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ilapin.filemanager.domain.FsItem

class FsAdapter : RecyclerView.Adapter<FsAdapter.ViewHolder>() {

    private val fsItems = ArrayList<FsItem>()

    fun setFsItems(fsItems: List<FsItem>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = this@FsAdapter.fsItems[oldItemPosition]
                val newItem = fsItems[newItemPosition]
                return oldItem.name == newItem.name && oldItem.parentPath == newItem.parentPath
            }

            override fun getOldListSize() = this@FsAdapter.fsItems.size

            override fun getNewListSize() = fsItems.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return this@FsAdapter.fsItems[oldItemPosition] == fsItems[newItemPosition]
            }
        })

        this.fsItems.clear()
        this.fsItems += fsItems

        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(View.inflate(parent.context, android.R.layout.simple_list_item_1, parent))
    }

    override fun getItemCount() = fsItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(fsItems[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val titleView = itemView.findViewById<TextView>(android.R.id.text1)

        fun bind(fsItem: FsItem) {
            titleView.text = fsItem.name
        }
    }
}