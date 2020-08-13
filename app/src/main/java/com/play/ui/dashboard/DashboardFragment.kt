package com.play.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.play.PlayApplication
import com.play.R
import com.play.data.model.Story
import com.play.ui.base.BaseFragment
import com.play.utils.CommonUtils
import com.play.utils.Status
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlin.reflect.KProperty

class DashboardFragment : BaseFragment(), DashboardAdapter.StoryClickListener {

    private var dashboardViewModel : DashboardViewModel by activityViewModels()
    private lateinit var adapter: DashboardAdapter
    var mPageCount = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupObserver()
        dashboardViewModel.stories(mPageCount)
    }

    private fun setupObserver() {
        dashboardViewModel.getStories.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    if (mPageCount == 0)
                        hideLoading()

                    it.data?.let { stories -> renderList(stories) }
                    recycler_view.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    if (mPageCount == 0)
                        showLoading();
                }
                Status.ERROR -> {
                    it.message?.let { it1 -> showSnackBar(it1) }
                }
            }
        })
    }

    private fun renderList(stories: List<Story>) {
        adapter.addItems(stories)
        adapter.notifyDataSetChanged()
    }

    private fun setupUI() {
        recycler_view.layoutManager = LinearLayoutManager(PlayApplication.applicationContext())
        adapter = DashboardAdapter(arrayListOf(), itemClickListener = this )
        recycler_view.addItemDecoration(
            DividerItemDecoration(
                recycler_view.context,
                (recycler_view.layoutManager as LinearLayoutManager).orientation
            )
        )
        recycler_view.adapter = adapter
        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    dashboardViewModel.stories(++mPageCount)
                }
            }
        })
    }

    override fun onCellClickListener(story: Story) {
        dashboardViewModel.storeStory(story);
        requireActivity().feed_view_pager.currentItem = 2;
    }
}

private operator fun Any.setValue(
    dashboardFragment: DashboardFragment,
    property: KProperty<*>,
    dashboardViewModel: DashboardViewModel
) {
    TODO("Not yet implemented")
}

