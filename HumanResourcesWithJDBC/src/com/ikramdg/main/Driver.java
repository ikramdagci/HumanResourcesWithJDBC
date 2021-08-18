package com.ikramdg.main;

import com.ikramdg.core.Program;
import com.ikramdg.core.ProgramBuilder;
import com.ikramdg.util.DBConnector;
import com.ikramdg.util.DBInitializer;

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
