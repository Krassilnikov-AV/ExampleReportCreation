/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package example.exQuery;

import example.exConnected.ExampleConnection;

import java.io.IOException;
import java.sql.*;

/**
 * Класс ExQuery
 */
public class ExQuery {


	ExampleConnection exmpCon = new ExampleConnection();


	public void writeWithCompileQuery(int records) {
		PreparedStatement pstm;

		try {
			Connection connection = exmpCon.getPostConnection();
			connection.setAutoCommit(true);

			String compiledQuery = "INSERT INTO EMPLOYEE(EMPNO, EMPNM, DEPT, RANK, USERNAME)" +
				" VALUES" + "(?, ?, ?, ?, ?)";
			pstm = connection.prepareStatement(compiledQuery);

			long start = System.currentTimeMillis();

			for(int index = 1; index < records; index++) {
				pstm.setInt(1, index);
				pstm.setString(2, "emp number-"+index);
				pstm.setInt(3, index);
				pstm.setInt(4, index);
				pstm.setString(5, "username");

				long startInternal = System.currentTimeMillis();
				pstm.executeUpdate();
				System.out.println("each transaction time taken = " + (System.currentTimeMillis() - startInternal) + " ms");
			}

			long end = System.currentTimeMillis();
			System.out.println("total time taken = " + (end - start) + " ms");
			System.out.println("avg total time taken = " + (end - start)/ records + " ms");

			pstm.close();
			connection.close();

		} catch (SQLException ex) {
			System.err.println("SQLException information");
			while (ex != null) {
				System.err.println("Error msg: " + ex.getMessage());
				ex = ex.getNextException();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int[] writeInABatchWithCompiledQuery(int records) throws  IOException {
		PreparedStatement pstm;

		try {
			Connection connection = exmpCon.getPostConnection();
			connection.setAutoCommit(true);

			String compiledQuery = "INSERT INTO EMPLOYEE(EMPNO, EMPNM, DEPT, RANK, USERNAME)" +
				" VALUES" + "(?, ?, ?, ?, ?)";
			pstm = connection.prepareStatement(compiledQuery);

			for(int index = 1; index <= records; index++) {
				pstm.setInt(1, index);
				pstm.setString(2, "empo number-"+index);
				pstm.setInt(3, index+100);
				pstm.setInt(4, index+200);
				pstm.setString(5, "usernames");
				pstm.addBatch();
			}

			long start = System.currentTimeMillis();
			int[] inserted = pstm.executeBatch();
			long end = System.currentTimeMillis();

			System.out.println("total time taken to insert the batch = " + (end - start) + " ms");
			System.out.println("total time taken = " + (end - start)/records + " s");

			pstm.close();
			connection.close();

			return inserted;

		} catch (SQLException ex) {
			System.err.println("SQLException information");
			while (ex != null) {
				System.err.println("Error msg: " + ex.getMessage());
				ex = ex.getNextException();
			}
			throw new RuntimeException("Error");
		}
	}

	//	ExampleConnection exmpCon = new ExampleConnection();
//
//	String sqlQuery = "insert into PERSONS values (?,?,?,?)";
//try{
//		PreparedStatement pstmt = exmpCon.getPostConnection(sqlQuery);
//		connection.autoCommit(false);
//		for(int i=1; i<= 200;i++){
//			pstmt.setString(1,"Java");
//			pstmt.setString(2,"CodeGeeks");
//			pstmt.setInt(3,i);
//			pstmt.setInt(4,i);
//			pstmt.addBatch();
//		}
//		int[] result = pstmt.executeBatch();
//		System.out.println("The number of rows inserted: "+ result.length);
//		connection.commit();
//	}catch(Exception e){
//		e.printStackTrace();
//		connection.rollBack();
//	} finally{
//		if(pstmt!=null)
//			pstmt.close();
//		if(connection!=null)
//			connection.close();
//	}
}