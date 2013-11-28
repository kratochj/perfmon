package eu.kratochvil.perfmon.db.utils;

import eu.kratochvil.perfmon.db.Statistics;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Albert Sputa (albert.sputa@topmonks.com)
 * @version $Revision$
 */
public class StatisticsMapper  implements RowMapper<Statistics> {

    @Override
    public Statistics mapRow(ResultSet rs, int rowNum) throws SQLException {
        if (rs == null) return null;
        Statistics keyOwner = new Statistics();
        keyOwner.setName(rs.getString("name"));
        keyOwner.setCategory(rs.getString("category"));
        keyOwner.setDtYear(rs.getInt("dt_year"));
        keyOwner.setDtMonth(rs.getInt("dt_month"));
        keyOwner.setDtDay(rs.getInt("dt_day"));
        keyOwner.setDtHour(rs.getInt("dt_hour"));
        keyOwner.setLastUpdated(rs.getDate("last_updated"));
        keyOwner.setTotalCalls(rs.getLong("total_calls"));
        keyOwner.setTotalTime(rs.getLong("total_time"));
        keyOwner.setLongest(rs.getLong("longest"));
        keyOwner.setShorttest(rs.getLong("shorttest"));
        return keyOwner;
    }
}
