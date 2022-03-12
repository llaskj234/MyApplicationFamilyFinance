package com.hui.myapplication.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.hui.myapplication.R;

public class BeizhuDialog extends Dialog implements View.OnClickListener {

    EditText et;
    Button cancleBtn,ensureBtn;
    OnEnsureListener onEnsureListener;
    //设定回调接口方法
    public void setOnEnsureListener(OnEnsureListener onEnsureListener){
        this.onEnsureListener = onEnsureListener;
    }
    public BeizhuDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_beizhu);//设置对话框显示布局
        et = findViewById(R.id.dialog_beizhu_et);
        cancleBtn = findViewById(R.id.dialog_beizhu_btn_cancle);
        ensureBtn = findViewById(R.id.dialog_beizhu_btn_ensure);
        cancleBtn.setOnClickListener(this);
        ensureBtn.setOnClickListener(this);
    }

    public interface OnEnsureListener{
        public void onEnsure();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_beizhu_btn_cancle:
                cancel();
                break;
            case R.id.dialog_beizhu_btn_ensure:
                if (onEnsureListener!=null) {
                    onEnsureListener.onEnsure();
                }
                break;
        }
    }

    //获取输入数据的方法
    public String getEditText(){
        return et.getText().toString().trim();//trim去掉前后空格及回车字符
    }

    //设置Dialog的尺寸与屏幕尺寸一致
    public void setDiologSize(){
        //获取当前窗口对象
        Window window = getWindow();
        //获取窗口对象的参数
        WindowManager.LayoutParams wlp = window.getAttributes();
        //获取屏幕宽度
        Display d = window.getWindowManager().getDefaultDisplay();
        wlp.width = (int)(d.getWidth());//对话窗口为屏幕窗口
        wlp.gravity = Gravity.BOTTOM;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes(wlp);
        handler.sendEmptyMessageDelayed(1,100);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            //自动弹出软键盘的方法
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
        }
    };
}
