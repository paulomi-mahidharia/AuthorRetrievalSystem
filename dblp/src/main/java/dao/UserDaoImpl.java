package dao;

public class UserDaoImpl implements UserDao{

	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		if(username.equals("") || username == null || password.equals("") || password == null){
			return false;
		}else{
			return true;
		}
	}

}
