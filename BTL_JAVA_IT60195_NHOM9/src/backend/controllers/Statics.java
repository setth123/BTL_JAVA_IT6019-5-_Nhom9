package backend.controllers;

import backend.utils.ReadData;

public class Statics {
	public static int books() {
		return ReadData.readBook("/BTL_JAVA_IT60195_NHOM9/src/backend/DemoDB/Book.txt").size();
		
	}
	public static int users() {
		return ReadData.readAccount("/BTL_JAVA_IT60195_NHOM9/src/backend/DemoDB/user-account.txt").size();
	}
}
