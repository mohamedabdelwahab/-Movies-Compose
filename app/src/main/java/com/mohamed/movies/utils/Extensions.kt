package com.mohamed.movies.utils

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import com.mohamed.movies.domain.model.Failure
import com.mohamed.movies.ui.model.ProgressTypes
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

fun Failure.userMessage(): String = when (this) {
    is Failure.NetworkConnection -> "No internet connection. Please check your network."
    is Failure.UnAuthorize -> "You are not authorized. Please log in again."
    is Failure.ServerError -> message ?: "Server error occurred. Try again later."
}


fun String?.alternate(alt: String = Constants.Defaults.EMPTY_STRING): String {
    return if (this?.trim().isNullOrEmpty()) alt
    else this!!.trim()
}

@Composable
fun LazyListState.OnBottomReached(
    progressTypes: ProgressTypes? = ProgressTypes.NONE,
    loadMore: () -> Unit
) {
    val shouldLoadMore = remember {
        derivedStateOf {
            val totalItemsCount = layoutInfo.totalItemsCount
            val lastVisibleItemIndex =
                layoutInfo.visibleItemsInfo.lastOrNull()?.index
                    ?: 0
            lastVisibleItemIndex >= (totalItemsCount - 2)
        }
    }
    LaunchedEffect(this, progressTypes) {
        snapshotFlow { shouldLoadMore.value }.distinctUntilChanged()
            .filter { it }.collect {
                if (it && progressTypes == ProgressTypes.NONE) {
                    loadMore()
                }
            }
    }

}
