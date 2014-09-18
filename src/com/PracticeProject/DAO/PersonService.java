package com.PracticeProject.DAO;

import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.http.entity.StringEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.PracticeProject.Parse.ConnectParse;
import com.PracticeProject.readexcel.ReadExcel;



public class PersonService implements PersonDAO{

	ArrayList<JSONObject> excelData = new ReadExcel().readExcelFile();
	JSONObject josObject = new JSONObject();
	StringEntity input;
	Person person=new Person();

	@Override
	public void savePerson() {
		// TODO Auto-generated method stub
		String str;
		str = "https://www.parse.com/1/batch";
		System.out.println(str);  
		josObject.put("requests", excelData);

		try {
			input = new StringEntity(josObject.toString());
			new ConnectParse().httppostMethod(URI.create(str),input );
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updatePerson() {
		// TODO Auto-generated method stub
		Person person1 = getPerson();
		String str="mj";
		JSONObject josObject = new JSONObject();
		josObject.put("NAME", str);

		str = "https://www.parse.com/1/classes/Employees/"+person1.getId();
		System.out.println(str);
		StringEntity input;
		try {
			input = new StringEntity(josObject.toString());
			new ConnectParse().httpputMethod(URI.create(str),input );
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Person getPerson() {
		// TODO Auto-generated method stub
		try {
			JSONArray jsonArray = null;
			String str;

			String where = URLEncoder.encode("{\"NAME\":\"MARITA\"}","UTF-8");
			str = "https://www.parse.com/1/classes/Employees"+where;

			System.out.println(str);
			jsonArray = new ConnectParse().httpgetMethod(URI.create(str));

			System.out.println("R : "+jsonArray);
			Iterator<JSONObject> iterator = jsonArray.iterator();

			while (iterator.hasNext()) {
				JSONObject jsonObject = iterator.next();
				String id=(String) jsonObject.get("objectId");
				person.setId( id);

			}

			//		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return person;

	}

	@Override
	public void deletePerson() {
		// TODO Auto-generated method stub
		ArrayList<Person> arrayList = getAllPerson();
		String str;

		for(Person person1: arrayList){
			str = "https://www.parse.com/1/classes/Employees/"+person1.getId();
			System.out.println(str);  

			try {
				new ConnectParse().httpdeleteMethod(URI.create(str));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public ArrayList<Person> getAllPerson() {
		// TODO Auto-generated method stub
		ArrayList<Person> arrayList = new ArrayList<Person>();
		try {
			JSONArray jsonArray = null;
			String str;

			str = "https://www.parse.com/1/classes/Employees";

			System.out.println(str);
			jsonArray = new ConnectParse().httpgetMethod(URI.create(str));

			System.out.println("R : "+jsonArray);
			Iterator<JSONObject> iterator = jsonArray.iterator();

			while (iterator.hasNext()) {
				person = new Person();
				JSONObject jsonObject = iterator.next();
				String id=(String) jsonObject.get("objectId");
				person.setId( id);
				arrayList.add(person);

			}

			//		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayList;

	}

	public static void main(String[] args) {
		PersonService ps=new PersonService();
		Person person=new Person();
		ps.savePerson();
		ps.updatePerson();
		ps.deletePerson();
	}

}