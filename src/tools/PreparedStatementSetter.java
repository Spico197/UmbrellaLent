package tools;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementSetter {
	public void setholder(PreparedStatement pstmt) 
			throws SQLException;
}
