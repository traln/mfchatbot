package vn.com.mobifone.mfchatbot.ui;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import vn.com.mobifone.mfchatbot.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MarsFullscreenDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MarsFullscreenDialogFragment extends DialogFragment {
    String imgSrc;

    public static MarsFullscreenDialogFragment newInstance(String imgSrc){
        MarsFullscreenDialogFragment  marsFullscreenDialogFragment = new MarsFullscreenDialogFragment();

        Bundle args = new Bundle();
        args.putString("img_src", imgSrc);
        marsFullscreenDialogFragment.setArguments(args);

        return marsFullscreenDialogFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imgSrc = getArguments().getString("img_src");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mars_fullscreen_dialog, container, false);

        final ImageView imageViewFullscreen = v.findViewById(R.id.imageViewFullscreen);

        if(imgSrc != null)
            Picasso.get().load(imgSrc).into(imageViewFullscreen);

        return v;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
}
