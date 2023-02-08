package hr.algebra.baza

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var etTaskName        : EditText
    private lateinit var etTaskDescription : EditText
    private lateinit var bSave             : Button
    private lateinit var lvTasks           : ListView
    private lateinit var tasksAdapter      : TasksAdapter

    // Bazi podataka pristupamo kao objektu kojeg je potrebno deklarirati/inicijalizirati
    private lateinit var db                : BazaZadataka

    private val list = ArrayList< Task >( )


    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_main )

        initWidgets( )

        // kreiranje, ili dohvat postojeće baze podataka
        db = BazaZadataka.getInstance( this )

        listTasks( )

        bSave.setOnClickListener {
            val name = etTaskName.text.toString( )
            val desc = etTaskDescription.text.toString( )
            val task = Task( null, name, desc )
            db.insertTask( task )
            etTaskName.setText( "" )
            etTaskDescription.setText( "" )
            listTasks( )
        }

        lvTasks.onItemClickListener = AdapterView.OnItemClickListener { _, _, pos, _ ->
            val task = list[pos]
            db.deleteTask( task )
            listTasks( )
        }
    }

    private fun listTasks( ) {
        val freshTasksList = db.getTasks( )
        list.clear( )
        list.addAll( freshTasksList )
        tasksAdapter.notifyDataSetChanged( )
    }

    private fun initWidgets( ) {
        etTaskName        = findViewById( R.id.etTaskName )
        etTaskDescription = findViewById( R.id.etTaskDescription )
        bSave             = findViewById( R.id.bSave )
        lvTasks           = findViewById( R.id.lvTasks )
        tasksAdapter      = TasksAdapter( this, list )
        lvTasks.adapter   = tasksAdapter
    }
}