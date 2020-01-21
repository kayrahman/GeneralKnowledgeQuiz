package neel.com.quizdoor.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.navigation
import neel.com.quizdoor.fragment.HomeFragment
import neel.com.quizdoor.R
import neel.com.quizdoor.fragment.MyAccountFragment
import neel.com.quizdoor.fragment.LeaderboardFragment




class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        replaceFragment(HomeFragment())



        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.navigation_home -> {
                    // replaceFragment(LeaderboardFragment())
                     replaceFragment(HomeFragment())

                    toolbar.title = "Take a quiz"

                    true
                }

                R.id.navigation_people -> {
                    replaceFragment(LeaderboardFragment())

                    toolbar.title = "Leaderboard"

                    true
                }
                R.id.navigation_my_account -> {
                    replaceFragment(MyAccountFragment())

                    toolbar.title = "Profile"

                    true
                }
                else -> false
            }
        }



    }

    private fun replaceFragment(fragment: androidx.fragment.app.Fragment) {
       supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_layout, fragment)
                .commit()
    }
}
