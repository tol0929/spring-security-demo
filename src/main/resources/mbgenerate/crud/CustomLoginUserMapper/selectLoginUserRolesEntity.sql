select
	login_user.*,
	roles.*
from
	login_user
left join user_role on
	user_role.user_id = login_user.id
left join roles on
	user_role.role_id = roles.id
where
    login_user.id = /*[# mb:p="userId"]*/ '' /*[/]*/;