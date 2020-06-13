package com.example.loginregister


import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.content_main.*


class dashboard : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    private lateinit var mWindowManager: WindowManager
    private lateinit var mChatHeadView: View

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        val btn_Learning: ImageButton = findViewById(R.id.btnLearning)
        btnLearning.setOnClickListener {
            val intent = Intent(this, Learning_Dashboard::class.java)
            startActivity(intent)

        }

        val btn_project: ImageButton = findViewById(R.id.btnProject)
        btnProject.setOnClickListener {
            val intent = Intent(this, Project::class.java)
            startActivity(intent)
        }

        val btn_work: ImageButton = findViewById(R.id.btnWork)
        btnWork.setOnClickListener {
            val intent = Intent(this, Work::class.java)
            startActivity(intent)
        }

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        mChatHeadView = LayoutInflater.from(this).inflate(R.layout.chat_head_layout, null)

        val layoutParms = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            WindowManager.LayoutParams.TYPE_PHONE
        }

        //Add the view to the window.
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            layoutParms,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        //Specify the chat head initial position
        params.gravity = Gravity.TOP or Gravity.START

        params.x = 0
        params.y = 300

        //Add the view to the window
        mWindowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        mWindowManager.addView(mChatHeadView, params)

        val chatIcon: ImageView = mChatHeadView.findViewById(R.id.chatIcon)
        val chatText: TextView = mChatHeadView.findViewById(R.id.chatText)

        chatIcon.setOnClickListener {
            val intent =
                Intent(this@dashboard, ChatActivity::class.java)
            startActivity(intent)
            mChatHeadView.visibility = View.GONE
        }

        chatText.setOnClickListener {
            val intent =
                Intent(this@dashboard, ChatActivity::class.java)
            startActivity(intent)
            mChatHeadView.visibility = View.GONE
        }

        mChatHeadView.setOnTouchListener(object : View.OnTouchListener {
            private var lastAction = 0
            private var initialX = 0
            private var initialY = 0
            private var initialTouchX = 0f
            private var initialTouchY = 0f
            override fun onTouch(v: View?, event: MotionEvent): Boolean {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {

                        //remember the initial position.
                        initialX = params.x
                        initialY = params.y

                        //get the touch location
                        initialTouchX = event.rawX
                        initialTouchY = event.rawY
                        lastAction = event.action
                        return true
                    }
                    MotionEvent.ACTION_UP -> {
                        lastAction = event.action
                        return true
                    }
                    MotionEvent.ACTION_MOVE -> {
                        //Calculate the X and Y coordinates of the view.
                        params.x = initialX + (event.rawX - initialTouchX).toInt()
                        params.y = initialY + (event.rawY - initialTouchY).toInt()

                        //Update the layout with new X & Y coordinate
                        mWindowManager.updateViewLayout(mChatHeadView, params)
                        lastAction = event.action
                        return true
                    }
                }
                return false
            }
        })

    }

    override fun onResume() {
        super.onResume()

        mChatHeadView.visibility = View.VISIBLE

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
                startActivity(Intent(applicationContext, Learning_Dashboard::class.java))
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
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                searchItem.collapseActionView()
                Toast.makeText(this@dashboard, "Looking for $query", Toast.LENGTH_LONG).show()
                return true

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Toast.makeText(this@dashboard, "Looking for $newText", Toast.LENGTH_LONG).show()

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




