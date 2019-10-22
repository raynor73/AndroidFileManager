package ilapin.filemanager.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ilapin.filemanager.R
import ilapin.filemanager.domain.FileManager
import ilapin.filemanager.domain.FsItem

class FileManagerView(
    context: Context, attrs: AttributeSet?, defStyleAttr: Int
) : FrameLayout(context, attrs, defStyleAttr) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null)

    private val fsItemsListView: RecyclerView
    private val linearProgressBar: View
    private val circularProcessBar: View

    private val adapter = FsAdapter()

    init {
        val layout = View.inflate(context, R.layout.view_file_manager, this)

        fsItemsListView = layout.findViewById(R.id.fsItemsListView)
        linearProgressBar = layout.findViewById(R.id.linearProgressBar)
        circularProcessBar = layout.findViewById(R.id.circularProgressBar)

        fsItemsListView.layoutManager = LinearLayoutManager(context)
        fsItemsListView.adapter = adapter

        adapter.setFsItems(listOf(
            FsItem("Foo", FsItem.Type.FILE, "/"),
            FsItem("Bar", FsItem.Type.FILE, "/"),
            FsItem("Baz", FsItem.Type.FILE, "/")
        ))
    }

    private fun render(state: FileManager.State) {

    }
}