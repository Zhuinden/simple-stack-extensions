package com.zhuinden.simplestackextensions.fragments;

import com.zhuinden.simplestack.StateChange;

import java.util.List;

import javax.annotation.Nonnull;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * The default fragment state changer uses `attach` and `detach` to navigate between fragments.
 *
 * Also sets the {@link DefaultFragmentKey} in the arguments using {@link DefaultFragmentKey#ARGS_KEY} as the argument key.
 */
public class DefaultFragmentStateChanger {
    private final FragmentManager fragmentManager;
    private final int containerId;

    /**
     * Constructor.
     *
     * @param fragmentManager the fragment manager
     * @param containerId the container ID in which fragments are swapped
     */
    public DefaultFragmentStateChanger(@Nonnull FragmentManager fragmentManager, @IdRes int containerId) {
        this.fragmentManager = fragmentManager;
        this.containerId = containerId;
    }

    /**
     * Forward navigation handler. Horizontal translation animation by default.
     *
     * @param fragmentTransaction the fragment transaction
     * @param stateChange the state change
     */
    protected void onForwardNavigation(@Nonnull FragmentTransaction fragmentTransaction, @Nonnull StateChange stateChange) {
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left, R.anim.slide_in_from_right, R.anim.slide_out_to_left);
    }

    /**
     * Backward navigation handler. Horizontal translation animation by default.
     *
     * @param fragmentTransaction the fragment transaction
     * @param stateChange the state change
     */
    protected void onBackwardNavigation(@Nonnull FragmentTransaction fragmentTransaction, @Nonnull StateChange stateChange) {
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_left, R.anim.slide_out_to_right, R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }

    /**
     * Replace navigation handler. Open transition animation by default.
     *
     * @param fragmentTransaction the fragment transaction
     * @param stateChange the state change
     */
    protected void onReplaceNavigation(@Nonnull FragmentTransaction fragmentTransaction, @Nonnull StateChange stateChange) {
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
    }

    /**
     * Decide if the fragment is currently added, but not showing.
     *
     * @param fragment the fragment
     * @return if the fragment is not showing
     */
    protected boolean isNotShowing(@Nonnull Fragment fragment) {
        return fragment.isDetached();
    }

    /**
     * Start showing the fragment that is added but not showing.
     *
     * @param fragmentTransaction the fragment transaction
     * @param fragment the fragment
     */
    protected void startShowing(@Nonnull FragmentTransaction fragmentTransaction, @Nonnull Fragment fragment) {
        fragmentTransaction.attach(fragment); // show top fragment if already exists
    }

    /**
     * Stop showing the fragment that is added, showing, but should not be showing.
     *
     * @param fragmentTransaction the fragment transaction
     * @param fragment the fragment
     */
    protected void stopShowing(@Nonnull FragmentTransaction fragmentTransaction, @Nonnull Fragment fragment) {
        fragmentTransaction.detach(fragment); // destroy view of fragment not top
    }

    /**
     * Handles the transition from one state to another, swapping fragments.
     *
     * @param stateChange the state change
     */
    public final void handleStateChange(@Nonnull StateChange stateChange) {
        fragmentManager.executePendingTransactions(); // two synchronous immediate fragment transactions can overlap.

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().disallowAddToBackStack();
        if (stateChange.getDirection() == StateChange.FORWARD) {
            onForwardNavigation(fragmentTransaction, stateChange);
        } else if (stateChange.getDirection() == StateChange.BACKWARD) {
            onBackwardNavigation(fragmentTransaction, stateChange);
        } else { // REPLACE
            onReplaceNavigation(fragmentTransaction, stateChange);
        }

        List<DefaultFragmentKey> previousKeys = stateChange.getPreviousKeys();
        List<DefaultFragmentKey> newKeys = stateChange.getNewKeys();
        for (DefaultFragmentKey oldKey : previousKeys) {
            Fragment fragment = fragmentManager.findFragmentByTag(oldKey.getFragmentTag());
            if (fragment != null) {
                if (!newKeys.contains(oldKey)) {
                    fragmentTransaction.remove(fragment); // remove fragments not in backstack
                } else if (!isNotShowing(fragment)) {
                    stopShowing(fragmentTransaction, fragment);
                }
            }
        }
        for (DefaultFragmentKey newKey : newKeys) {
            Fragment fragment = fragmentManager.findFragmentByTag(newKey.getFragmentTag());
            if (newKey.equals(stateChange.topNewKey())) {
                if (fragment != null) {
                    if (fragment.isRemoving()) { // fragments are quirky, they die asynchronously. Ignore if they're still there.
                        fragmentTransaction.replace(containerId, newKey.createFragment(), newKey.getFragmentTag());
                    } else if (isNotShowing(fragment)) {
                        startShowing(fragmentTransaction, fragment);
                    }
                } else {
                    fragment = newKey.createFragment(); // create and add new top if did not exist
                    fragmentTransaction.add(containerId, fragment, newKey.getFragmentTag());
                }
            } else {
                if (fragment != null && !isNotShowing(fragment)) {
                    stopShowing(fragmentTransaction, fragment);
                }
            }
        }
        fragmentTransaction.commitAllowingStateLoss();
    }
}
