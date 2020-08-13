package com.play.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.play.R
import com.play.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val mainViewModel : MainViewModel by viewModels()

    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        mainAdapter = MainAdapter(this);
        mainAdapter.setCount(3);
        feed_view_pager.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
        feed_view_pager.adapter = mainAdapter;
        feed_view_pager.currentItem = 0
        feed_view_pager.isUserInputEnabled = false

        changeTheme(mainViewModel.theme())
    }

    override
    fun onBackPressed() {
        if (feed_view_pager.currentItem > 0)
            feed_view_pager.currentItem = feed_view_pager.currentItem - 1
        else super.onBackPressed()
    }
}