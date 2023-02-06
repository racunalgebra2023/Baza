package hr.algebra.baza

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var etTaskName        : EditText
    private lateinit var etTaskDescription : EditText
    private lateinit var bSave             : Button
    private lateinit var lvTasks           : ListView

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_main )
    }
}