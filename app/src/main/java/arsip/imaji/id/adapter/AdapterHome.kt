package arsip.imaji.id.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import arsip.imaji.id.R
import arsip.imaji.id.view.AboutActivity
import arsip.imaji.id.view.EnangementActivity
import arsip.imaji.id.view.GraduationActivity
import arsip.imaji.id.view.WeddingActivity

class AdapterHome(var context: Context) : RecyclerView.Adapter<AdapterHome.MyAdapter>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return MyAdapter(view)
    }

    override fun onBindViewHolder(holder: MyAdapter, position: Int) {
        if (position == 0) {
            holder.image1.setImageResource(R.drawable.ic_round_local_florist_24)
            holder.back.setBackgroundColor(Color.parseColor("#E6E53935"))
            holder.text.text = "Enangement"
            holder.back.setOnClickListener {
                context.startActivity(Intent(context, EnangementActivity::class.java))
            }
        }
        if (position == 1) {
            holder.image1.setImageResource(R.drawable.ic_round_local_florist_24)
            holder.back.setBackgroundColor(Color.parseColor("#F236883A"))
            holder.text.text = "Graduation"
            holder.back.setOnClickListener {
                context.startActivity(Intent(context, GraduationActivity::class.java))
            }
        }
        if (position == 2) {
            holder.image1.setImageResource(R.drawable.ic_round_local_florist_24)
            holder.back.setBackgroundColor(Color.parseColor("#F2AF4576"))
            holder.text.text = "Prewed"
            holder.back.setOnClickListener {
                context.startActivity(Intent(context, WeddingActivity::class.java))
            }
        }
        if (position == 3) {
            holder.image1.setImageResource(R.drawable.ic_round_local_florist_24)
            holder.back.setBackgroundColor(Color.parseColor("#F2EEAA45"))
            holder.text.text = "About"
            holder.back.setOnClickListener {
                context.startActivity(Intent(context, AboutActivity::class.java))
            }
        }
    }

    override fun getItemCount(): Int {
        return 4
    }

    inner class MyAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var image1: ImageView
        var text: TextView
        var back: RelativeLayout

        init {
            image = itemView.findViewById(R.id.image)
            image1 = itemView.findViewById(R.id.image1)
            text = itemView.findViewById(R.id.text)
            back = itemView.findViewById(R.id.back)
        }
    }
}