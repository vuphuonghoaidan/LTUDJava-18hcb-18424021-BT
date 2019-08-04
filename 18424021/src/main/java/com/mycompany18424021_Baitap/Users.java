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
public class Users {
    protected String _USER;
    protected String _PASS;
    protected String _CV;

    public void setUSER(String _USER) {
        this._USER = _USER;
    }

    public void setPASS(String _PASS) {
        this._PASS = _PASS;
    }

    public void setCV(String _CV) {
        this._CV = _CV;
    }

    public String getPASS() {
        return _PASS;
    }

    public String getCV() {
        return _CV;
    }
}
