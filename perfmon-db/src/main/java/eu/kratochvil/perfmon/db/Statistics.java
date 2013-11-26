package eu.kratochvil.perfmon.db;

import java.util.Date;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class Statistics {
	String name;
	String category;
	int dtYear;
	int dtMonth;
	int dtDay;
	int dtHour;
	Date lastUpdated;
	long totalCalls;
	long totalTime;
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

	public int getDtYear() {
		return dtYear;
	}

	public void setDtYear(int dtYear) {
		this.dtYear = dtYear;
	}

	public int getDtMonth() {
		return dtMonth;
	}

	public void setDtMonth(int dtMonth) {
		this.dtMonth = dtMonth;
	}

	public int getDtDay() {
		return dtDay;
	}

	public void setDtDay(int dtDay) {
		this.dtDay = dtDay;
	}

	public int getDtHour() {
		return dtHour;
	}

	public void setDtHour(int dtHour) {
		this.dtHour = dtHour;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public long getTotalCalls() {
		return totalCalls;
	}

	public void setTotalCalls(long totalCalls) {
		this.totalCalls = totalCalls;
	}

	public long getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
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
				", dtYear=" + dtYear +
				", dtMonth=" + dtMonth +
				", dtDay=" + dtDay +
				", dtHour=" + dtHour +
				", lastUpdated=" + lastUpdated +
				", totalCalls=" + totalCalls +
				", totalTime=" + totalTime +
				", longest=" + longest +
				", shorttest=" + shorttest +
				'}';
	}
}
