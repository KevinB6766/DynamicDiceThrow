package edu.temple.dicethrow

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import kotlin.text.replace


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

    private var isLandscape = false // Flag to track the orientation
    private lateinit var buttonFragment: ButtonFragment // instantiate fragment
    private lateinit var dieFragment: DieFragment // instantiate fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* TODO 1: Load fragment(s)
            - Show _only_ ButtonFragment if portrait
            - show _both_ fragments if Landscape
          */
        buttonFragment = ButtonFragment()
        dieFragment = DieFragment()

        isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        loadFragmentBasedOnOrientation()   // Check the orientation and load the appropriate fragment

        /* TODO 2: switch fragments if die rolled and in portrait (no need to switch fragments if Landscape)
        */

        // This callback function gets invoked when the child Fragment invokes it
        // Remember to place Fragment transactions on BackStack so then can be reversed
//        override fun buttonClicked() {
//        }
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        isLandscape = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE
        loadFragmentBasedOnOrientation()
    }

    override fun buttonClicked() {
        if (!isLandscape) {
            supportFragmentManager.commit {
                replace(R.id.container1, dieFragment)
                addToBackStack(null)
            }
        }
    }


override fun onConfigurationChanged(newConfig: Configuration) {
    super.onConfigurationChanged(newConfig)
    isLandscape = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE
    loadFragmentBasedOnOrientation()
}

override fun buttonClicked() {
    if (!isLandscape) {
        supportFragmentManager.commit {
            replace(R.id.container1, dieFragment)
            addToBackStack(null)
        }
    }
}
}