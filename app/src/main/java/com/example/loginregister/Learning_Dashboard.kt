package com.example.loginregister

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_learning_dashboard_contents.*


class Learning_Dashboard : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learning_dashboard)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        // click listeners for buttons
        pythonbutton.setOnClickListener {
            val intent = Intent(this, PythonPage::class.java)
            startActivity(intent)
        }
        javabutton.setOnClickListener {
            val intent = Intent(this, JavaPage::class.java)
            startActivity(intent)
        }
        phpbutton.setOnClickListener {
            val intent = Intent(this, PHPPage::class.java)
            startActivity(intent)
        }
        htmlbutton.setOnClickListener {
            val intent = Intent(this, PHPPage::class.java)
            startActivity(intent)
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                startActivity(Intent(applicationContext, UserProfileActivity::class.java))
            }
            R.id.nav_messages -> {
                Toast.makeText(this, "Messages clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_friends -> {
                Toast.makeText(this, "Friends clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_dashboard -> {
                Toast.makeText(this, "Dashboard clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_Learning -> {
                Toast.makeText(this, "Learning clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_projects -> {
                startActivity(Intent(applicationContext, Project::class.java))
            }
            R.id.nav_work -> {
                startActivity(Intent(applicationContext, Work::class.java))
            }

        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.action_menu, menu)
        menuInflater.inflate(R.menu.search_menu, menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.menu_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            // search form is submitted or user has pressed ok button
            // take query and apply search
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                searchItem.collapseActionView()
                Toast.makeText(this@Learning_Dashboard, "Looking for $query", Toast.LENGTH_LONG)
                    .show()
                return true

            }

            // user is typing ( adding or removing characters )
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId;
        if (id == R.id.nav_settings) {
            Toast.makeText(this, "settings clicked", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.nav_log_out) {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

}




