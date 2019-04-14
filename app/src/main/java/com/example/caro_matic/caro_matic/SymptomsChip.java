package com.example.caro_matic.caro_matic;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.tylersuehr.chips.Chip;

public class SymptomsChip extends Chip {
    String title;
    String subtitle;

    public SymptomsChip(String title, String subtitle){
        this.title = title;
        this.subtitle = subtitle;
    }
    @NonNull
    @Override
    public String getTitle() {
        return title;
    }

    @Nullable
    @Override
    public Object getId() {
        return null;
    }

    @Nullable
    @Override
    public String getSubtitle() {
        return subtitle;
    }

    @Nullable
    @Override
    public Uri getAvatarUri() {
        return null;
    }

    @Nullable
    @Override
    public Drawable getAvatarDrawable() {
        return null;
    }
}
