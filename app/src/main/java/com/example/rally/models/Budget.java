package com.example.rally.models;

import java.util.Hashtable;
import java.util.List;

public class Budget {
    private String profile_ID;
    private Hashtable<String, List<Purchase>> categories = new Hashtable<String, List<Purchase>>();
}
