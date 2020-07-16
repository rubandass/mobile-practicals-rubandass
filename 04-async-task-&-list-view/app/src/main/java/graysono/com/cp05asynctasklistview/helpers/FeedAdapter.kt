package graysono.com.cp05asynctasklistview.helpers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ArrayAdapter
import java.text.FieldPosition

class FeedAdapter(
    context: Context,
    private var resource: Int,
    private var feedEntries: ArrayList<FeedEntry>
) : ArrayAdapter<FeedEntry>(context, resource) {

    override fun getCount(): Int {
        return if (feedEntries.isNotEmpty()) feedEntries.size else 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val feedViewHolder: FeedViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(resource, parent, false)
            feedViewHolder = FeedViewHolder(view)
            view.tag = feedViewHolder
        } else {
            view = convertView
            feedViewHolder = view.tag as FeedViewHolder
        }

        val feedEntry: FeedEntry = feedEntries[position]
        val releaseDate: String = feedEntry.releaseDate.substring(0, 10)

        feedViewHolder.txvName.text = feedEntry.name
        feedViewHolder.txvReleaseDate.text = "Release Date: $releaseDate"
        return view
    }
}
