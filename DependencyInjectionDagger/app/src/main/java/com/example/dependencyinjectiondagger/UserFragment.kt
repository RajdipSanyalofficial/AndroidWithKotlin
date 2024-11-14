package com.example.dependencyinjectiondagger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class UserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Here you can set user data, for demonstration let's set static data
        val userTextView = view.findViewById<TextView>(R.id.tv_user_data)
        userTextView.text = "User Data: Rajdip Sanyal, Age: 26, Location: West Bengal"
    }
}

