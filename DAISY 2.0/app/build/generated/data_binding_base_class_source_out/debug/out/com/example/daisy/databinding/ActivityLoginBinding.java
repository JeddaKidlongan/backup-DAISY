// Generated by view binder compiler. Do not edit!
package com.example.daisy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.daisy.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoginBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatButton button;

  @NonNull
  public final TextInputEditText emailEt;

  @NonNull
  public final TextInputLayout emailLayout;

  @NonNull
  public final Guideline guideline3;

  @NonNull
  public final TextView loginExtra;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final TextInputEditText passET;

  @NonNull
  public final TextInputLayout passwordLayout;

  @NonNull
  public final TextView signInEt;

  @NonNull
  public final ImageView ssLogo;

  private ActivityLoginBinding(@NonNull ConstraintLayout rootView, @NonNull AppCompatButton button,
      @NonNull TextInputEditText emailEt, @NonNull TextInputLayout emailLayout,
      @NonNull Guideline guideline3, @NonNull TextView loginExtra, @NonNull ConstraintLayout main,
      @NonNull TextInputEditText passET, @NonNull TextInputLayout passwordLayout,
      @NonNull TextView signInEt, @NonNull ImageView ssLogo) {
    this.rootView = rootView;
    this.button = button;
    this.emailEt = emailEt;
    this.emailLayout = emailLayout;
    this.guideline3 = guideline3;
    this.loginExtra = loginExtra;
    this.main = main;
    this.passET = passET;
    this.passwordLayout = passwordLayout;
    this.signInEt = signInEt;
    this.ssLogo = ssLogo;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button;
      AppCompatButton button = ViewBindings.findChildViewById(rootView, id);
      if (button == null) {
        break missingId;
      }

      id = R.id.emailEt;
      TextInputEditText emailEt = ViewBindings.findChildViewById(rootView, id);
      if (emailEt == null) {
        break missingId;
      }

      id = R.id.emailLayout;
      TextInputLayout emailLayout = ViewBindings.findChildViewById(rootView, id);
      if (emailLayout == null) {
        break missingId;
      }

      id = R.id.guideline3;
      Guideline guideline3 = ViewBindings.findChildViewById(rootView, id);
      if (guideline3 == null) {
        break missingId;
      }

      id = R.id.loginExtra;
      TextView loginExtra = ViewBindings.findChildViewById(rootView, id);
      if (loginExtra == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.passET;
      TextInputEditText passET = ViewBindings.findChildViewById(rootView, id);
      if (passET == null) {
        break missingId;
      }

      id = R.id.passwordLayout;
      TextInputLayout passwordLayout = ViewBindings.findChildViewById(rootView, id);
      if (passwordLayout == null) {
        break missingId;
      }

      id = R.id.signInEt;
      TextView signInEt = ViewBindings.findChildViewById(rootView, id);
      if (signInEt == null) {
        break missingId;
      }

      id = R.id.ss_logo;
      ImageView ssLogo = ViewBindings.findChildViewById(rootView, id);
      if (ssLogo == null) {
        break missingId;
      }

      return new ActivityLoginBinding((ConstraintLayout) rootView, button, emailEt, emailLayout,
          guideline3, loginExtra, main, passET, passwordLayout, signInEt, ssLogo);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
