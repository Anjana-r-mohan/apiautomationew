package com.Retailer.Endpoints;

import com.Retailer.Utilities.Generate_Token;

/**
 * Project Name    : BizomRetailer
 * Developer       : Anjana R Mohan
 * Version         : 1.0.0
 **/
public class Routes {
    public static String base_url = "https://devbizomqa.bizomdev.in/";
    public static String getToken() {
        return Generate_Token.getNewAccessToken();
    }
    public static String token = "45904146ee2e9e73b77dd225033020ef2a6b96dc";

    // User Module
    public static String GET_OUTLET_AND_CHILD_OUTLET = base_url+"outlets/getOutletAndChildOutlet/2?access_token=" + getToken();

}
