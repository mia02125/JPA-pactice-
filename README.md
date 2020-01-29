
# 스스로 공부하고 만들어가는 SpringBoot

## 회원가입 
### MainController

```java
	@GetMapping("/join")
	public String join() { 
		return "join";
	}

```

<br><br><br>

### User(model)

```java
Data
@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String userId;
	private String userPw;
	private String userName;
	private String userEmail;
	private String signUpDate;
}
```

<br><br><br>

### UserController

```java
@PostMapping("/joinRequest")
	public String join(@RequestParam Map<String, String> paramMap) { 
		String userId = paramMap.get("userId");
		String userPw = paramMap.get("userPw");
		String userName = paramMap.get("userName");
		String userEmail = paramMap.get("userEmail");
		String signUpDate = paramMap.get("signUpDate");
		String page = userService.join(userId, userPw, userName, userEmail, signUpDate);
		return page;
	}

```

<br><br><br>

### userPwHashCode

```java
@Service
public class UserPwHashCode {
	public String getSHA256(String plainText) { 
		
		Logger log = LoggerFactory.getLogger(this.getClass());
		
		String shaString = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(plainText.getBytes());
			byte byteData[] = digest.digest();
			StringBuffer buffer = new StringBuffer();
			for(int i = 0; i < byteData.length; i++) { 
				buffer.append(Integer.toString((byteData[i] & 0xff) * 0x100, 16).substring(1));
			}
			shaString = buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("hashCode Error");
			shaString = null;
		}
		return shaString;
	}
}
```

<br><br><br>

### join.html

```
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Insert title here</title>
</head>
<script type="text/javascript">
  	$(function() { 
  		$('#userPwCheck').blur(function() { 
  		if($('#userPw').val() != $('#userPwCheck').val()) {
  			if($('#userPwCheck').val()!='') {
  				alert("비밀번호가 일치하지않습니다.");
  				$('#userPwCheck').val('');
  				$('#userPwCheck').blur();
  			}
  		}
  	})
});
  </script>
<body>
<form action="/joinRequest" method="post">
<input type="text" placeholder="ID" name = "userId"/>
<input type="password" placeholder="PW" name = "userPw" id = "userPw"/>
<input type="password" placeholder="PW확인" name="userPwCheck" id = "userPwCheck"/>
<input type="text" placeholder="NAME" name = "userName"/>
<input type="text" placeholder="EMAIL" name = "userEmail"/>
<input type="submit" id="signUpBtn" value = "가입"/>
</form>
</body>
</html>
```

<br><br><br>
<h2>.</h2>
<h2>.</h2>
<h1>로그인 생략하고 <Strong>아이디 찾기</Strong></h1> 

```html

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<title>find</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<form action="/findRequest" method="post">
		<h3>가입되어있는 정보를 알기위해서 이름을 입력하세요</h3>
		<input type="text" name="userName"> 
		<input type="submit" value="찾기">
		<table th:if="${session.findUser != null}">
			<thead>
				<tr>
					<th width="30">ID</th>
				</tr>
				<th:block th:each="User:${session.findUser}"> <!-- new ArrayList<Freeboard>()의 boardList의 내용을 board 이라함  -->
				<tr>
					<td width="30" style="text-align: center"><span th:text="${User.getUserId()}"></span></td>
				</tr>
				</th:block>
			</thead>
		</table>	
	</form>
	<a href="/">시작페이지로</a>
</body>
</html>
```

<br><br><br>

### UserController
```java
@PostMapping("/findRequest")
	public String find(@RequestParam Map<String, String> paramMap) {
		String userName = paramMap.get("userName");
		String page = userService.find(userName);
		return page; 		
	}
```

<br><br><br>

### UserRepository

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUserIdAndUserPw(String userId, String userPw);
	User[] findByUserName(String userName); //리스트 형식이 아니면 쿼리가 2개 이상일 때 에러 발생 
	// 첫번째 컬럼만 불러오기 때문에 배열이나 리스트형식으로 바꿔줘야한다.
}

```

<br><br><br>

### UserSevice 
```java
	//아이디 찾기
	public String find(String userName) {
		try {
			User[] user = userRepository.findByUserName(userName);
			if(user == null) { 
				return "find";
			} else {
				session.setAttribute("findUser", user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("findService Error");
		}
		return "find"; 
	}
```
