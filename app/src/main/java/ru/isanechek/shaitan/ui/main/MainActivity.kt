package ru.isanechek.shaitan.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import ru.isanechek.shaitan.utils.tools._id
import ru.isanechek.shaitan.utils.tools._layout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(_layout.activity_main)
    }

    override fun onSupportNavigateUp(): Boolean =
            findNavController(this, _id.main_host_fragment).navigateUp()
}
