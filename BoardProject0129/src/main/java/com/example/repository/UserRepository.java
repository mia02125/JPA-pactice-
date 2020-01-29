package com.example.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(Long Id); //리스트 형식이 아니면 쿼리가 2개 이상일 때 에러 발생
	User findByUserIdAndUserPw(String userId, String userPw);
	User[] findByUserName(String userName); //리스트 형식이 아니면 쿼리가 2개 이상일 때 에러 발생 
	// 첫번째 컬럼만 불러오기 때문에 배열이나 리스트형식으로 바꿔줘야한다.
}
