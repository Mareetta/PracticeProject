package com.PracticeProject.DAO;

import java.util.ArrayList;



public interface PersonDAO {
	public void savePerson();
	public void updatePerson();
	public Person getPerson();
	public void deletePerson();
	public void deleteCertainPerson();
	public ArrayList<Person> getAllPerson();

}
