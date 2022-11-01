package com.example.epamproj.unused;

import com.example.epamproj.unused.DBManager;

public class UserController {
        private DBManager dbManager;


        private UserController() {
                dbManager=DBManager.getInstance();
        }


}
