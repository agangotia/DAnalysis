package com.dedupanalysis.phase2;
import java.sql.Connection;

import java.sql.ResultSet;

import java.sql.Statement;
import java.util.ArrayList;

import com.dedupanalysis.FileStatsVO;



public class DedupDAOPh2 {
	
	public static ArrayList<MatchedFileVO>  getMatchedFilesDetails()
	{
		ArrayList<MatchedFileVO> ALMatchedFileStats=new ArrayList<MatchedFileVO>();
try {
			
			//new file count
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
			Connection conn =java.sql.DriverManager.getConnection("jdbc:sqlserver://VM-03105\\DEDUPXP;databaseName=DBDEDUPXP;user=root;password=Spring2013!1;");
			
			Statement stmt = conn.createStatement( );
			//String SQL = "SELECT TOP 20 A.FILE_EXTENSION,A.SIZES2,B.COUNT2 FROM (SELECT FILE_EXTENSION,SUM(SIZES) AS SIZES2 FROM [DBDEDUPXP].[dbo].[FILE_SIZES] GROUP BY FILE_EXTENSION ) A ,(SELECT FILE_EXTENSION,SUM(COUNT) AS COUNT2 FROM [DBDEDUPXP].[dbo].[FILE_COUNTS] GROUP BY FILE_EXTENSION ) B WHERE A.FILE_EXTENSION=B.FILE_EXTENSION ORDER BY A.SIZES2 DESC;";
			String SQL = "SELECT ACCOUNTID,COMPUTERID,FILE_NAME,FILE_EXTENSION,FILE_SIZE,COUNT FROM [DBDEDUPXP].[dbo].[MATCHEDFILES] order by COUNT desc;";
			ResultSet rs = stmt.executeQuery( SQL );
			
			
		
			
			while ( rs.next( ) ) {
				
				int accountID=rs.getInt("ACCOUNTID");
				int computerID=rs.getInt("COMPUTERID");
				String fileName = rs.getNString("FILE_NAME");
				String fileExtn = rs.getNString("FILE_EXTENSION");
				Double size = (double) rs.getDouble("FILE_SIZE");
				Long count = rs.getLong("COUNT");
				
				MatchedFileVO obj=new MatchedFileVO();
				obj.setAccountID(accountID);
				obj.setComputerID(computerID);
				obj.setCount(count);
				obj.setFileExtension(fileExtn);
				obj.setFileName(fileName);
				obj.setSize(size);
				ALMatchedFileStats.add(obj);
				//System.out.println("\n"+fileExtn+"\t"+count);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return ALMatchedFileStats;
	
	}

}
