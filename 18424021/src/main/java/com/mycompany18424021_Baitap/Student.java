/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany18424021_Baitap;

/**
 *
 * @author WINPC
 */
public class Student {
    protected String _MSSV;
    protected String _HOTEN;
    protected Boolean _GT; //True: Nam , False: Nu
    protected String _CMND;

    public String get_MSSV(){
        return this._MSSV;
    }

    public String get_HOTEN(){
        return this._HOTEN;
    }

    public Boolean get_GioiTinh(){
        return this._GT;
    }

    public String get_CMND(){
        return this._CMND;
    }
}
