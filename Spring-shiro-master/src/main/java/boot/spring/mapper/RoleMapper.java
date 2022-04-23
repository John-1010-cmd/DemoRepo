package boot.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import boot.spring.po.Role;


public interface RoleMapper {
	List<Role> getRoles();
	void addRole(Role role);
	void deleterole(long roleid);//删除一个角色
	void addRolePermission(@Param("roleid")long roleid,@Param("permissionid")long permissionid);
	void deleterole_permission(@Param("roleid")long roleid,@Param("permissionid")long permissionid);//删除一个角色和一个权限的关联
	void deleteroles(long roleid);//删除一个角色的所有权限关联
	Role getRolebyid(long roleid);
	void updateRole(Role role);
}
