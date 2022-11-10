package arsip.imaji.id.adapter

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import arsip.imaji.id.R
import arsip.imaji.id.model.DataObject
import arsip.imaji.id.view.AllProductActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

class AdapterMore(context: Context, items: ArrayList<DataObject>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<DataObject> = ArrayList()
    private var context : Context? = null

    init {
        this.items = items
        this.context = context
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 5) {
            VIEW_TYPE_MORE
        } else {
            VIEW_TYPE_ITEM
        }
    }

    inner class ViewHolderItem(itemView: View): RecyclerView.ViewHolder(itemView) {

        var image1: ImageView
        var firstName: TextView
        var lastName: TextView
        var age: TextView

        init {
                image1 = itemView.findViewById(R.id.imgProduct) as ImageView
                firstName = itemView.findViewById(R.id.firstName) as TextView
                lastName = itemView.findViewById(R.id.lastName) as TextView
                age = itemView.findViewById(R.id.age) as TextView

        }

        fun bind(data : DataObject) {
            val displayMetrics = DisplayMetrics()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                context?.display?.getRealMetrics(displayMetrics)
            }
            val circularProgressDrawable = CircularProgressDrawable(context!!)
            image1.viewTreeObserver?.addOnGlobalLayoutListener {
                context?.let {
                    Glide.with(it)
                        .load(data.pathPhoto)
                        .placeholder(circularProgressDrawable)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(circularProgressDrawable)
                        .into(object : CustomTarget<Drawable>(image1.width, 1) {
                            override fun onLoadCleared(placeholder: Drawable?) {
                            }

                            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                                image1.setImageDrawable(resource)
                            }
                        })
                }
            }
        }

    }

    internal inner class ViewHolderMore(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var lyNavMore: LinearLayout = itemView.findViewById(R.id.lyNavMoreProduct)
        fun bind() {
            lyNavMore.setOnClickListener {
                context?.startActivity(Intent(context, AllProductActivity::class.java))
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_MORE) {
            return ViewHolderMore(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.adapter_more_product, parent, false)
            )
        }
        return ViewHolderItem(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.items, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (items.size > 6) 6 else items.size
    }

    companion object {
        const val VIEW_TYPE_ITEM = 1
        const val VIEW_TYPE_MORE = 2
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position==5){
            if (holder is ViewHolderMore) {
                holder.bind()
            }
        }else{
            if (holder is ViewHolderItem) {
                val data = items[position]
                holder.bind(data)
            }
        }
    }
}