// Generated by view binder compiler. Do not edit!
package com.example.daisy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.daisy.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityEditProfileBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final EditText editBio;

  @NonNull
  public final EditText editName;

  @NonNull
  public final Button saveButton;

  private ActivityEditProfileBinding(@NonNull LinearLayout rootView, @NonNull EditText editBio,
      @NonNull EditText editName, @NonNull Button saveButton) {
    this.rootView = rootView;
    this.editBio = editBio;
    this.editName = editName;
    this.saveButton = saveButton;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityEditProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityEditProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_edit_profile, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityEditProfileBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.edit_bio;
      EditText editBio = ViewBindings.findChildViewById(rootView, id);
      if (editBio == null) {
        break missingId;
      }

      id = R.id.edit_name;
      EditText editName = ViewBindings.findChildViewById(rootView, id);
      if (editName == null) {
        break missingId;
      }

      id = R.id.save_button;
      Button saveButton = ViewBindings.findChildViewById(rootView, id);
      if (saveButton == null) {
        break missingId;
      }

      return new ActivityEditProfileBinding((LinearLayout) rootView, editBio, editName, saveButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}