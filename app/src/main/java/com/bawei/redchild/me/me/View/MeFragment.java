package com.bawei.redchild.me.me.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.redchild.R;
import com.bawei.redchild.base.BaseFragment;
import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static android.content.Context.MODE_PRIVATE;

/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/5/17 0017
 */

public class MeFragment extends BaseFragment implements View.OnClickListener{

    private ImageView mHeadicon;
    private TextView mName,mset;
    private SharedPreferences babyInfo;
    private Bitmap mBimap;
    private String mName1;
    private String mIconurl;

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_loginsuccess;
    }

    /**
     * 初始化 view控件
     */
    @Override
    protected void initView() {
        mset= (TextView) getView().findViewById(R.id.tv_set_success);
        mset.setOnClickListener(this);
        mHeadicon = (ImageView) getView().findViewById(R.id.iv_head_success);
        mHeadicon.setOnClickListener(this);
        mName = (TextView) getView().findViewById(R.id.tv_name_success);
        babyInfo = getActivity().getSharedPreferences("babyInfo", MODE_PRIVATE);
        mName1 = babyInfo.getString("name", "aa");
        mIconurl = babyInfo.getString("icon", "");
        Glide.with(getActivity()).load(mIconurl).bitmapTransform(new CropCircleTransformation(getActivity())).into(mHeadicon);
        mName.setText(mName1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_set_success:
                babyInfo.edit().clear().commit();
                Toast.makeText(getActivity(), "清除SharedP 缓存", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Login_act.class);
                startActivity(intent);
                break;
            case R.id.iv_head_success:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                final String[] items = {"照相机", "本地图片", "取消"};
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                invokecamera();
                                dialog.dismiss();
                                break;
                            case 1:
                                invokeimgages();
                                dialog.dismiss();
                                break;
                            case 2:
                                dialog.dismiss();
                                break;
                        }
                    }
                });
                builder.create().show();
                break;
        }
    }

    public void invokecamera() {
        //启动照像机组件
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addCategory("android.intent.category.DEFAULT");
        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200) {
            //得到像册中图片的地址
            Uri uri = data.getData();
//设置图片
//       img.setImageURI(uri);
//简单编辑
            crop(uri);
        }else if (requestCode == 9999) {
            //得到裁剪后的照片
            mBimap = data.getParcelableExtra("data");
            mHeadicon.setImageBitmap(mBimap);
        }else if (requestCode == 100) {
            //得到照片
            mBimap = data.getParcelableExtra("data");

            mHeadicon.setImageBitmap(mBimap);

            super.onActivityResult(requestCode, resultCode, data);
        }


    }
    public void invokeimgages() {
        //调用系统相册
        Intent intent = new Intent(Intent.ACTION_PICK);
        //设置图片种类,去除视频类
        intent.setType("image/*");
        startActivityForResult(intent, 200);
    }


    //简单编辑的方法
    private void crop(Uri uri) {

        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //是否裁剪
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", false);// 取消人脸识别

        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 9999);
    }

}
