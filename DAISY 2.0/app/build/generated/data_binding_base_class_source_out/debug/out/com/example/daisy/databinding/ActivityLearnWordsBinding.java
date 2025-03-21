// Generated by data binding compiler. Do not edit!
package com.example.daisy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.example.daisy.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityLearnWordsBinding extends ViewDataBinding {
  @NonNull
  public final ImageButton btnBack;

  @NonNull
  public final Button btnBye;

  @NonNull
  public final Button btnHello;

  @NonNull
  public final Button btnNo;

  @NonNull
  public final Button btnThankYou;

  @NonNull
  public final Button btnYes;

  protected ActivityLearnWordsBinding(Object _bindingComponent, View _root, int _localFieldCount,
      ImageButton btnBack, Button btnBye, Button btnHello, Button btnNo, Button btnThankYou,
      Button btnYes) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnBack = btnBack;
    this.btnBye = btnBye;
    this.btnHello = btnHello;
    this.btnNo = btnNo;
    this.btnThankYou = btnThankYou;
    this.btnYes = btnYes;
  }

  @NonNull
  public static ActivityLearnWordsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_learn_words, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityLearnWordsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityLearnWordsBinding>inflateInternal(inflater, R.layout.activity_learn_words, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityLearnWordsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_learn_words, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityLearnWordsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityLearnWordsBinding>inflateInternal(inflater, R.layout.activity_learn_words, null, false, component);
  }

  public static ActivityLearnWordsBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static ActivityLearnWordsBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityLearnWordsBinding)bind(component, view, R.layout.activity_learn_words);
  }
}
