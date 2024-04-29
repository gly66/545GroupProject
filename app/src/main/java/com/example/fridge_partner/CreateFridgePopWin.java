package com.example.fridge_partner;

import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

public class CreateFridgePopWin extends PopupWindow {

    private Context mContext;

    private View view;

    private Button btn_create_pop;

    public EditText text_name;

    public EditText text_description;


    public CreateFridgePopWin(Activity mContext) {

        this.mContext = mContext;

        this.view = LayoutInflater.from(mContext).inflate(R.layout.create_fridge, null);

        text_name = (EditText) view.findViewById(R.id.fridgename);
        text_description= (EditText) view.findViewById(R.id.description);

        btn_create_pop =  (Button) view.findViewById(R.id.createButton);

        // 设置按钮监听
//        btn_create_pop.setOnClickListener(itemsOnClick);

        // 设置外部可点击
        this.setOutsideTouchable(true);


        /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);

        // 设置弹出窗体的宽和高
        /*
         * 获取圣诞框的窗口对象及参数对象以修改对话框的布局设置, 可以直接调用getWindow(),表示获得这个Activity的Window
         * 对象,这样这可以以同样的方式改变这个Activity的属性.
         */
        Window dialogWindow = mContext.getWindow();

        WindowManager m = mContext.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值

        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth((int) (d.getWidth() * 0.8));

        // 设置弹出窗体可点击
        this.setFocusable(true);

    }

}
