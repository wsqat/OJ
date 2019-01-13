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



### 196. 删除重复的电子邮箱（easy）

### 题目 

编写一个 SQL 查询，来删除 Person 表中所有重复的电子邮箱，重复的邮箱里只保留 Id 最小 的那个。

	+----+------------------+
	| Id | Email            |
	+----+------------------+
	| 1  | john@example.com |
	| 2  | bob@example.com  |
	| 3  | john@example.com |
	+----+------------------+

Id 是这个表的主键。
例如，在运行你的查询语句之后，上面的 Person 表应返回以下几行:
	
	+----+------------------+
	| Id | Email            |
	+----+------------------+
	| 1  | john@example.com |
	| 2  | bob@example.com  |
	+----+------------------+


### 思路

此题使用内连接让两个表以邮箱关联起来，然后把相同邮箱且Id大的删除掉。


### sql

```
# Write your MySQL query statement below
 delete p2 from Person p1 join Person p2 on p1.Email = p2.Email where p2.Id > p1.Id
```


### 197. 上升的温度（easy）

### 题目 


给定一个 Weather 表，编写一个 SQL 查询，来查找与之前（昨天的）日期相比温度更高的所有日期的 Id。

	+---------+------------------+------------------+
	| Id(INT) | RecordDate(DATE) | Temperature(INT) |
	+---------+------------------+------------------+
	|       1 |       2015-01-01 |               10 |
	|       2 |       2015-01-02 |               25 |
	|       3 |       2015-01-03 |               20 |
	|       4 |       2015-01-04 |               30 |
	+---------+------------------+------------------+
	例如，根据上述给定的 Weather 表格，返回如下 Id:
	
	+----+
	| Id |
	+----+
	|  2 |
	|  4 |
	+----+

### 思路

#### 1

这道题给了我们一个Weather表，让我们找出比前一天温度高的Id，由于Id的排列未必是按顺序的，所以我们要找前一天就得根据日期来找，我们可以使用MySQL的函数Datadiff来计算两个日期的差值，我们的限制条件是温度高且日期差1。

#### 2

下面这种解法我们使用了MySQL的TO_DAYS函数，用来将日期换算成天数，其余跟上面相同。


#### 3

我们也可以使用Subdate函数，来实现日期减1，参见代码如下。


#### 4

最后来一种完全不一样的解法，使用了两个变量pre_t和pre_d分别表示上一个温度和上一个日期，然后当前温度要大于上一温度，且日期差为1，满足上述两条件的话选出来为Id，否则为NULL，然后更新pre_t和pre_d为当前的值，最后选出的Id不为空即可。





### sql

```
# Write your MySQL query statement below

SELECT w1.Id FROM Weather w1, Weather w2
WHERE w1.Temperature > w2.Temperature AND DATEDIFF(w1.RecordDate, w2.RecordDate) = 1; # 831ms

SELECT w1.Id FROM Weather w1, Weather w2
WHERE w1.Temperature > w2.Temperature AND TO_DAYS(w1.Date) = TO_DAYS(w2.Date) + 1;


SELECT w1.Id FROM Weather w1, Weather w2
WHERE w1.Temperature > w2.Temperature AND SUBDATE(w1.Date, 1) = w2.Date;


SELECT Id FROM (
SELECT CASE WHEN Temperature > @pre_t AND DATEDIFF(Date, @pre_d) = 1 THEN Id ELSE NULL END AS Id,
@pre_t := Temperature, @pre_d := Date 
FROM Weather, (SELECT @pre_t := NULL, @pre_d := NULL) AS init ORDER BY Date ASC
) id WHERE Id IS NOT NULL;


```



