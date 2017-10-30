package kr.or.dgit.mybatis_code.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.mybatis_code.dao.StudentDao;
import kr.or.dgit.mybatis_code.dto.Student;
import kr.or.dgit.mybatis_code.utill.MyBatisSqlSessionFactory;

public class StudentService {
	private static final String nameSpace = "kr.or.dgit.mybatis_code.dao.StudentDao.";
	
	public List<Student> selectAllStudent(){
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();){
			StudentDao dao = sqlSession.getMapper(StudentDao.class);
			return dao.findAllStudents();
		}
	}

	public int createStudent(Student student) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.openSession()){
			StudentDao dao = sqlSession.getMapper(StudentDao.class);
			int res =dao.insertStudent(student);
			sqlSession.commit();
			return res;
		}
	}	

	
	public Student selectStudentByNo(int studId) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.openSession()){
			return sqlSession.selectOne(nameSpace + "findStudentById", studId);
		}
	}
	
	public int deleteStudentByNo(Student student) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.openSession()){
			int res = sqlSession.delete(nameSpace + "deleteStudent", student.getStudId());
			sqlSession.commit();
			return res;
		}
	}
}
