package com.tekgs.nextgen.tekegg.view.financialDashboard;

import com.softwareonpurpose.calibrator4test.Calibrator;
import com.tekgs.nextgen.tekegg.view.financialDashboard.revenue.RevenueRegionCalibratable;
import com.tekgs.nextgen.tekegg.view.financialDashboard.revenue.RevenueRegionCalibrator;

import java.util.List;

public class FinancialDashboardViewCalibrator extends Calibrator {
    private static final String DESCRIPTION = "'Financial' dashboard";
    private final FinancialDashboardViewExpected expected;
    private final FinancialDashboardView actual;

    private FinancialDashboardViewCalibrator(FinancialDashboardViewExpected expected, FinancialDashboardView actual) {
        super(DESCRIPTION, expected, actual);
        this.expected = expected;
        this.actual = actual;
        List<RevenueRegionCalibratable> expectedRevenueList = expected.inRevenueRegion().getRevenueList();
        List<RevenueRegionCalibratable> actualRevenueList = actual.inRevenueRegion().getRevenueList();
        addCalibrationsExpected(expectedRevenueList, actualRevenueList);
        addCalibrationsUnexpected(actualRevenueList);
    }

    public static FinancialDashboardViewCalibrator getInstance(FinancialDashboardViewExpected expected, FinancialDashboardView actual) {
        return new FinancialDashboardViewCalibrator(expected, actual);

    }

    private void addCalibrationsExpected(List<RevenueRegionCalibratable> expectedRevenueList, List<RevenueRegionCalibratable> actualRevenueList) {
        expectedRevenueList.forEach(expectedRevenue -> {
            RevenueRegionCalibratable revenueFound = addCalibrationFound(actualRevenueList, expectedRevenue);
            if (revenueFound == null) {
                addChildCalibrator(RevenueRegionCalibrator.getInstance(expectedRevenue, null));
            } else {
                actualRevenueList.remove(revenueFound);
            }
        });

    }

    private RevenueRegionCalibratable addCalibrationFound(List<RevenueRegionCalibratable> actualRevenueList, RevenueRegionCalibratable expectedRevenue) {
        RevenueRegionCalibratable revenueFound = actualRevenueList.stream()
                .filter(actualRevenue -> actualRevenue.equivalent(expectedRevenue))
                .findFirst()
                .orElse(null);

        if (revenueFound != null) {
            addChildCalibrator(RevenueRegionCalibrator.getInstance(expectedRevenue, revenueFound));
        }
        return revenueFound;
    }

    private void addCalibrationsUnexpected(List<RevenueRegionCalibratable> actualRevenueList) {
        actualRevenueList.forEach(revenueUnexpected -> addChildCalibrator(RevenueRegionCalibrator.getInstance(null, revenueUnexpected)));
    }

    @Override
    protected void executeVerifications() {
        verify("'revenue' title ", this.expected.getRevenueListTitle(), this.actual.getRevenueListTitle());
    }
}
