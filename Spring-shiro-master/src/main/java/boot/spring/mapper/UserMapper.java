package boot.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import boot.spring.po.User;

public interface UserMapper {
	List<User> getusers();
	User getUserByid(long userid);
	User getUserByusername(String username);
	void deleteuser(long userid);
	void deleteuserrole(@Param("userid")long userid,@Param("roleid")long roleid);
	void adduserrole(@Param("userid") long userid,@Param("roleid")long roleid);
	void adduser(User user);
	void updateuser(User user);
	void deleteuseroles(long uid);
}
