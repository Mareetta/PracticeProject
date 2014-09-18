package com.PracticeProject.monitor;

import java.io.File;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;




import com.PracticeProject.DAO.PersonService;
import com.PracticeProject.readexcel.ExcelFileReader;




public class DirectoryMonitor extends ExcelFileReader  {
	public static void main(String args[]) {


		TimerTask task = new FileWatcher(new File("E:/ceino.xlsx"))
		{

			@Override
			protected void onChange(File file) {
				// TODO Auto-generated method stub
				System.out.println( "File "+ file.getName() +" has changes !" );
				DirectoryMonitor dm=new DirectoryMonitor();
				//Person person=new Person();
				PersonService ps=new PersonService();

				dm.readExcelFile();
				ps.deletePerson();
				ps.savePerson();
			}

		}		   ;


		Timer timer = new Timer();
		timer.schedule( task , new Date(), 5000 );
	}

}

