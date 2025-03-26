package edu.temple.dicethrow

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/*
Our DieThrow application has been refactored to move the dieRoll() logic
into the ViewModel instead of the Fragment.
Study the DieViewModel, ButtonFragment, and DieFragment classes to
see the changes.

Follow the requirements below to have this app function
in both portrait and landscape configurations.
The Activity layout files for both Portrait and Landscape are already provided
*/
class MainActivity : AppCompatActivity(), ButtonFragment.ButtonInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment1 = ButtonFragment()
        val fragment2 = DieFragment()


        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container1, ButtonFragment())
                    .commit()
            }
        } else {
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container1, fragment1)
                    .replace(R.id.container2, fragment2)
                    .commit()
            }
            else {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container1, fragment1)
                    .commit()
            }
        }

    }

    override fun buttonClicked() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            val newFragment = DieFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container1, newFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}
