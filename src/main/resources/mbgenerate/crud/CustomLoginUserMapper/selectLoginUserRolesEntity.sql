select
	login_user.name,
	login_user.email,
	login_user.password,
	roles.name as role_name
from
	login_user
left join user_role on
	user_role.user_id = login_user.id
left join roles on
	user_role.role_id = roles.id
where
    login_user.email = /*[# mb:p="email"]*/ '' /*[/]*/;