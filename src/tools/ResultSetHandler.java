package tools;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetHandler {
	public void setResult(ResultSet rs)
			throws SQLException;
}
