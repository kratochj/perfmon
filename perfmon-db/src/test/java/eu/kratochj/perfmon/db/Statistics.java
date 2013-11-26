package eu.kratochj.perfmon.db;

import java.util.Date;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class Statistics {
	String name;
	String category;
	int dt_year;
	int dt_month;
	int dt_day;
	int dt_hour;
	Date last_updated;
	long total_calls;
	long total_time;
	long longest;
	long shorttest;

	public Statistics() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getDt_year() {
		return dt_year;
	}

	public void setDt_year(int dt_year) {
		this.dt_year = dt_year;
	}

	public int getDt_month() {
		return dt_month;
	}

	public void setDt_month(int dt_month) {
		this.dt_month = dt_month;
	}

	public int getDt_day() {
		return dt_day;
	}

	public void setDt_day(int dt_day) {
		this.dt_day = dt_day;
	}

	public int getDt_hour() {
		return dt_hour;
	}

	public void setDt_hour(int dt_hour) {
		this.dt_hour = dt_hour;
	}

	public Date getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(Date last_updated) {
		this.last_updated = last_updated;
	}

	public long getTotal_calls() {
		return total_calls;
	}

	public void setTotal_calls(long total_calls) {
		this.total_calls = total_calls;
	}

	public long getTotal_time() {
		return total_time;
	}

	public void setTotal_time(long total_time) {
		this.total_time = total_time;
	}

	public long getLongest() {
		return longest;
	}

	public void setLongest(long longest) {
		this.longest = longest;
	}

	public long getShorttest() {
		return shorttest;
	}

	public void setShorttest(long shorttest) {
		this.shorttest = shorttest;
	}

	@Override
	public String toString() {
		return "Statistics{" +
				"name='" + name + '\'' +
				", category='" + category + '\'' +
				", dt_year=" + dt_year +
				", dt_month=" + dt_month +
				", dt_day=" + dt_day +
				", dt_hour=" + dt_hour +
				", last_updated=" + last_updated +
				", total_calls=" + total_calls +
				", total_time=" + total_time +
				", longest=" + longest +
				", shorttest=" + shorttest +
				'}';
	}
}
