package self.mvvmexample.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import self.mvvmexample.R;


public class DialogPopup {

    public static ProgressDialog progressDialog;
    private static DialogPopup instance = new DialogPopup();

    public static DialogPopup getInstance() {
        return instance;
    }

    public static void showLoadingDialog(Context context, String message) {
        try {
            if (isDialogShowing()) {
                dismissLoadingDialog();
            }


            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                if (activity.isFinishing()) {
                    return;
                }
            }

            progressDialog = new ProgressDialog(context, R.style.DialogTheme);
            progressDialog.show();
            WindowManager.LayoutParams layoutParams = progressDialog.getWindow().getAttributes();
            layoutParams.dimAmount = 0.5f;
            progressDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            progressDialog.setCancelable(false);
            progressDialog.setContentView(R.layout.dialog_loading_box);

            ((ProgressWheel) progressDialog.findViewById(R.id.progress_wheel)).spin();
            TextView messageText = progressDialog.findViewById(R.id.tvProgress);
            messageText.setTypeface(FontUtils.INSTANCE.getFont(context));
            messageText.setText(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean isDialogShowing() {
        try {
            if (progressDialog == null) {
                return false;
            } else {
                return progressDialog.isShowing();
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static void dismissLoadingDialog() {
        try {
            if (progressDialog != null) {
                progressDialog.dismiss();
                progressDialog = null;
            }
        } catch (Exception e) {
            Log.e("e", "=" + e);
        }
    }


}
