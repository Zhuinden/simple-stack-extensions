package com.zhuinden.simplestackextensions.fragments;

import androidx.annotation.LayoutRes;
import androidx.fragment.app.Fragment;

/**
 * A default base fragment that contains a method to get the key from the arguments.
 */
public abstract class KeyedFragment extends Fragment {
    /**
     * Required no-arg fragment constructor.
     */
    public KeyedFragment() {
        super();
    }

    /**
     * Optional fragment constructor that takes layout.
     *
     * @param layoutRes the layout res
     */
    public KeyedFragment(@LayoutRes int layoutRes) {
        super(layoutRes);
    }

    /**
     * Returns the key added to the arguments of the Fragment.
     *
     * @param <T> the type of the key
     * @return the key
     */
    public final <T extends DefaultFragmentKey> T getKey() {
        return requireArguments().getParcelable(DefaultFragmentKey.ARGS_KEY);
    }
}