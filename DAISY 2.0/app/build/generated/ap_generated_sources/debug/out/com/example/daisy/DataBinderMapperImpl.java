package com.example.daisy;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.example.daisy.databinding.ActivityLearnAlphabetBindingImpl;
import com.example.daisy.databinding.ActivityLearnNumbersBindingImpl;
import com.example.daisy.databinding.ActivityLearnWordsBindingImpl;
import com.example.daisy.databinding.ActivitySignBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYLEARNALPHABET = 1;

  private static final int LAYOUT_ACTIVITYLEARNNUMBERS = 2;

  private static final int LAYOUT_ACTIVITYLEARNWORDS = 3;

  private static final int LAYOUT_ACTIVITYSIGN = 4;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(4);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.daisy.R.layout.activity_learn_alphabet, LAYOUT_ACTIVITYLEARNALPHABET);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.daisy.R.layout.activity_learn_numbers, LAYOUT_ACTIVITYLEARNNUMBERS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.daisy.R.layout.activity_learn_words, LAYOUT_ACTIVITYLEARNWORDS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.daisy.R.layout.activity_sign, LAYOUT_ACTIVITYSIGN);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYLEARNALPHABET: {
          if ("layout/activity_learn_alphabet_0".equals(tag)) {
            return new ActivityLearnAlphabetBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_learn_alphabet is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYLEARNNUMBERS: {
          if ("layout/activity_learn_numbers_0".equals(tag)) {
            return new ActivityLearnNumbersBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_learn_numbers is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYLEARNWORDS: {
          if ("layout/activity_learn_words_0".equals(tag)) {
            return new ActivityLearnWordsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_learn_words is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSIGN: {
          if ("layout/activity_sign_0".equals(tag)) {
            return new ActivitySignBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_sign is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(1);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(4);

    static {
      sKeys.put("layout/activity_learn_alphabet_0", com.example.daisy.R.layout.activity_learn_alphabet);
      sKeys.put("layout/activity_learn_numbers_0", com.example.daisy.R.layout.activity_learn_numbers);
      sKeys.put("layout/activity_learn_words_0", com.example.daisy.R.layout.activity_learn_words);
      sKeys.put("layout/activity_sign_0", com.example.daisy.R.layout.activity_sign);
    }
  }
}
