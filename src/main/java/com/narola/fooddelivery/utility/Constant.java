package com.narola.fooddelivery.utility;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Constant {
	
	public static final String ERR_SOMETHING_WRONG="Oops, Something went Wrong..";
	
	public static final int DISH_TYPE_VEG = 0;
	public static final int DISH_TYPE_NON_VEG = 1;
	public static final int ADMIN_SUPERADMIN = 1;
	public static final int ADMIN_ADMIN = 2;
	public static final int ADMIN_RESTAURANTADMIN = 3;
	public static final int ENDUSER = 0;
	
	public static final int PAYMENT_FAIL=3;
	public static final int PAYMENT_PENDING=2;
	public static final int PAYMENT_SUCCESS=1;
	
	public static final int ORDER_NEW=1;
	public static final int ORDER_ACCEPTED=2;
	public static final int ORDER_PREPARING=3;
	public static final int ORDER_ONTHEWAY=4;
	public static final int ORDER_DELIVERED=5;
	public static final int ORDER_REJECTED=6;
	public static final int ORDER_CANCELED=7;
	
	public static final Map<Integer,String> ORDER_STATUS;
	static {
        Map<Integer, String> aMap = new HashMap<>();
        aMap.put(1, "New");
        aMap.put(2, "Accepted");
        aMap.put(3, "Preparing");
        aMap.put(4, "On The Way");
        aMap.put(5, "Delivered");
        aMap.put(6, "Rejected");
        aMap.put(7, "Canceled");
        ORDER_STATUS = Collections.unmodifiableMap(aMap);
    }
	
	public static final Map<Integer,String> ORDER_PROCESS;
	static {
        Map<Integer, String> aMap = new HashMap<>();
        aMap.put(1, "Accept");
        aMap.put(2, "Prepare");
        aMap.put(3, "Dispatch");
        aMap.put(4, "Complete");
        
        ORDER_PROCESS = Collections.unmodifiableMap(aMap);
    }
	
	public static final Map<Integer,String> ORDER_STATUS_COLOR;
	static {
        Map<Integer, String> aMap = new HashMap<>();
        aMap.put(1, "info");
        aMap.put(2, "primary");
        aMap.put(3, "warning");
        aMap.put(4, "success");
        aMap.put(5, "dark");
        aMap.put(6, "danger");
        aMap.put(7, "danger");
        ORDER_STATUS_COLOR = Collections.unmodifiableMap(aMap);
    }
	
}
