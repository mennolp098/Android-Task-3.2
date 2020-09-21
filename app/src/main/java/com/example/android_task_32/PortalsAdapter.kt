package com.example.android_task_32


import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class PortalAdapter (private val portals: List<Portal>) : RecyclerView.Adapter<PortalAdapter.ViewHolder>(){

    private var customTabHelper: CustomTabHelper = CustomTabHelper()
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun databind(portal: Portal) {
            itemView.findViewById<TextView>(R.id.tvTitle).text = portal.title
            itemView.findViewById<TextView>(R.id.tvUrl).text = portal.url
            itemView.setOnClickListener {
                val builder = CustomTabsIntent.Builder()

                // modify toolbar color
                builder.setToolbarColor(ContextCompat.getColor(itemView.context, R.color.colorPrimary))

                // add share button to overflow menu
                builder.addDefaultShareMenuItem()

                builder.setShowTitle(true)

                // animation for enter and exit of tab
                builder.setStartAnimations(itemView.context, android.R.anim.fade_in, android.R.anim.fade_out)
                builder.setExitAnimations(itemView.context, android.R.anim.fade_in, android.R.anim.fade_out)

                val customTabsIntent = builder.build()
                // check is chrom available
                val packageName = customTabHelper.getPackageNameToUse(itemView.context, CustomTabHelper.STABLE_PACKAGE)

                customTabsIntent.intent.setPackage(packageName)
                customTabsIntent.launchUrl(itemView.context, Uri.parse(portal.url))

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_portal, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return portals.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(portals[position])
    }
}