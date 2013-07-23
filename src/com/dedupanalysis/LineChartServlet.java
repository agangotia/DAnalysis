package com.dedupanalysis;


import com.google.visualization.datasource.DataSourceServlet;
import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.value.ValueType;
import com.google.visualization.datasource.query.Query;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

/**
 * Servlet implementation class SimpleExampleServlet
 */

public class LineChartServlet extends DataSourceServlet {
	 @Override
	  public DataTable generateDataTable(Query query, HttpServletRequest request) {
	    // Create a data table.
		 
		 System.out.println("\n Lets Crank it");
		 ArrayList<FileStatsVO>  ALFileStats=DedupDAO.fillFileStats();
		 System.out.println("\n Cranked");
		 
		 
		
	    DataTable data = new DataTable();
	    ArrayList<ColumnDescription> cd = new ArrayList<ColumnDescription>();
	    cd.add(new ColumnDescription("FileExtension", ValueType.TEXT, "Description 1"));
	    cd.add(new ColumnDescription("Size", ValueType.NUMBER, "Size in Gb"));
	    cd.add(new ColumnDescription("Count", ValueType.NUMBER, "Number of Files"));
	    

	    data.addColumns(cd);

	    for(FileStatsVO temp : ALFileStats){
	    	
	    	try{
	    		String extension=temp.getExtension();
		    	Double a=temp.getSize();
		    	Double sizeGB=a/1048576;
		    	Long count=temp.getCount();
		    	System.out.println("\nAdding"+extension+" size:"+sizeGB+" count"+count);
		    	data.addRowFromValues(extension,sizeGB,count);
	    		
	    	}catch(TypeMismatchException e)
	    	{
	    		System.out.println("Invalid type!");
	    	}
	    	
	    	
	    	
	    }
	  
	   
	    return data;
	  }

	  /**
	   * NOTE: By default, this function returns true, which means that cross
	   * domain requests are rejected.
	   * This check is disabled here so examples can be used directly from the
	   * address bar of the browser. Bear in mind that this exposes your
	   * data source to xsrf attacks.
	   * If the only use of the data source url is from your application,
	   * that runs on the same domain, it is better to remain in restricted mode.
	   */
	  @Override
	  protected boolean isRestrictedAccessMode() {
	    return false;
	  }

}
