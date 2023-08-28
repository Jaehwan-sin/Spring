package com.tech.sWeb05.dao;

import java.util.ArrayList;

import com.tech.sWeb05.dto.Emp;
import com.tech.sWeb05.dto3.Student2;

public interface ReviewDao {
	public ArrayList<Emp> getDeptEmpJoin();
	public ArrayList<Emp> getEmp();
	public ArrayList<Student2> getStu2SubGrade();

}
