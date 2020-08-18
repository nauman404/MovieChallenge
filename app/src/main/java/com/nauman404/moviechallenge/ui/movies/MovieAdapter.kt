package com.nauman404.moviechallenge.ui.movies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nauman404.data.local.models.Movie
import com.nauman404.moviechallenge.R
import kotlinx.android.synthetic.main.row_movies.view.*
import kotlinx.android.synthetic.main.row_movies.view.year_text

const val TYPE_HEADER_YEAR: Int=2
const val TYPE_ITEM_MOVIE: Int=1

class MoviesAdapter : PagedListAdapter<Any, RecyclerView.ViewHolder>(MoviesDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_ITEM_MOVIE -> MoviesViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_movies, parent, false)
            )
            TYPE_HEADER_YEAR -> YearViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_year, parent, false)
            )
            else -> MoviesViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_movies, parent, false)
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (getItem(position) is Int) {
            return TYPE_HEADER_YEAR
        } else if (getItem(position) is Movie) {
            return TYPE_ITEM_MOVIE
        }
        return -1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            TYPE_ITEM_MOVIE -> (holder as MoviesViewHolder).bind(getItem(position) as Movie)
            TYPE_HEADER_YEAR -> (holder as YearViewHolder).bind(getItem(position) as Int)
        }

    }


    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Movie) = with(itemView)
        {
            movies_constraint.setOnClickListener {
                //This can be moved to MovieFragment.
                Navigation.findNavController(itemView)
                    .navigate(MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(item))
            }
            title_text.text = item.title
            //Doing it on single line. Later it will be a separate view.
            rating_text.text = context.getString(R.string.rating_colon).plus(item.rating.toString())
            year_text.text = context.getString(R.string.release_colon).plus(item.year.toString())

        }

    }

    class YearViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Int) = with(itemView) {
            year_text.text = item.toString()
        }
    }

    companion object {
        val MoviesDiffCallback = object : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
                return if (oldItem is Movie && newItem is Movie) {
                    oldItem.title == newItem.title
                } else if (oldItem is Int && newItem is Int)
                    oldItem == newItem
                else
                    false
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
                return if (oldItem is Movie && newItem is Movie) {
                    oldItem == newItem
                } else if (oldItem is Int && newItem is Int)
                    oldItem == newItem
                else
                    false
            }
        }
    }
}