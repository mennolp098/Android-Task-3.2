package com.example.android_task_32

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NewPortalFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_portal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_add_portal).setOnClickListener {
            addPortal(view)
        }
    }

    private fun addPortal(view: View) {
        val title = view.findViewById<EditText>(R.id.et_title).text.toString()
        val url = view.findViewById<EditText>(R.id.et_url).text.toString()

        if(title.isEmpty() || url.isEmpty() || url.contentEquals(getString(R.string.url_start_help))) {
            Toast.makeText(this.context, R.string.empty_field, Toast.LENGTH_SHORT).show();
            return
        }

        val portal = Portal(title, url)
        val bundle = bundleOf("portal" to portal)
        findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment, bundle)
    }
}