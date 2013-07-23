package com.dedupanalysis.phase2;



import com.dedupanalysis.phase1wothers.DedupDAOPh1WOther;
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

public class Ph2MatchedFileStats extends DataSourceServlet {
	 @Override
	  public DataTable generateDataTable(Query query, HttpServletRequest request) {
	    // Create a data table.
		 
		 System.out.println("\n com.dedupanalysis.phase2.Ph2MatchedFileStats Lets Crank it");
		 ArrayList<MatchedFileVO>  ALMatchedFileStats=DedupDAOPh2.getMatchedFilesDetails();
		 System.out.println("\n Cranked");
		
		 
		 
		
	    DataTable data = new DataTable();
	    ArrayList<ColumnDescription> cd = new ArrayList<ColumnDescription>();
	    cd.add(new ColumnDescription("accountid", ValueType.NUMBER, "Account ID"));
	    cd.add(new ColumnDescription("compid", ValueType.NUMBER, "Computer ID"));
	    cd.add(new ColumnDescription("filename", ValueType.TEXT, "File Name"));
	    cd.add(new ColumnDescription("extension", ValueType.TEXT, "File Extension"));
	    cd.add(new ColumnDescription("size", ValueType.NUMBER, "Size in Gb"));
	    cd.add(new ColumnDescription("count", ValueType.NUMBER, "Count of Matched Files"));
	    

	    data.addColumns(cd);

	  
	    	
	    	for(MatchedFileVO temp : ALMatchedFileStats){
	    	
	    	try{
	    		Double a=temp.getSize();
		    	Double sizeGB=a/1048576;
		    	
		    	System.out.println("Adding "+sizeGB);
		    	data.addRowFromValues(temp.getAccountID(),temp.getComputerID(),temp.getFileName(),temp.getFileExtension(),sizeGB,temp.getCount());
	    		
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
