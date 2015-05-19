package com.example.fallenzone.openthedoor;

import java.io.BufferedReader;
import java.io.File;
import android.content.Context;
import android.webkit.WebView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * Created by ping on 2015/5/16.
 */
public class Login extends MainActivity {
    private String account ;
    private String password;
    FileOutputStream out = null;

    public void fillAccountAndPassword(){


    }

    public String getAccount() {
        StringBuffer data = new StringBuffer();
        try {
            FileInputStream in = openFileInput("test.txt");

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(in, "utf-8"));
                String line;
                while ((line = reader.readLine()) != null) {
                    data.append(line);
                    line += account;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
        try{
            out = openFileOutput("test.txt" , Context.MODE_PRIVATE);
            out.write(account.getBytes());
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                out.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
