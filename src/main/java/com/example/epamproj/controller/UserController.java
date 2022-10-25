package com.example.epamproj.controller;

import com.example.epamproj.unused.DBManager;

public class UserController {
        private DBManager dbManager;


        private UserController() {
                dbManager=DBManager.getInstance();
        }


}
