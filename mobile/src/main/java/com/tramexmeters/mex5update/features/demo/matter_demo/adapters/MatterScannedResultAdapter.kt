package com.tramexmeters.mex5update.features.demo.matter_demo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.swipe.SwipeLayout
import com.tramexmeters.mex5update.R
import com.tramexmeters.mex5update.databinding.MatterScannedListItemBinding
import com.tramexmeters.mex5update.features.demo.matter_demo.model.MatterScannedResultModel
import com.tramexmeters.mex5update.features.demo.matter_demo.viewholder.MatterItemViewModel
import kotlinx.coroutines.runBlocking


class MatterScannedResultAdapter(
    private val matterList: List<MatterScannedResultModel>
) : RecyclerView.Adapter<MatterItemViewModel>() {
    private lateinit var context: Context
    private var onClickListener: OnClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatterItemViewModel {
        val binding =
            MatterScannedListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        context = parent.context
        return MatterItemViewModel(binding, parent.context)
    }

    override fun onBindViewHolder(holder: MatterItemViewModel, position: Int) {
        val matterInfo = matterList[position]
        holder.bind(matterInfo)

        if (matterInfo.isDeviceOnline) {
            holder.itemView.isEnabled = true
            holder.itemView.isClickable = true

            holder.itemView.alpha = 1.0f // Set alpha to 1 for enabled rows
        } else {
            holder.binding.imageview.setColorFilter(
                ContextCompat.getColor(context, R.color.silabs_dark_gray_icon),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
            holder.itemView.isEnabled = false
            holder.itemView.isClickable = false
            holder.itemView.alpha = 0.5f // Set alpha to 0.5 for disabled rows
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.silabs_inactive_light));
            holder.binding.cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.silabs_inactive_light));
            holder.binding.itemViewHolder.setBackgroundColor(ContextCompat.getColor(context, R.color.silabs_inactive_light));
            holder.binding.imageview.setBackgroundColor(ContextCompat.getColor(context, R.color.silabs_inactive_light));

        }

        //   holder.binding.imgDelete.setVisibility(View.GONE);
        holder.binding.swipe.setShowMode(SwipeLayout.ShowMode.PullOut);
        holder.binding.swipe.addDrag(
            SwipeLayout.DragEdge.Right,
            holder.binding.swipe.findViewById(R.id.bottom_wrapper)
        )
        //viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, viewHolder.swipeLayout.findViewById(R.id.bottom_wrapper));
        holder.binding.itemViewHolder.setOnClickListener {

            runBlocking {
                if (onClickListener != null) {
                    onClickListener!!.onClick(position, matterInfo)
                }
            }

        }
        holder.binding.imgDelete.setOnClickListener {
            runBlocking {
                if (onClickListener != null) {
                    onClickListener!!.onDelete(position, matterInfo)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return matterList.size
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        suspend fun onClick(position: Int, model: MatterScannedResultModel)
        //  suspend fun simpleCallback(position: Int, model: MatterScannedResultModel)

        suspend fun onDelete(position: Int, model: MatterScannedResultModel)
    }


}
