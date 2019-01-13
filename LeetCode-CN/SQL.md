## SQL

### 175. 组合两个表（easy）

### 题目 

	表1: Person
	
	+-------------+---------+
	| 列名         | 类型     |
	+-------------+---------+
	| PersonId    | int     |
	| FirstName   | varchar |
	| LastName    | varchar |
	+-------------+---------+
	PersonId 是上表主键
	表2: Address
	
	+-------------+---------+
	| 列名         | 类型    |
	+-------------+---------+
	| AddressId   | int     |
	| PersonId    | int     |
	| City        | varchar |
	| State       | varchar |
	+-------------+---------+
	AddressId 是上表主键
 

编写一个 SQL 查询，满足条件：无论 person 是否有地址信息，都需要基于上述两表提供 person 的以下信息：

	FirstName, LastName, City, State


### 思路

1.内连接（inner  join），2.左连接（left  join），3.右连接（right  join），4完全连接（full  join），看了就知道怎么写了。（看一下^_^）点击[打开链接](https://blog.csdn.net/qq_38192784/article/details/74469066)

### sql

```
# Write your MySQL query statement below
select  p.FirstName, p.LastName, a.City, a.State from Person p left join Address a on p.PersonId = a.PersonId
```

### 181. 超过经理收入的员工(easy)

### 题目

SQL架构
Employee 表包含所有员工，他们的经理也属于员工。每个员工都有一个 Id，此外还有一列对应员工的经理的 Id。

	+----+-------+--------+-----------+
	| Id | Name  | Salary | ManagerId |
	+----+-------+--------+-----------+
	| 1  | Joe   | 70000  | 3         |
	| 2  | Henry | 80000  | 4         |
	| 3  | Sam   | 60000  | NULL      |
	| 4  | Max   | 90000  | NULL      |
	+----+-------+--------+-----------+
给定 Employee 表，编写一个 SQL 查询，该查询可以获取收入超过他们经理的员工的姓名。在上面的表格中，Joe 是唯一一个收入超过他的经理的员工。
	
	+----------+
	| Employee |
	+----------+
	| Joe      |
	+----------+

### 思路

	解析：生成两个实例对象进行内交通过ManagerId和Id，然后限制条件是一个Salary大于另一个即可：


### sql

```
# Write your MySQL query statement below
select e1.Name as Employee from Employee e1, Employee e2 where e1.ManagerId = e2.Id  and e1.Salary > e2.Salary 
```






### 182. 查找重复的电子邮箱(easy)

### 题目

SQL架构
编写一个 SQL 查询，查找 Person 表中所有重复的电子邮箱。

示例：

	+----+---------+
	| Id | Email   |
	+----+---------+
	| 1  | a@b.com |
	| 2  | c@d.com |
	| 3  | a@b.com |
	+----+---------+
根据以上输入，你的查询应返回以下结果：

	+---------+
	| Email   |
	+---------+
	| a@b.com |
	+---------+

说明：所有电子邮箱都是小写字母。

### 思路

	此题比较简单，寻找重复的数据即可，用group by Email分组后 数据个数大于1的就是重复的


### sql

```
# Write your MySQL query statement below
select Email from Person group by Email having count(Email) > 1
```


### 183. 从不订购的客户(easy)

### 题目

SQL架构
某网站包含两个表，Customers 表和 Orders 表。编写一个 SQL 查询，找出所有从不订购任何东西的客户。

Customers 表：

	+----+-------+
	| Id | Name  |
	+----+-------+
	| 1  | Joe   |
	| 2  | Henry |
	| 3  | Sam   |
	| 4  | Max   |
	+----+-------+
Orders 表：

	+----+------------+
	| Id | CustomerId |
	+----+------------+
	| 1  | 3          |
	| 2  | 1          |
	+----+------------+
例如给定上述表格，你的查询应返回：

	+-----------+
	| Customers |
	+-----------+
	| Henry     |
	| Max       |
	+-----------+

### 思路

	此题比较简单，直接让两个表左外连接，然后只要找出右边的CustomerId为Null的顾客就是没有下订单的顾客。



数据库在通过连接两张或多张表来返回记录时，都会生成一张中间的临时表，然后再将这张临时表返回给用户。 在使用left jion时，on和where条件的区别如下：

- 1、on条件是在生成临时表时使用的条件，它不管on中的条件是否为真，都会返回左边表中的记录。
- 2、where条件是在临时表生成好后，再对临时表进行过滤的条件。这时已经没有left join的含义（必须返回左边表的记录）了，条件不为真的就全部过滤掉。

### sql

```
# Write your MySQL query statement below
select c.Name Customers from Customers c left join Orders o on c.Id = o.CustomerId where o.CustomerId is null
```

