package com.tekgs.nextgen.tekegg.view.financialDashboard.revenue;

import com.softwareonpurpose.calibrator4test.Calibrator;

public class RevenueRegionCalibrator extends Calibrator {

    private static final String DESCRIPTION = "'Revenue' region";

    private RevenueRegionCalibratable expected;
    private RevenueRegionCalibratable actual;

    private RevenueRegionCalibrator(RevenueRegionCalibratable expected, RevenueRegionCalibratable actual) {
        super(DESCRIPTION, expected, actual);
        this.expected = expected;
        this.actual = actual;
    }

    public static RevenueRegionCalibrator getInstance(RevenueRegionCalibratable expected, RevenueRegionCalibratable actual) {
        return new RevenueRegionCalibrator(expected, actual);
    }

    @Override
    protected void executeVerifications() {
        verify("'Revenue' Month", expected.getMonth(), actual.getMonth());
        verify("'Revenue' Net", expected.getNetRevenue(), actual.getNetRevenue());
    }



}
