// Generated by view binder compiler. Do not edit!
package com.example.daisy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.daisy.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemDashboardBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final ImageView itemIcon;

  @NonNull
  public final TextView itemTitle;

  private ItemDashboardBinding(@NonNull CardView rootView, @NonNull ImageView itemIcon,
      @NonNull TextView itemTitle) {
    this.rootView = rootView;
    this.itemIcon = itemIcon;
    this.itemTitle = itemTitle;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemDashboardBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemDashboardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_dashboard, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemDashboardBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.item_icon;
      ImageView itemIcon = ViewBindings.findChildViewById(rootView, id);
      if (itemIcon == null) {
        break missingId;
      }

      id = R.id.item_title;
      TextView itemTitle = ViewBindings.findChildViewById(rootView, id);
      if (itemTitle == null) {
        break missingId;
      }

      return new ItemDashboardBinding((CardView) rootView, itemIcon, itemTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
