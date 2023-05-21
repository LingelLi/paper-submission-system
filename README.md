# 华东师范大学毕业论文管理系统



## 前端

`cd GraduationProjectManager-front`

`npm run dev`



> #### 登录
>
> 学生、教师、管理员使用同一个页面登录，自动跳转不同主页。 
>
> 默认账号：
>
> + 学生
>
>   用户名：学生 密码：123456
>
> + 教师
>
>   用户名：教师1 密码：123456
>
> + 管理员
>
>   用户名：管理员 密码：123456



## 后端

#### 1. 登录及用户管理后端

`cd GraduationProjectManager-api`

`使用 maven 管理依赖，使用IDEA + SpringBoot 运行项目`



#### 2. 论文提交及流程管理后端

`cd GraduationProjectThesisManagementSystem`

`使用 maven 管理依赖，使用IDEA + SpringBoot 运行项目`



## 数据库

将主目录下的 sql 文件导入数据库

> GraduationProjectManager-api 对应 graduationmanager 数据库
>
> GraduationProjectThesisManagementSystem 对应 paper 数据库



#### 状态码设计：
0   个人基本信息未确认

1   个人基本信息已确认，未提交论文预审材料

2   论文预审材料已提交，未预审

3   论文预审通过，未提交评审版论文

4   论文预审未通过（在论文预审页显示意见，可重新提交，重新提交后到状态2）

5   评审版论文已提交，未初评

6   论文初评通过，未提交答辩申请

7   论文初评未通过（在论文初评页显示意见，可重新提交，重新提交后到状态8）

8   评审版论文再次提交，未复评

9   论文复评通过，未提交答辩申请

10  论文复评未通过（流程终止，延毕）

11  答辩申请已提交，等待答辩

12  答辩通过，显示答辩意见，未提交学位申请

13  答辩未通过，显示答辩意见（流程终止，延毕）

14  提交学位申请

15  学位申请通过
