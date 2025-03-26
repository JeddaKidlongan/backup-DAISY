package com.example.daisy;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.example.daisy.databinding.ActivityLettersBindingImpl;
import com.example.daisy.databinding.ActivityLettersQuizBindingImpl;
import com.example.daisy.databinding.ActivityMain2BindingImpl;
import com.example.daisy.databinding.ActivityNumbersBindingImpl;
import com.example.daisy.databinding.ActivityNumbersQuizBindingImpl;
import com.example.daisy.databinding.ActivityQuizBindingImpl;
import com.example.daisy.databinding.ActivitySignBindingImpl;
import com.example.daisy.databinding.ActivityWordsBindingImpl;
import com.example.daisy.databinding.ActivityWordsQuizBindingImpl;
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
  private static final int LAYOUT_ACTIVITYLETTERS = 1;

  private static final int LAYOUT_ACTIVITYLETTERSQUIZ = 2;

  private static final int LAYOUT_ACTIVITYMAIN2 = 3;

  private static final int LAYOUT_ACTIVITYNUMBERS = 4;

  private static final int LAYOUT_ACTIVITYNUMBERSQUIZ = 5;

  private static final int LAYOUT_ACTIVITYQUIZ = 6;

  private static final int LAYOUT_ACTIVITYSIGN = 7;

  private static final int LAYOUT_ACTIVITYWORDS = 8;

  private static final int LAYOUT_ACTIVITYWORDSQUIZ = 9;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(9);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.daisy.R.layout.activity_letters, LAYOUT_ACTIVITYLETTERS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.daisy.R.layout.activity_letters_quiz, LAYOUT_ACTIVITYLETTERSQUIZ);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.daisy.R.layout.activity_main_2, LAYOUT_ACTIVITYMAIN2);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.daisy.R.layout.activity_numbers, LAYOUT_ACTIVITYNUMBERS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.daisy.R.layout.activity_numbers_quiz, LAYOUT_ACTIVITYNUMBERSQUIZ);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.daisy.R.layout.activity_quiz, LAYOUT_ACTIVITYQUIZ);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.daisy.R.layout.activity_sign, LAYOUT_ACTIVITYSIGN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.daisy.R.layout.activity_words, LAYOUT_ACTIVITYWORDS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.daisy.R.layout.activity_words_quiz, LAYOUT_ACTIVITYWORDSQUIZ);
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
        case  LAYOUT_ACTIVITYLETTERS: {
          if ("layout/activity_letters_0".equals(tag)) {
            return new ActivityLettersBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_letters is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYLETTERSQUIZ: {
          if ("layout/activity_letters_quiz_0".equals(tag)) {
            return new ActivityLettersQuizBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_letters_quiz is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYMAIN2: {
          if ("layout/activity_main_2_0".equals(tag)) {
            return new ActivityMain2BindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_main_2 is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYNUMBERS: {
          if ("layout/activity_numbers_0".equals(tag)) {
            return new ActivityNumbersBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_numbers is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYNUMBERSQUIZ: {
          if ("layout/activity_numbers_quiz_0".equals(tag)) {
            return new ActivityNumbersQuizBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_numbers_quiz is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYQUIZ: {
          if ("layout/activity_quiz_0".equals(tag)) {
            return new ActivityQuizBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_quiz is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSIGN: {
          if ("layout/activity_sign_0".equals(tag)) {
            return new ActivitySignBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_sign is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYWORDS: {
          if ("layout/activity_words_0".equals(tag)) {
            return new ActivityWordsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_words is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYWORDSQUIZ: {
          if ("layout/activity_words_quiz_0".equals(tag)) {
            return new ActivityWordsQuizBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_words_quiz is invalid. Received: " + tag);
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
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(9);

    static {
      sKeys.put("layout/activity_letters_0", com.example.daisy.R.layout.activity_letters);
      sKeys.put("layout/activity_letters_quiz_0", com.example.daisy.R.layout.activity_letters_quiz);
      sKeys.put("layout/activity_main_2_0", com.example.daisy.R.layout.activity_main_2);
      sKeys.put("layout/activity_numbers_0", com.example.daisy.R.layout.activity_numbers);
      sKeys.put("layout/activity_numbers_quiz_0", com.example.daisy.R.layout.activity_numbers_quiz);
      sKeys.put("layout/activity_quiz_0", com.example.daisy.R.layout.activity_quiz);
      sKeys.put("layout/activity_sign_0", com.example.daisy.R.layout.activity_sign);
      sKeys.put("layout/activity_words_0", com.example.daisy.R.layout.activity_words);
      sKeys.put("layout/activity_words_quiz_0", com.example.daisy.R.layout.activity_words_quiz);
    }
  }
}
