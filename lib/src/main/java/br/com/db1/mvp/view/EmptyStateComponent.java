package br.com.db1.mvp.view;


import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import br.com.db1.mvp.R;
import br.com.db1.mvp.util.LogUtils;

/**
 * Created by andre.moraes on 16/02/2018.
 */
public class EmptyStateComponent extends RelativeLayout implements IEmptyStateComponent {

    private static final String TAG = EmptyStateComponent.class.getSimpleName();

    ImageView ivImage;
    TextView tvMessage;

    private ViewGroup parent;
    private ViewGroup target;

    public EmptyStateComponent(Context context, ViewGroup parent, ViewGroup target) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.component_empty_state, this);
        ivImage = findViewById(R.id.cpt_empty_state_iv_image);
        tvMessage = findViewById(R.id.cpt_empty_state_tv_message);
        this.parent = parent;
        this.target = target;
    }

    public EmptyStateComponent setImage(@DrawableRes int imageRes) {
        if (isValid()) {
            ivImage.setBackgroundResource(imageRes);
        }
        return this;
    }

    public EmptyStateComponent setMessage(@StringRes int messageRes) {
        tvMessage.setText(messageRes);
        return this;
    }

    @Override
    public void show() {
        if (isValid()) {
            final int index = parent.indexOfChild(target);
            if (index >= 0) {
                parent.removeViewAt(index);
                parent.addView(this, index);
            }
        }
    }

    @Override
    public void hide() {
        if (isValid()) {
            final int index = parent.indexOfChild(this);
            if (index >= 0) {
                parent.removeViewAt(index);
                parent.addView(target, index);
            }
        }
    }

    private boolean isValid() {
        if (parent == null || target == null) {
            LogUtils.error(TAG, "parent and target cannot be null.");
            return false;
        }
        return true;
    }

}
