package com.zhuinden.simplestackextensionsample.features.registration

import androidx.fragment.app.Fragment
import com.zhuinden.simplestack.ScopeKey
import com.zhuinden.simplestackextensions.fragments.DefaultFragmentKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CreateLoginCredentialsKey(private val placeholder: String = "") : DefaultFragmentKey(), ScopeKey.Child {
    override fun instantiateFragment(): Fragment = CreateLoginCredentialsFragment()

    override fun getParentScopes(): List<String> = listOf("registration")
}