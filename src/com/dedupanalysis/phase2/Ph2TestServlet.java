package com.dedupanalysis.phase2;


import com.google.visualization.datasource.DataSourceServlet;
import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.value.ValueType;
import com.google.visualization.datasource.query.Query;
import java.util.ArrayList;


import javax.servlet.http.HttpServletRequest;

/**
 * Servlet implementation class SimpleExampleServlet
 */

public class Ph2TestServlet extends DataSourceServlet {
	 @Override
	  public DataTable generateDataTable(Query query, HttpServletRequest request) {
	    // Create a data table.
		 
		
		 
		 
		
	    DataTable data = new DataTable();
	    ArrayList<ColumnDescription> cd = new ArrayList<ColumnDescription>();
	    cd.add(new ColumnDescription("FileExtension", ValueType.TEXT, "Description 1"));
	    cd.add(new ColumnDescription("Size", ValueType.NUMBER, "Size in Gb"));
	    cd.add(new ColumnDescription("Count", ValueType.NUMBER, "Number of Files"));
	    

	    data.addColumns(cd);

	  
	    	
	    	try{
	    		
		    	data.addRowFromValues(".exe",500,1000);
		    	data.addRowFromValues(".mp3",1500,1000);
		    	data.addRowFromValues(".dat",600,1000);
	    		
	    	}catch(TypeMismatchException e)
	    	{
	    		System.out.println("Invalid type!");
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
