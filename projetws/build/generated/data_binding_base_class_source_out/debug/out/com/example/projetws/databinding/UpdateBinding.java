// Generated by view binder compiler. Do not edit!
package com.example.projetws.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.projetws.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class UpdateBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button buttonUpdate;

  @NonNull
  public final EditText editTextNom;

  @NonNull
  public final EditText editTextPhoto;

  @NonNull
  public final EditText editTextPrenom;

  @NonNull
  public final EditText editTextVille;

  @NonNull
  public final ImageView imageView4;

  @NonNull
  public final RadioButton radioFemale;

  @NonNull
  public final RadioGroup radioGroupSexe;

  @NonNull
  public final RadioButton radioMale;

  private UpdateBinding(@NonNull LinearLayout rootView, @NonNull Button buttonUpdate,
      @NonNull EditText editTextNom, @NonNull EditText editTextPhoto,
      @NonNull EditText editTextPrenom, @NonNull EditText editTextVille,
      @NonNull ImageView imageView4, @NonNull RadioButton radioFemale,
      @NonNull RadioGroup radioGroupSexe, @NonNull RadioButton radioMale) {
    this.rootView = rootView;
    this.buttonUpdate = buttonUpdate;
    this.editTextNom = editTextNom;
    this.editTextPhoto = editTextPhoto;
    this.editTextPrenom = editTextPrenom;
    this.editTextVille = editTextVille;
    this.imageView4 = imageView4;
    this.radioFemale = radioFemale;
    this.radioGroupSexe = radioGroupSexe;
    this.radioMale = radioMale;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static UpdateBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static UpdateBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.update, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static UpdateBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonUpdate;
      Button buttonUpdate = ViewBindings.findChildViewById(rootView, id);
      if (buttonUpdate == null) {
        break missingId;
      }

      id = R.id.editTextNom;
      EditText editTextNom = ViewBindings.findChildViewById(rootView, id);
      if (editTextNom == null) {
        break missingId;
      }

      id = R.id.editTextPhoto;
      EditText editTextPhoto = ViewBindings.findChildViewById(rootView, id);
      if (editTextPhoto == null) {
        break missingId;
      }

      id = R.id.editTextPrenom;
      EditText editTextPrenom = ViewBindings.findChildViewById(rootView, id);
      if (editTextPrenom == null) {
        break missingId;
      }

      id = R.id.editTextVille;
      EditText editTextVille = ViewBindings.findChildViewById(rootView, id);
      if (editTextVille == null) {
        break missingId;
      }

      id = R.id.imageView4;
      ImageView imageView4 = ViewBindings.findChildViewById(rootView, id);
      if (imageView4 == null) {
        break missingId;
      }

      id = R.id.radioFemale;
      RadioButton radioFemale = ViewBindings.findChildViewById(rootView, id);
      if (radioFemale == null) {
        break missingId;
      }

      id = R.id.radioGroupSexe;
      RadioGroup radioGroupSexe = ViewBindings.findChildViewById(rootView, id);
      if (radioGroupSexe == null) {
        break missingId;
      }

      id = R.id.radioMale;
      RadioButton radioMale = ViewBindings.findChildViewById(rootView, id);
      if (radioMale == null) {
        break missingId;
      }

      return new UpdateBinding((LinearLayout) rootView, buttonUpdate, editTextNom, editTextPhoto,
          editTextPrenom, editTextVille, imageView4, radioFemale, radioGroupSexe, radioMale);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
