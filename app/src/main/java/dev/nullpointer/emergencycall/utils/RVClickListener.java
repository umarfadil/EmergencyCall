package dev.nullpointer.emergencycall.utils;

import android.view.View;

/**
 * Created by umarfadil on 11/19/17.
 */

public interface RVClickListener {
    void onClick(View view, int position);
    void onLongClick(View view, int position);
}
