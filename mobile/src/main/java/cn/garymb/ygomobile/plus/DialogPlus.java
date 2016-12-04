package cn.garymb.ygomobile.plus;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import cn.garymb.ygomobile.lite.R;

public class DialogPlus {
    private Context context;
    private AlertDialog.Builder mBuilder;
    private LayoutInflater mLayoutInflater;
    private View mView;
    private TextView mTitleView;
    private View closeView;
    private FrameLayout mFrameLayout;
    private Button mButton;
    private Dialog mDialog;
    private View mContentView;

    public DialogPlus(Context context) {
        this.context = context;
        mBuilder = new AlertDialog.Builder(context);
        mLayoutInflater = LayoutInflater.from(context);
        mView = mLayoutInflater.inflate(R.layout.dialog_base, null);
        mBuilder.setView(mView);
        mTitleView = bind(R.id.title);
        closeView = bind(R.id.close);
        mFrameLayout = bind(R.id.container);
        mButton = bind(R.id.button_ok);
        setCloseLinster((dlg, id) -> {
            dlg.dismiss();
        });
    }

    public DialogPlus hideButton() {
        mButton.setVisibility(View.GONE);
        return this;
    }

    public DialogPlus setCancelable(boolean cancelable) {
        mBuilder.setCancelable(cancelable);
        return this;
    }

    public DialogPlus setCloseLinster(DialogInterface.OnClickListener clickListener) {
        closeView.setOnClickListener((v) -> {
            if (clickListener != null) {
                clickListener.onClick(mDialog, DialogInterface.BUTTON_NEGATIVE);
            }
        });
        return this;
    }

    public DialogPlus setButtonListener(DialogInterface.OnClickListener clickListener) {
        mButton.setOnClickListener((v) -> {
            if (clickListener != null) {
                clickListener.onClick(mDialog, DialogInterface.BUTTON_POSITIVE);
            }
        });
        return this;
    }

    public DialogPlus setOnCancelListener(DialogInterface.OnCancelListener clickListener) {
        mBuilder.setOnCancelListener(clickListener);
        return this;
    }

    public DialogPlus setTitle(int id) {
        return setTitle(context.getString(id));
    }

    public DialogPlus setMessage(int id) {
        return setMessage(context.getString(id));
    }

    public DialogPlus setMessage(String text) {
        setView(R.layout.dialog_message);
        TextView textView = findViewById(R.id.text);
        textView.setText(text);
        return this;
    }

    public DialogPlus setTitle(String text) {
        mTitleView.setText(text);
        return this;
    }

    public DialogPlus setButtonText(int id) {
        return setButtonText(context.getString(id));
    }

    public DialogPlus setButtonText(String text) {
        mButton.setText(text);
        return this;
    }

    public DialogPlus setView(int id) {
        View view = mLayoutInflater.inflate(id, null);
        return setContentView(view);
    }

    public View getContentView() {
        return mContentView;
    }

    public DialogPlus setView(View view) {
        setContentView(view);
        return this;
    }

    public DialogPlus setContentView(int id) {
        View view = mLayoutInflater.inflate(id, null);
        return setContentView(view);
    }

    public DialogPlus setContentView(View view) {
        this.mContentView = view;
        mFrameLayout.removeAllViews();
        mFrameLayout.addView(view);
        return this;
    }

    public Dialog show() {
        if (mDialog != null) {
            if (!mDialog.isShowing()) {
                mDialog.show();
            }
            return mDialog;
        }
        mDialog = mBuilder.show();
        return mDialog;
    }

    public void dismiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    private <T extends View> T bind(int id) {
        return (T) mView.findViewById(id);
    }

    public <T extends View> T findViewById(int id) {
        return (T) mContentView.findViewById(id);
    }

}
