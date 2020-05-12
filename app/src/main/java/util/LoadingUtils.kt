package util

import android.content.Context
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.covid_19.R

fun showLoading(context: Context, swipeRefreshLayout:SwipeRefreshLayout){
    swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(context, R.color.royal))

    swipeRefreshLayout.isEnabled = true
    swipeRefreshLayout.isRefreshing = true
}