package uos.uos25.util;

public class MileageUtil {
    private static final Integer percentage = 10;
    public static Integer getMileage(Integer price){
        return price * percentage / 100;
    }
}
