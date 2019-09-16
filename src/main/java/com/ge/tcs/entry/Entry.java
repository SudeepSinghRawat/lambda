package com.ge.tcs.entry;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.ge.tcs.database.DBManager;
import com.ge.tcs.entity.TableOne;

public class Entry implements RequestHandler<Map, String> {

	public String handleRequest(Map input, Context context) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		DBManager.test();
		System.out.println(DBManager.getAllDetialFromtable());
		System.out.println(DBManager.getUserDetial(23));
		TableOne t = new TableOne(6,"Dharitri",10);
		System.out.println(DBManager.insertData(t));
	}

}
