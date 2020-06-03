package com.zhuinden.simplestackextensions.fragments;

import android.os.Bundle;
import android.os.Parcelable;

import androidx.fragment.app.Fragment;

/**
 * Default fragment key for the {@link DefaultFragmentStateChanger}.
 */
public abstract class DefaultFragmentKey implements Parcelable {
    /**
     * Argument key used in the arguments bundle to store and retrieve the key from the fragment.
     */
    public static final String ARGS_KEY = "___SIMPLE_STACK_FRAGMENT_ARGUMENTS_KEY_TAG___";

    /**
     * Return the fragment tag. Implemented as FQN of the key by default.
     *
     * Should be consistent across process death.
     *
     * In data classes, should be generally overridden to return `toString()`.
     *
     * @return the fragment tag
     */
    public String getFragmentTag() {
        return getClass().getName();
    }

    /**
     * Instantiate a new instance of the Fragment.
     *
     * @return the new instance of fragment
     */
    protected abstract Fragment instantiateFragment();

    /**
     * Create instance of the fragment, with the key set in its arguments.
     *
     * @return the new fragment instance
     */
    public final Fragment createFragment() {
        Fragment fragment = instantiateFragment();
        Bundle args = fragment.getArguments();
        if(args == null) {
            args = new Bundle();
            fragment.setArguments(args);
        }
        args.putParcelable(ARGS_KEY, this);
        return fragment;
    }
}
