package com.yang.base.fragment;

import android.widget.TextView;

import com.yang.base.R;
import com.yang.base.base.BaseFragment;
import com.yang.base.util.BaseCryptoHelper;

/***
 * @desc 加解密demo
 * @author yangguoq
 */

public class CryptoDemoFragment extends BaseFragment {

    private String str ="这是一条等待加密的数据";

    private TextView tv_old,tv_enBase64,tv_deBase64,tv_encrypt,tv_decrypt;
    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_cropty_demo;
    }

    @Override
    protected void findViews() {
        tv_old=findViewById(R.id.tv_old);
        tv_encrypt=findViewById(R.id.tv_encrypt);
        tv_decrypt=findViewById(R.id.tv_decrypt);
        tv_enBase64=findViewById(R.id.tv_enBase64);
        tv_deBase64=findViewById(R.id.tv_deBase64);
    }

    @Override
    protected void init() {
        tv_old.setText("原始数据 =>"+str);

        String encode=BaseCryptoHelper.getInstance().base64Encode(str.getBytes());
        tv_enBase64.setText("base64加密后 =>"+encode);
        byte[] decode=BaseCryptoHelper.getInstance().base64Decode(encode);
        tv_deBase64.setText("base64解密后 =>" +new String(decode));


        String enCrypt=BaseCryptoHelper.getInstance().AESEncrypt(str);
        //String enCrypt=BaseCryptoHelper.encrypt(str.getBytes());
        tv_encrypt.setText("AES加密后 =>"+ enCrypt);
        tv_decrypt.setText("AES解密后 =>"+ BaseCryptoHelper.getInstance().AESDecrypt(enCrypt));
    }
}
