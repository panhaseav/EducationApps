package com.example.loginregister

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class UserProfileActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var userNameTextView: TextView
    private lateinit var countryTextView: TextView
    private lateinit var cityTextView: TextView
    private lateinit var dobTextView: TextView
    private lateinit var aboutTextView: TextView
    private lateinit var emailAddressTextView: TextView
    private lateinit var userProfileImage: ImageView
    private lateinit var programminglangaugeTextView: TextView
    private lateinit var purchaseTextView: TextView

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        userNameTextView = findViewById(R.id.userNameTextView)
        countryTextView = findViewById(R.id.countryTextView)
        cityTextView = findViewById(R.id.cityTextView)
        dobTextView = findViewById(R.id.dobTextView)
        aboutTextView = findViewById(R.id.aboutTextView)
        emailAddressTextView = findViewById(R.id.emailAddressTextView)
        userProfileImage = findViewById(R.id.userProfileImage)
        programminglangaugeTextView = findViewById(R.id.programminglanguageTextView)
        purchaseTextView = findViewById(R.id.purchaseTextView)

        userProfileImage.setImageResource(R.drawable.user)

        userNameTextView.text = "Panha Phal"

        countryTextView.text = "United Kingdom"

        emailAddressTextView.text = "panhaphal@rocketmail.com"

        cityTextView.text = "Bournemouth"

        dobTextView.text = "08 May, 2000"

        programminglangaugeTextView.text ="Python, Java and PHP"
        purchaseTextView.text =" 5 application, CV prepation and cover later"

        aboutTextView.text =
            "I am new to programming and looking for a project that can " +
                    "improve my skill. " +
                    "Currently I am studying at Bournemouth University and looking to improve my programming skills.  " +
                    "I am a really hard working individual and also love to communicate with different people.At my free time I follow a number of sci-fi and fantasy genre movies and television shows,I spend a large amount of my free time exploring the latest technology advancements in the programming development world.  "


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {

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
                Toast.makeText(this, "Projects clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_work -> {
                Toast.makeText(this, "work clicked", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this@UserProfileActivity, "Looking for $query", Toast.LENGTH_LONG)
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
            Toast.makeText(this, "log out clicked", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }


}
