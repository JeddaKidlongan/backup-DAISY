package com.example.daisy.databinding;
import com.example.daisy.R;
import com.example.daisy.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityLearnAlphabetBindingImpl extends ActivityLearnAlphabetBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.btnBack, 1);
        sViewsWithIds.put(R.id.lettersGrid, 2);
        sViewsWithIds.put(R.id.btnA, 3);
        sViewsWithIds.put(R.id.btnB, 4);
        sViewsWithIds.put(R.id.btnC, 5);
        sViewsWithIds.put(R.id.btnD, 6);
        sViewsWithIds.put(R.id.btnE, 7);
        sViewsWithIds.put(R.id.btnF, 8);
        sViewsWithIds.put(R.id.btnG, 9);
        sViewsWithIds.put(R.id.btnH, 10);
        sViewsWithIds.put(R.id.btnI, 11);
        sViewsWithIds.put(R.id.btnJ, 12);
        sViewsWithIds.put(R.id.btnK, 13);
        sViewsWithIds.put(R.id.btnL, 14);
        sViewsWithIds.put(R.id.btnM, 15);
        sViewsWithIds.put(R.id.btnN, 16);
        sViewsWithIds.put(R.id.btnO, 17);
        sViewsWithIds.put(R.id.btnP, 18);
        sViewsWithIds.put(R.id.btnQ, 19);
        sViewsWithIds.put(R.id.btnR, 20);
        sViewsWithIds.put(R.id.btnS, 21);
        sViewsWithIds.put(R.id.btnT, 22);
        sViewsWithIds.put(R.id.btnU, 23);
        sViewsWithIds.put(R.id.btnV, 24);
        sViewsWithIds.put(R.id.btnW, 25);
        sViewsWithIds.put(R.id.btnX, 26);
        sViewsWithIds.put(R.id.btnY, 27);
        sViewsWithIds.put(R.id.btnZ, 28);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityLearnAlphabetBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 29, sIncludes, sViewsWithIds));
    }
    private ActivityLearnAlphabetBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.google.android.material.button.MaterialButton) bindings[3]
            , (com.google.android.material.button.MaterialButton) bindings[4]
            , (android.widget.ImageButton) bindings[1]
            , (com.google.android.material.button.MaterialButton) bindings[5]
            , (com.google.android.material.button.MaterialButton) bindings[6]
            , (com.google.android.material.button.MaterialButton) bindings[7]
            , (com.google.android.material.button.MaterialButton) bindings[8]
            , (com.google.android.material.button.MaterialButton) bindings[9]
            , (com.google.android.material.button.MaterialButton) bindings[10]
            , (com.google.android.material.button.MaterialButton) bindings[11]
            , (com.google.android.material.button.MaterialButton) bindings[12]
            , (com.google.android.material.button.MaterialButton) bindings[13]
            , (com.google.android.material.button.MaterialButton) bindings[14]
            , (com.google.android.material.button.MaterialButton) bindings[15]
            , (com.google.android.material.button.MaterialButton) bindings[16]
            , (com.google.android.material.button.MaterialButton) bindings[17]
            , (com.google.android.material.button.MaterialButton) bindings[18]
            , (com.google.android.material.button.MaterialButton) bindings[19]
            , (com.google.android.material.button.MaterialButton) bindings[20]
            , (com.google.android.material.button.MaterialButton) bindings[21]
            , (com.google.android.material.button.MaterialButton) bindings[22]
            , (com.google.android.material.button.MaterialButton) bindings[23]
            , (com.google.android.material.button.MaterialButton) bindings[24]
            , (com.google.android.material.button.MaterialButton) bindings[25]
            , (com.google.android.material.button.MaterialButton) bindings[26]
            , (com.google.android.material.button.MaterialButton) bindings[27]
            , (com.google.android.material.button.MaterialButton) bindings[28]
            , (androidx.gridlayout.widget.GridLayout) bindings[2]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}