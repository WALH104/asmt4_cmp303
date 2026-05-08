// Simple date class with day difference calculation
public class Date implements Comparable<Date> {

    public enum DateFormat {
        US, EU
    }

    private int year, month, day;

    public Date(int year, int month, int day, DateFormat format) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    // Returns number of days from this date to other
    public int daysUntil(Date other) {
        return other.toJulianDay() - this.toJulianDay();
    }

    private int toJulianDay() {
        int a = (14 - month) / 12;
        int y = year + 4800 - a;
        int m = month + 12 * a - 3;
        return day + (153 * m + 2) / 5 + 365 * y + y / 4 - y / 100 + y / 400 - 32045;
    }

    @Override
    public int compareTo(Date other) {
        if (this.year != other.year)
            return Integer.compare(this.year, other.year);
        if (this.month != other.month)
            return Integer.compare(this.month, other.month);
        return Integer.compare(this.day, other.day);
    }

    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d", year, month, day);
    }
}