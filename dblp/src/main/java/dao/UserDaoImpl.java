package dao;

public class UserDaoImpl implements UserDao{

	public boolean login(String username, String password) {
		//System.out.println(username);
		// TODO Auto-generated method stub
		if(username == null || username.isEmpty() || password == null || password.isEmpty()){
			return false;
		}else{
			return true;
		}
	}

}
