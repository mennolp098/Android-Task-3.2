package com.example.android_task_32

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_hub.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HubFragment : Fragment() {

    private val portals = arrayListOf<Portal>(Portal("Google", "http://google.com"),Portal("Youtube", "http://youtube.com"),Portal("StackOverflow", "http://stackoverflow.com"))
    private val portalAdapter = PortalAdapter(portals)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hub, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvPortals = view.findViewById<RecyclerView>(R.id.rvPortals)
        rvPortals.layoutManager = StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL)
        //rvPortals.addItemDecoration(DividerItemDecoration(view.context, DividerItemDecoration.HORIZONTAL))
        rvPortals.adapter = portalAdapter

        portalAdapter.notifyDataSetChanged()

        val newPortal = arguments?.getParcelable<Portal>("portal")
        if (newPortal != null) {
            Log.d("[HubFragment]", "We gots on the portal");
            portals.add(newPortal)
            portalAdapter.notifyDataSetChanged()
            Log.d("[HubFragment]", portals.size.toString());
            Log.d("[HubFragment]", portalAdapter.itemCount.toString());
        }
    }
}