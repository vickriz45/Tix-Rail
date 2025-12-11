package id.ac.pnm.tix_rail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import id.ac.pnm.tix_rail.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        username = intent.getStringExtra("EXTRA_USERNAME")
        if (username.isNullOrEmpty()) {
            username = "(NAMA PENGGUNA)"
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureBottomNav()

        if (savedInstanceState == null) {
            val homeFragment = HomeFragment.newInstance(username!!)
            setCurrentFragment(homeFragment)
            binding.bottomNavigationBar.selectedItemId = R.id.menu_beranda
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }

    private fun configureBottomNav() {
        binding.bottomNavigationBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_beranda -> {
                    val usernameToSend = username ?: "Pengguna"
                    val homeFragment = HomeFragment.newInstance(usernameToSend)
                    setCurrentFragment(homeFragment)
                }
                R.id.menu_tiket_saya -> setCurrentFragment(TiketSayaFragment())
                R.id.menu_promo -> setCurrentFragment(PromoFragment())
                R.id.menu_profil -> setCurrentFragment(ProfileFragment())
                else -> false
            }
            true
        }
    }

    fun navigateToFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment, tag)
            addToBackStack(tag)
            commit()
        }
    }
}