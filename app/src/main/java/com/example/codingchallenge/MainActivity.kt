package com.example.codingchallenge

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.ContextThemeWrapper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.example.codingchallenge.Binding.BinderHandler
import com.example.codingchallenge.Model.Users
import com.example.codingchallenge.RecyclerviewBinding.adapter.ClickHandler
import com.example.codingchallenge.RecyclerviewBinding.adapter.LongClickHandler
import com.example.codingchallenge.RecyclerviewBinding.adapter.binder.ItemBinder
import com.example.codingchallenge.RecyclerviewBinding.adapter.binder.ItemBinderBase
import com.example.codingchallenge.Utils.BindingPresenter
import com.example.codingchallenge.Utils.IntentUtils
import com.example.codingchallenge.Utils.SessionManager
import com.example.codingchallenge.databinding.RecyclerviewbindingBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), BinderHandler<Any> {

    lateinit var sessionManager: SessionManager
    lateinit var binding: RecyclerviewbindingBinding
    var userList = ObservableArrayList<Users>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(this)

        supportActionBar!!.title = getString(R.string.app_home)

        if (!sessionManager.isLoggedIn) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        binding = DataBindingUtil.setContentView(this, R.layout.recyclerviewbinding)
        binding.view = this
        binding.list = userList
        binding.presenter = BindingPresenter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        getUsers()
    }

    private fun getUsers() {
        val retrofit = Retrofit.Builder()
                .baseUrl(ApiInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val apiInterface = retrofit.create(ApiInterface::class.java)
        val getUsers = apiInterface.getUsers()

        getUsers.enqueue(object : Callback<List<Users>> {
            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                //can create custom error message handling to show meaningfull message to client
                Toast.makeText(this@MainActivity, "Unavailable", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                if (response.isSuccessful) {
                    val users: List<Users> = response.body()!!
                    println("error = " + response.errorBody())
                    userList.clear()
                    userList.addAll(users)
                }

            }

        })
    }

    override fun clickHandler(): ClickHandler<Any>? {
        return ClickHandler { users ->
            startActivity(Intent(this@MainActivity, DetailActivity::class.java).apply {
                putExtra(PARAM_DETAIL_USERS, Gson().toJson(users))
            })
        }
    }

    override fun longClickHandler(): LongClickHandler<Any>? {
        return null
    }

    override fun itemViewBinder(): ItemBinder<Any>? {
        return ItemBinderBase<Any>(BR.users, R.layout.list_item_users)
    }

    companion object {
        const val PARAM_DETAIL_USERS = "detail_users"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_more, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item!!.itemId
        val menuItemView = findViewById<View>(R.id.action_more)
        if (id == R.id.action_more) {
            val wrapper = ContextThemeWrapper(this, R.style.AppThemePopUpCustomStyle)
            val popupMenu = PopupMenu(wrapper, menuItemView)
            //add list
            popupMenu.inflate(R.menu.menu_logout)
            val menuLogout = popupMenu.menu.findItem(R.id.action_logout)
            menuLogout.setOnMenuItemClickListener {
                sessionManager.logout()
                return@setOnMenuItemClickListener false
            }

            popupMenu.show()

        }
        return super.onOptionsItemSelected(item)
    }

}
