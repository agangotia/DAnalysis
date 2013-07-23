package com.dedupanalysis;
import java.sql.Connection;

import java.sql.ResultSet;

import java.sql.Statement;
import java.util.ArrayList;



public class DedupDAO {
	public static ArrayList<FileStatsVO>  fillFileStats()
	{
		ArrayList<FileStatsVO> ALFileStats=new ArrayList<FileStatsVO>();
		try {
			
			//new file count
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
			Connection conn =java.sql.DriverManager.getConnection("jdbc:sqlserver://VM-03105\\DEDUPXP;databaseName=DBDEDUPXP;user=root;password=Spring2013!1;");
			
			Statement stmt = conn.createStatement( );
			//String SQL = "SELECT TOP 20 A.FILE_EXTENSION,A.SIZES2,B.COUNT2 FROM (SELECT FILE_EXTENSION,SUM(SIZES) AS SIZES2 FROM [DBDEDUPXP].[dbo].[FILE_SIZES] GROUP BY FILE_EXTENSION ) A ,(SELECT FILE_EXTENSION,SUM(COUNT) AS COUNT2 FROM [DBDEDUPXP].[dbo].[FILE_COUNTS] GROUP BY FILE_EXTENSION ) B WHERE A.FILE_EXTENSION=B.FILE_EXTENSION ORDER BY A.SIZES2 DESC;";
			String SQL = "SELECT TOP 20 A.FILE_EXTENSION,A.SIZES2,B.COUNT2 FROM (SELECT FILE_EXTENSION,SUM(SIZES) AS SIZES2 FROM [DBDEDUPXP].[dbo].[FILE_SIZES_PH1] GROUP BY FILE_EXTENSION ) A ,(SELECT FILE_EXTENSION,SUM(COUNT) AS COUNT2 FROM [DBDEDUPXP].[dbo].[FILE_COUNTS_PH1] GROUP BY FILE_EXTENSION ) B WHERE A.FILE_EXTENSION=B.FILE_EXTENSION ORDER BY A.SIZES2 DESC;";
			ResultSet rs = stmt.executeQuery( SQL );
			
			
		
			
			while ( rs.next( ) ) {
				
				
				String fileExtn = rs.getNString("FILE_EXTENSION");
				Double size = (double) rs.getDouble("SIZES2");
				Long count = rs.getLong("COUNT2");
				
				FileStatsVO obj=new FileStatsVO();
				obj.setExtension(fileExtn);
				obj.setSize(size);
				obj.setCount(count);
				ALFileStats.add(obj);
				//System.out.println("\n"+fileExtn+"\t"+count);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return ALFileStats;
	}

	public static ArrayList<FileStatsVO>  fillFileStatsByCount()
	{
		ArrayList<FileStatsVO> ALFileStats=new ArrayList<FileStatsVO>();
		try {
			
			//new file count
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
			Connection conn =java.sql.DriverManager.getConnection("jdbc:sqlserver://VM-03105\\DEDUPXP;databaseName=DBDEDUPXP;user=root;password=Spring2013!1;");
			
			Statement stmt = conn.createStatement( );
			//String SQL = "SELECT TOP 20 A.FILE_EXTENSION,A.SIZES2,B.COUNT2 FROM (SELECT FILE_EXTENSION,SUM(SIZES) AS SIZES2 FROM [DBDEDUPXP].[dbo].[FILE_SIZES] GROUP BY FILE_EXTENSION ) A ,(SELECT FILE_EXTENSION,SUM(COUNT) AS COUNT2 FROM [DBDEDUPXP].[dbo].[FILE_COUNTS] GROUP BY FILE_EXTENSION ) B WHERE A.FILE_EXTENSION=B.FILE_EXTENSION ORDER BY B.COUNT2 DESC;";
			String SQL = "SELECT TOP 20 A.FILE_EXTENSION,A.SIZES2,B.COUNT2 FROM (SELECT FILE_EXTENSION,SUM(SIZES) AS SIZES2 FROM [DBDEDUPXP].[dbo].[FILE_SIZES_PH1] GROUP BY FILE_EXTENSION ) A ,(SELECT FILE_EXTENSION,SUM(COUNT) AS COUNT2 FROM [DBDEDUPXP].[dbo].[FILE_COUNTS_PH1] GROUP BY FILE_EXTENSION ) B WHERE A.FILE_EXTENSION=B.FILE_EXTENSION ORDER BY B.COUNT2 DESC;";
			ResultSet rs = stmt.executeQuery( SQL );
			
			
		
			
			while ( rs.next( ) ) {
				
				
				String fileExtn = rs.getNString("FILE_EXTENSION");
				Double size = (double) rs.getDouble("SIZES2");
				Long count = rs.getLong("COUNT2");
				
				FileStatsVO obj=new FileStatsVO();
				obj.setExtension(fileExtn);
				obj.setSize(size);
				obj.setCount(count);
				ALFileStats.add(obj);
				//System.out.println("\n"+fileExtn+"\t"+count);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return ALFileStats;
	}

}
