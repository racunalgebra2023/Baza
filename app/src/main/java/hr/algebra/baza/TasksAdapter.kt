package hr.algebra.baza

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class TasksAdapter( context: Context, val list: ArrayList< Task > )
            : ArrayAdapter< Task >( context, R.layout.task, list ) {

    override fun getView( position: Int, convertView: View?, parent: ViewGroup ): View {
        val row = LayoutInflater
                    .from( parent.context )
                    .inflate( R.layout.task, parent, false )

        val task = list[position]
        row.findViewById< TextView >( R.id.tvTaskName ).text = task.name
        row.findViewById< TextView >( R.id.tvTaskDescription ).text = task.description

        return row
    }
}