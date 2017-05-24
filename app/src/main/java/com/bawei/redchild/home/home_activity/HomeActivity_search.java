package com.bawei.redchild.home.home_activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.redchild.R;
import com.bawei.redchild.home.baseadapter.OtherAdapter;
import com.bawei.redchild.home.bean.VoiceBean;
import com.bawei.redchild.home.h_url_f1.ClickUtils;

import com.bawei.redchild.home.h_url_f1.MyGridView;
import com.google.gson.Gson;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;


import java.util.ArrayList;
import java.util.List;




/**
 * 作者:史洲铭
 * 时间:2017/4/13 20:51
 * 类描述:搜索管理模块页面
 */
public class HomeActivity_search extends Activity implements AdapterView.OnItemClickListener,
        View.OnClickListener {
    private MyGridView mUserGv, mOtherGv;
    private List<String> mUserList = new ArrayList<>();
    private List<String> mOtherList = new ArrayList<>();
    private OtherAdapter mUserAdapter, mOtherAdapter;

    private TextView mTextView, activity_home_search_tv;
    private ImageView mClear_userGridView, home_title_btn_barcode;
    private EditText et_search_input;
    private ImageView home_search_title_logo;
    private ImageView img_search_input_delete;
    private ImageView img_search_input_voice_icon;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_searchbar_top);
        initView();
        initData();

    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            mOtherList.add("li三"+i);
        }
        mOtherAdapter = new OtherAdapter(HomeActivity_search.this, mOtherList, false);
        mOtherGv.setAdapter(mOtherAdapter);


    }

    public void initView() {
        mUserGv = (MyGridView) findViewById(R.id.userGridView);
        mOtherGv = (MyGridView) findViewById(R.id.otherGridView);
        mTextView = (TextView) findViewById(R.id.my_category_tip_text);
        activity_home_search_tv = (TextView) findViewById(R.id.activity_home_search_tv);
        mClear_userGridView = (ImageView) findViewById(R.id.clear_userGridView);
        home_title_btn_barcode = (ImageView) findViewById(R.id.home_title_btn_barcode);

        if (mUserList.size() == 0) {
            hideRecently();
        } else {
            showRecently();
        }

        mUserAdapter = new OtherAdapter(HomeActivity_search.this, mUserList, true);
        mOtherAdapter = new OtherAdapter(HomeActivity_search.this, mOtherList, false);
        mOtherGv.setAdapter(mOtherAdapter);
        mUserGv.setAdapter(mUserAdapter);
        mUserGv.setOnItemClickListener(HomeActivity_search.this);
        mOtherGv.setOnItemClickListener(HomeActivity_search.this);
        mClear_userGridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity_search.this);
                builder.setMessage("确定清空历史记录？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mUserList.clear();
                        initView();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.create().show();
            }
        });

        et_search_input = (EditText) findViewById(R.id.et_search_input);
        home_title_btn_barcode.setOnClickListener(this);
        home_search_title_logo = (ImageView) findViewById(R.id.home_search_title_logo);
        home_search_title_logo.setOnClickListener(this);
        img_search_input_delete = (ImageView) findViewById(R.id.img_search_input_delete);
        img_search_input_delete.setOnClickListener(this);
        img_search_input_voice_icon = (ImageView) findViewById(R.id.img_search_input_voice_icon);
        img_search_input_voice_icon.setOnClickListener(this);
        activity_home_search_tv.setOnClickListener(this);

        et_search_input.setOnClickListener(this);
        et_search_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    img_search_input_delete.setVisibility(View.VISIBLE);
                    img_search_input_voice_icon.setVisibility(View.GONE);
                } else {
                    img_search_input_delete.setVisibility(View.GONE);
                    img_search_input_voice_icon.setVisibility(View.VISIBLE);
                }
            }
        });

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.userGridView:
                if (!ClickUtils.isDoubleClick()) {
                    /*Intent intent = new Intent();
                    startActivity(intent);*/
                    Toast.makeText(HomeActivity_search.this, "dianji", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.otherGridView:
                if (!ClickUtils.isDoubleClick()) {
                    showRecently();
                    String item = ((OtherAdapter) adapterView.getAdapter()).getItem(i);
                    if (!mUserList.contains(item)) {
                        mUserAdapter.addItem(item);
                    }
                    et_search_input.setText(item);
                }
                break;
        }
    }


    public void showRecently() {
        mClear_userGridView.setVisibility(View.VISIBLE);
        mTextView.setVisibility(View.VISIBLE);
    }


    public void hideRecently() {
        mClear_userGridView.setVisibility(View.GONE);
        mTextView.setVisibility(View.GONE);
    }

    private void submit() {
        // validate
        String input = et_search_input.getText().toString().trim();
        if (TextUtils.isEmpty(input)) {
            Toast.makeText(this, "input不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        et_search_input.setFocusable(false);
        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
        // TODO validate success, do something
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_title_btn_barcode:
                finish();
                break;
            case R.id.activity_home_search_tv:
                submit();
                break;
            case R.id.img_search_input_delete:
                et_search_input.getText().clear();
                et_search_input.setFocusable(false);
                break;
            case R.id.img_search_input_voice_icon:
                startVoice();
                break;
            case R.id.et_search_input:
                et_search_input.setFocusable(true);
                et_search_input.setFocusableInTouchMode(true);
                et_search_input.requestFocus();
                et_search_input.findFocus();
                break;
        }
    }
////////////////////////////////////////////////////////////////////

    public void startVoice() {
        //1.创建RecognizerDialog对象
        RecognizerDialog mDialog = new RecognizerDialog(this, null);
        //2.设置accent、 language等参数
        mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
        //若要将UI控件用于语义理解，必须添加以下参数设置，设置之后onResult回调返回将是语义理解结果
        // mDialog.setParameter("asr_sch", "1");
        // mDialog.setParameter("nlp_version", "2.0");

        mBuffer = new StringBuffer();
        //3.设置回调接口
        mDialog.setListener(mRecognizerDialogListener);
        //4.显示dialog，接收语音输入
        mDialog.show();
    }

    private StringBuffer mBuffer;
    RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {
        //听写结果回调接口(返回Json格式结果，用户可参见附录13.1)；
        //一般情况下会通过onResults接口多次返回结果，完整的识别内容是多次结果的累加；
        //关于解析Json的代码可参见Demo中JsonParser类；
        //isLast等于true时会话结束。
        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
            String result = results.getResultString();//语音听写的结果

            String resultString = processData(result);

            mBuffer.append(resultString);

            if (isLast) {
                //话已经说完了
                String finalResult = mBuffer.toString();
                System.out.println("解析结果:" + finalResult);
                et_search_input.setText(finalResult);
            }
        }

        @Override
        public void onError(SpeechError error) {

        }
    };

    //解析json
    protected String processData(String result) {
        Gson gson = new Gson();
        VoiceBean voiceBean = gson.fromJson(result, VoiceBean.class);

        StringBuffer sb = new StringBuffer();

        ArrayList<VoiceBean.WsBean> ws = voiceBean.ws;
        for (VoiceBean.WsBean wsBean : ws) {
            String word = wsBean.cw.get(0).w;
            sb.append(word);
        }

        return sb.toString();
    }


///////////////////////////////////////////////////////////////////////
   /*@Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        img_search_input_delete.setVisibility(View.VISIBLE);
        img_search_input_voice_icon.setVisibility(View.GONE);
    }

    @Override
    public void afterTextChanged(Editable editable) {
        img_search_input_delete.setVisibility(View.GONE);
        img_search_input_voice_icon.setVisibility(View.VISIBLE);
    }*/
}
