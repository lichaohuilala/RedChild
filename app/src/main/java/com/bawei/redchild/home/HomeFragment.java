package com.bawei.redchild.home;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.acker.simplezxing.activity.CaptureActivity;
import com.bawei.redchild.HomeActivity;
import com.bawei.redchild.MainActivity;
import com.bawei.redchild.R;
import com.bawei.redchild.home.baseadapter.H_frag_base;
import com.bawei.redchild.home.baseadapter.H_recyview_adapter;
import com.bawei.redchild.home.bean.Home_frag1_bean;
import com.bawei.redchild.home.h_url_f1.Gsons;
import com.bawei.redchild.home.h_url_f1.Url;
import com.bawei.redchild.home.home_activity.HomeActivity_search;
import com.bawei.redchild.home.home_activity.SeekActivity;
import com.squareup.okhttp.Request;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {

    private ViewPager vp_homef1_hua_viewp;
    private TabLayout tb_homef1_biaot_tabl;
    private LinearLayout category_iv;
private RelativeLayout home_btn_search_layout;
    private static final int REQ_CODE_PERMISSION = 0x1111;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        vp_homef1_hua_viewp = (ViewPager) inflate.findViewById(R.id.vp_homef1_hua_viewp);
        tb_homef1_biaot_tabl = (TabLayout) inflate.findViewById(R.id.tb_homef1_biaot_tabl);
        category_iv= (LinearLayout) inflate.findViewById(R.id.category_iv);
        home_btn_search_layout= (RelativeLayout) inflate.findViewById(R.id.home_btn_search_layout);
       initview();
        return inflate;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initview() {

        OkHttpUtils.get().url(Url.path).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {

                Home_frag1_bean home_frag1_bean = Gsons.GetGsonfrom(response, Home_frag1_bean.class);
                List<Home_frag1_bean.DataBean> data = home_frag1_bean.getData();
                List<Home_frag1_bean.DataBean.TagBean> tag = data.get(0).getTag();
                H_frag_base h_frag_base = new H_frag_base(getChildFragmentManager(), tag);
                vp_homef1_hua_viewp.setAdapter(h_frag_base);
                tb_homef1_biaot_tabl.setupWithViewPager(vp_homef1_hua_viewp);

            }
        });
        category_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Scan Activity
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // Do not have the permission of camera, request it.
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQ_CODE_PERMISSION);
                } else {
                    // Have gotten the permission
                    startCaptureActivityForResult();
                }
            }
        });
        home_btn_search_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeActivity_search.class);
                startActivity(intent);
            }
        });
    }

    private void startCaptureActivityForResult() {
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean(CaptureActivity.KEY_NEED_BEEP, CaptureActivity.VALUE_BEEP);
        bundle.putBoolean(CaptureActivity.KEY_NEED_VIBRATION, CaptureActivity.VALUE_VIBRATION);
        bundle.putBoolean(CaptureActivity.KEY_NEED_EXPOSURE, CaptureActivity.VALUE_NO_EXPOSURE);
        bundle.putByte(CaptureActivity.KEY_FLASHLIGHT_MODE, CaptureActivity.VALUE_FLASHLIGHT_OFF);
        bundle.putByte(CaptureActivity.KEY_ORIENTATION_MODE, CaptureActivity.VALUE_ORIENTATION_AUTO);
        intent.putExtra(CaptureActivity.EXTRA_SETTING_BUNDLE, bundle);
        startActivityForResult(intent, CaptureActivity.REQ_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQ_CODE_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // User agree the permission
                    startCaptureActivityForResult();
                } else {
                    // User disagree the permission
                    Toast.makeText(getActivity(), "You must agree the camera permission request before you use the code scan function", Toast.LENGTH_LONG).show();
                }
            }
            break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CaptureActivity.REQ_CODE:
                switch (resultCode) {
                    case RESULT_OK:
                        String str = data.getStringExtra(CaptureActivity.EXTRA_SCAN_RESULT);
                        //or do sth
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        Uri content_url = Uri.parse(str);
                        intent.setData(content_url);
                        startActivity(intent); //用默认浏览器打开扫描得到的地址

                        break;
                    case RESULT_CANCELED:
                        if (data != null) {
                            // for some reason camera is not working correctly
                            String sstr = data.getStringExtra(CaptureActivity.EXTRA_SCAN_RESULT);

                            Intent intent2 = new Intent();
                            intent2.setAction("android.intent.action.VIEW");
                            Uri content_url2 = Uri.parse(sstr);
                            intent2.setData(content_url2);
                            startActivity(intent2); //用默认浏览器打开扫描得到的地址

                        }
                        break;
                }
                break;
        }

    }}

