package com.github.hyunwoo.todo.android

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.hyunwoo.todo.android.databinding.ItemLaunchBinding
import com.github.hyunwoo.todo.entity.RocketLaunch

class LaunchInfoAdapter(
    private var launches: List<RocketLaunch>
) : RecyclerView.Adapter<LaunchInfoAdapter.LaunchViewHolder>() {
    class LaunchViewHolder(
        private val binding: ItemLaunchBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(launch: RocketLaunch) {
            with(binding) {
                missionName.text =
                    context.getString(R.string.mission_name_field, launch.missionName)
                launchYear.text =
                    context.getString(R.string.launch_year_field, launch.launchYear.toString())
                details.text = context.getString(R.string.details_field, launch.details)
                val launchSuccess = launch.launchSuccess
                val successText = binding.launchSuccess
                if (launchSuccess != null) {
                    if (launchSuccess) {
                        successText.set(R.string.successful, R.color.colorSuccessful)
                    } else {
                        successText.set(R.string.unsuccessful, R.color.colorUnsuccessful)
                    }
                } else {
                    successText.set(R.string.no_data, R.color.colorNoData)
                }
            }
        }

        private fun TextView.set(@StringRes text: Int, @ColorRes resId: Int) {
            this.text = context.getString(text)
            this.setTextColor(ContextCompat.getColor(context, resId))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        val binding = ItemLaunchBinding.inflate(LayoutInflater.from(parent.context))
        return LaunchViewHolder(binding, parent.context)
    }

    override fun getItemCount() = launches.size

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bind(launches[position])
    }

    fun replaceList(newList: List<RocketLaunch>) {
        launches = newList.toList()
        notifyDataSetChanged()
    }
}
