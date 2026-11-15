import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class PassLoader {
    public static int loadAll(Connection con, List<String[]> passes) throws SQLException {
        String sql = "INSERT INTO bus_pass(pass_id, reg_no, name, route_no, amount) VALUES (?, ?, ?, ?, ?)";
        boolean oldAutoCommit = con.getAutoCommit();
        con.setAutoCommit(false);
        int totalRows = 0;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            for (String[] p : passes) {
                if (p == null || p.length != 5) {
                    throw new SQLException("Invalid pass row: " + Arrays.toString(p));
                }

                ps.setString(1, p[0]);
                ps.setString(2, p[1]);
                ps.setString(3, p[2]);
                ps.setInt(4, Integer.parseInt(p[3]));
                ps.setDouble(5, Double.parseDouble(p[4]));
                ps.addBatch();
                totalRows++;

                if (totalRows % 1000 == 0) {
                    ps.executeBatch();
                }
            }

            if (totalRows % 1000 != 0) {
                ps.executeBatch();
            }

            con.commit();
            return totalRows;
        } catch (SQLException | RuntimeException e) {
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(oldAutoCommit);
        }
    }
}
