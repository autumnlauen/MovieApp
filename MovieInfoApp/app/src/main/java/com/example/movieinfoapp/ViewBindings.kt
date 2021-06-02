package com.example.movieinfoapp

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun <T : ViewBinding> Fragment.viewBindings(
    bind: (View) -> T
) = object : ReadOnlyProperty<Fragment, T> {

    @Suppress("UNCHECKED_CAST")
    override operator fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val key = property.name.hashCode()

        return contentView().getTag(key) as? T?
            ?: bind(contentView())
                .also { contentView().setTag(key, it) }
    }

    private fun Fragment.contentView(): View = view
        ?: error("ViewBinding attempt on null view")
}
