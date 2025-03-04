package com.example.salaslivres

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.salaslivres.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var drawer : DrawerLayout
    private var isSidebarVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        replaceFragment(IndexFragment())

        setNavNavigation()
        setDrawerNavigation()

    }

    private fun setNavNavigation() {
        // Configura o comportamento do BottomNavigationView
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.livreB -> replaceFragment(LivreFragment())
                R.id.indexB -> replaceFragment(IndexFragment())
                R.id.melhoresB -> replaceFragment(MelhoresFragment())
                else -> false
            }
            true
        }

        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.livreB -> replaceFragment(LivreFragment())
                R.id.indexB -> replaceFragment(IndexFragment())
                R.id.melhoresB -> replaceFragment(MelhoresFragment())
                else -> false
            }
            true
        }

        setSupportActionBar(binding.myToolbar)
    }


    private fun setDrawerNavigation() {

        // Configurar o botão de menu (hambúrguer)
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.myToolbar,
            R.string.open_nav,
            R.string.close_nav
        )

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        setSupportActionBar(binding.myToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        // Configurar o BottomNavigationView


        binding.myToolbar.setNavigationOnClickListener {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        binding.horizontalNavView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.indexB -> {
                    replaceFragment(IndexFragment())
                }

                R.id.livreB -> {
                    replaceFragment(LivreFragment())
                }

                R.id.melhoresB -> {
                    replaceFragment(MelhoresFragment())
                }
                else -> {
                    Toast.makeText(this, "Opção inválida", Toast.LENGTH_SHORT).show()
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    public fun goToIndex() {
        val goIndex = Intent(this, IndexFragment::class.java)
        startActivity(goIndex)
    }

    public fun goToBest(view : View) {
        val goBest = Intent(this, MelhoresFragment::class.java)
        startActivity(goBest)
    }


}