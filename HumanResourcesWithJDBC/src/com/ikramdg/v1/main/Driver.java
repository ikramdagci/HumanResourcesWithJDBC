package com.ikramdg.v1.main;

import com.ikramdg.v1.core.Program;
import com.ikramdg.v1.core.ProgramBuilder;
import com.ikramdg.v1.util.DBConnector;
import com.ikramdg.v1.util.DBInitializer;

public class Driver {

	public static void main(String[] args) {
		DBInitializer.init(DBConnector.connect());
		Program program = ProgramBuilder.build(args);
		try {
			program.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
