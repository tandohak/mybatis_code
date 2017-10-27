package kr.or.dgit.mybatis_code.utill;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class MyBatisSqlSessionFactory {
	private static SqlSessionFactory sqlSessionFactroy;

	public static SqlSessionFactory getSqlsessionfactroy() {
		if(sqlSessionFactroy == null) {
			try (InputStream is = Resources.getResourceAsStream("mybatis-config.xml");){
				sqlSessionFactroy = new SqlSessionFactoryBuilder().build(is);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Mybatis 설정 확인 요망");
				throw new RuntimeException(e.getCause());
			}
		}
		return sqlSessionFactroy;
	}
	
	public static SqlSession openSession() {
		return sqlSessionFactroy.openSession();
	}
}
