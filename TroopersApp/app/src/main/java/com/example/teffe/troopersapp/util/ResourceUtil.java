package com.example.teffe.troopersapp.util;

import com.example.teffe.troopersapp.R;
import com.example.teffe.troopersapp.model.Affiliation;

/**
 * Created by teffe on 18/11/2017.
 */

public class ResourceUtil {
    public static int getResourceBasedOnAffiliation(Affiliation affiliation) {
        switch (affiliation) {
            case GALACTIC_REPUBLIC:
                return R.drawable.galactic_republic;
            case GALACTIC_EMPIRE:
                return R.drawable.galactic_empire;
            case FIRST_ORDER:
                return R.drawable.first_order;
            default:
                return 0;
        }
    }
}
