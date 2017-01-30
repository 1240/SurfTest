package com.l24o.surftest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var lines: List<String>
    private lateinit var adapter: SurfTestTaskAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.switchView -> {
                adapter.isNumbers = !adapter.isNumbers
                adapter.notifyDataSetChanged()
                return true
            }
            else ->
                return super.onOptionsItemSelected(item)
        }
    }

    public override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        adapter.isNumbers = savedInstanceState.getBoolean(Constants.SHOW_NUMBERS_KEY)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(Constants.SHOW_NUMBERS_KEY, adapter.isNumbers)
        super.onSaveInstanceState(outState)
    }

    fun initViews() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        var content: String? = null
        assets.open("input.txt").bufferedReader(Charsets.UTF_8).use { content = it.readText() }
        lines = content!!.replace("\"", "").split("\r\n").toList()
        adapter = SurfTestTaskAdapter(lines)
        recyclerView.adapter = adapter
    }
}
