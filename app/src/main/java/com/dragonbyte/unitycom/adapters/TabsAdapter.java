package com.dragonbyte.unitycom.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.dragonbyte.unitycom.fragments.ProfileFragment;
import com.dragonbyte.unitycom.fragments.SearchFragment;

/**
 * Created by Fiarie on 3/18/2017.
 */

public class TabsAdapter extends FragmentStatePagerAdapter {

    /*
       fragmentState = 1 => SearchFragment
       fragmentState = 2 => MyAccountFragment
     */

    private int fragmentState;
    public TabsAdapter (FragmentManager fm, int fragmentState) {
        super(fm);
        Log.e("here", "created");
        this.fragmentState = fragmentState;
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        Log.e("Fragment state", String.valueOf(fragmentState));
        if (fragmentState == 1) {
            Log.e("Fragment state", String.valueOf(fragmentState));
            fragment = new SearchFragment();
            return fragment;
        } else if (fragmentState == 2) {
            fragment = new ProfileFragment();
            return fragment;
        }
        else return null;

    }
    public void setBoard(int  value) {
        fragmentState = value;
    }
    @Override
    public int getCount() {
        return 1;
    }
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}