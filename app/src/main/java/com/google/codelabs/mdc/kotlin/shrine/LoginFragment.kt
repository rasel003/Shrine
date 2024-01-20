package com.google.codelabs.mdc.kotlin.shrine

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout

/**
 * Fragment representing the login screen for Shrine.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.shr_login_fragment, container, false)

        // Set an error if the password is less than 8 characters.
        view.findViewById<Button>(R.id.next_button).setOnClickListener {
            if (!isPasswordValid(view.findViewById<EditText>(R.id.password_edit_text).text)) {
                view.findViewById<TextInputLayout>(R.id.password_text_input).error = getString(R.string.shr_error_password)
            } else {
                view.findViewById<TextInputLayout>(R.id.password_text_input).error = null // Clear the error
                (activity as NavigationHost).navigateTo(ProductGridFragment(), false) // Navigate to the next Fragment
            }
        }

        // Clear the error once more than 8 characters are typed.
        view.findViewById<EditText>(R.id.password_edit_text).setOnKeyListener { _, _, _ ->
            if (isPasswordValid(view.findViewById<EditText>(R.id.password_edit_text).text)) {
                view.findViewById<TextInputLayout>(R.id.password_text_input).error = null //Clear the error
            }
            false
        }
        return view
    }

    /*
        In reality, this will have more complex logic including, but not limited to, actual
        authentication of the username and password.
     */
    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }
}
