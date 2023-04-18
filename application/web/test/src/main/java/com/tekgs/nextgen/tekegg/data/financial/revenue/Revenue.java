package com.tekgs.nextgen.tekegg.data.financial.revenue;

public class Revenue  {
    String month;
    Long netRevenue;
    
    private Revenue(String month, Long netRevenue) {
        this.month = month;
        this.netRevenue = netRevenue;
    }
    
    public static Revenue getInstance() {
        return new Revenue(null, null);
    }
    
    public static Revenue getInstance(String month, Long netRevenue) {
        return new Revenue(month, netRevenue);
    }
    
    
    public Long getNetRevenue() {
        return this.netRevenue;
    }

    public String getRevenueMonth() {return this.month;}
}
