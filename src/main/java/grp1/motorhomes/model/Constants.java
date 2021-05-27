package grp1.motorhomes.model;

/**
 * @author Christian og Patrick
 */
public class Constants {

    public static final double LOW_SEASON = 0.60; //in low season the price is 60% off normal price
    public static final double MID_SEASON = 0.30; //in mid season is 70% off normal price
    public static final double TRANSFER_COST_PER_KM = 0.70; //0,70€ per kilometer in transfer cost
    public static final int FUEL_COST_UNDER_HALF_TANK = 70; //under half tank, the fee is 70€
    public static final int FEE_ON_EXCESS_KM = 1; //more than 400 km driven a day, the fee is 1€
    public static final int MINIMUM_CANCELLATION_FEE = 200; //minimum fee is 200€
    public static final double CANCELLATION_FEE_50 = 0.2; //cancellation fee 20% for cancelling 50+ days prior
    public static final double CANCELLATION_FEE_49_TO_15 = 0.5; //cancellation fee 50% for cancelling 49 days to 15 days prior
    public static final double CANCELLATION_FEE_LESS_15 = 0.8; //cancellation fee 80% for cancelling less than 15 days prior
    public static final double CANCELLATION_FEE_ON_DAY = 0.95; //cancellation fee 95% for cancelling on the same day
    public static final double CANCELLATION_50_DAYS = 50;
    public static final double CANCELLATION_49_DAYS = 49;
    public static final double CANCELLATION_15_DAYS = 15;
    public static final double CANCELLATION_SAME_DAY = 0;
    public static final int JANUARY = 1;
    public static final int FEBRUARY = 2;
    public static final int MARCH = 3;
    public static final int APRIL = 4;
    public static final int MAY = 5;
    public static final int JUNE = 6;
    public static final int JULY = 7;
    public static final int AUGUST = 8;
    public static final int SEPTEMBER = 9;
    public static final int OCTOBER = 10;
    public static final int NOVEMBER = 11;
    public static final int DECEMBER = 12;
    public static final int GRACE_PERIOD = 2; // two days grace period after contract is done before the motorhome can be on other contracts

}
