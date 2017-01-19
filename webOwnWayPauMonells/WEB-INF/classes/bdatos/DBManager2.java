package bdatos;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBManager2 {


    private Connection conn=null;
    private Statement stmt=null;


    public DBManager2() throws Exception {
        javax.sql.DataSource ds=(javax.sql.DataSource)new javax.naming.InitialContext().lookup("java:comp/env/jdbc/GRATISJSP");
        //javax.sql.DataSource ds=(javax.sql.DataSource)new javax.naming.InitialContext().lookup("java:comp/env/jdbc/FORUM");
        conn=ds.getConnection();
        stmt=conn.createStatement();
    }


    public void close() throws Exception {
        stmt.close();
        conn.close();
    }


    public void execute(String sql) throws Exception {
        stmt.execute(sql);
    }


    public int executeUpdate(String sql) throws Exception {
        int changes=stmt.executeUpdate(sql);
        return changes;
    }


    public String executeValue(String sql) throws Exception {
        String value=null;
        ResultSet rset=stmt.executeQuery(sql);
        if(rset.next()) {
            value=rset.getString(1);
            rset.close();
        } else {
            rset.close();
            throw new SQLException("El valor no existe");
        }
        return value;
    }


    public ResultSet2 executeQuery(String sql) throws Exception {
        ResultSet rset=stmt.executeQuery(sql);
        ResultSet2 rset2=new ResultSet2(rset);
        rset.close();
        return rset2;
    }


    public String executeClever(String sql) {
        String html=null;
        try {
            if(sql.split(" ")[0].toLowerCase().equals("select")) {
                ResultSet2 rset2=null;
                ResultSet rset=stmt.executeQuery(sql);
                rset2=new ResultSet2(rset);
                rset.close();
				html=rset2.toHtml();
            } else if((sql.split(" ")[0].toLowerCase().equals("insert"))||(sql.split(" ")[0].toLowerCase().equals("update"))||(sql.split(" ")[0].toLowerCase().equals("delete"))) {
                int changes=0;
                changes=stmt.executeUpdate(sql);
                html="<span class=\"correct\">"+changes+" rows changed.</span>";
            } else {
                stmt.execute(sql);
                html="<span class=\"correct\">command executed.</span>";
            }
        } catch(Exception e) {
            html="<span class=\"error\">"+e+"</span>";
        }
        return html;
    }


    public String toString() {
        return ""+conn;
    }


	public String backup(String table) throws Exception {
        StringBuilder sb=new StringBuilder();
        ResultSet2 rset=executeQuery("select * from "+table);
        sb.append("insert into "+table+" values");
		while(rset.next()) {
			sb.append("(");
			for(int c=0;c<rset.columns();c++) {
				if(c>0)
					sb.append(",");
				sb.append("'"+rset.getString(c+1).replace("'","''")+"'");
			}
			sb.append("),");
		}
        return sb.substring(0,sb.length()-1);

	}


}

