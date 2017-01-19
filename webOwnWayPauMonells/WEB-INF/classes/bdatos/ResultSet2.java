package bdatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.Hashtable;


public class ResultSet2 {


    private String[] columns;
    private Vector<String[]> rows;
    private int pointer;


    public ResultSet2(String[] columns) {
        this.columns=columns;
        rows=new Vector<String[]>();
        pointer=-1;
    }


	public int getPointer() {
		return pointer;
	}


    public void addRow(String[] row) {
        rows.addElement(row);
    }


    public ResultSet2(ResultSet rset) throws Exception {
        columns=new String[rset.getMetaData().getColumnCount()];
        for(int i=0;i<rset.getMetaData().getColumnCount();i++)
            columns[i]=rset.getMetaData().getColumnLabel(i+1);
        rows=new Vector<String[]>();
        String[] row;
        while(rset.next()) {
            row=new String[rset.getMetaData().getColumnCount()];
            for(int i=0;i<rset.getMetaData().getColumnCount();i++)
                row[i]=rset.getString(i+1);
            rows.addElement(row);
        }
        pointer=-1;
    }


    public void beforeFirst() {
        pointer=-1;
    }


    public boolean next() {
        if(pointer+1==rows.size())
            return false;
        else {
            pointer++;
            return true;
        }
    }


    public String getString(int index) throws Exception {
        String value=null;
        if((index<1)||(index>columns.length))
            throw new SQLException("Indice de columna no valido");
        else
            value=rows.elementAt(pointer)[index-1];
        return value;
    }


    public String getString(String column) throws Exception {
        String value=null;
        int index=0;
        for(int i=0;i<columns.length;i++)
            if(columns[i].toUpperCase().equals(column.toUpperCase()))
               index=i+1;
        if((index<1)||(index>columns.length))
             throw new SQLException("Nombre de columna no valido");
        else
            value=rows.elementAt(pointer)[index-1];
        return value;
    }


    public int getInt(int index) throws Exception {
		return Integer.parseInt(getString(index));
    }


    public int getInt(String column) throws Exception {
		return Integer.parseInt(getString(column));
    }


    public String toString() {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<columns.length;i++)
            sb.append(columns[i]+" ");
        sb.append("\n");
        for(String[] row:rows) {
            for(int i=0;i<columns.length;i++)
                sb.append(row[i]+" ");
            sb.append("\n");
        }
        return sb.toString();
    }


    public String toHtml() {
        StringBuilder sb=new StringBuilder();
        sb.append("<table><tr>");
        for(int i=0;i<columns.length;i++)
            sb.append("<th>"+columns[i]+"</th>");
        sb.append("</tr>");
        for(String[] row:rows) {
            sb.append("<tr>");
            for(int i=0;i<columns.length;i++)
                sb.append("<td>"+row[i]+"</td>");
            sb.append("</tr>");
        }
        sb.append("</table>");
        sb.append("<br><span class=\"correct\">"+rows.size()+" rows.</span><br>");
        return sb.toString();
    }


	public int rows() {
		return rows.size();
	}


	public int columns() {
		return columns.length;
	}

    public void close() {
        columns=null;
        rows.clear();
        pointer=-1;
    }

	public Hashtable toHashtable() {
		Hashtable<String,String> ht=new Hashtable<String,String>();
		for(int i=0;i<columns.length;i++)
			ht.put(columns[i],rows.elementAt(pointer)[i]);
		return ht;
	}



}

