package com.Retailer.Endpoints;

import com.Retailer.Utilities.Generate_Token;

/**
 * Project Name    : BizomRetailer
 * Developer       : Anjana R Mohan
 * Version         : 1.0.0
 **/
public class Routes {
    public static String base_url = "https://devretailtwo.bizomdev.in/";

    // Return fresh token
    public static String getToken() {
        return Generate_Token.getNewAccessToken();
    }

    // Use methods to construct the full API URLs with fresh tokens
    public static String getOutletAndChildOutlet(String outletId) {
        return base_url + "outlets/getOutletAndChildOutlet/" + outletId + "?access_token=" + getToken();
    }

    public static String getEntityRuleDetails(String ruleId) {
        return base_url + "entityruledetails/getEntityruleDetails/" + ruleId + "?access_token=" + getToken();
    }

    public static String getInfo() {
        return base_url + "companies/getinfo?access_token=" + getToken();
    }

    public static String getCompanyKyc() {
        return base_url + "companies/getCompanyKycForm?access_token=" + getToken();
    }

}
