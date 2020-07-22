/*
编写一个 SQL 查询，获取 Employee 表中第二高的薪水（Salary） 。

+----+--------+
| Id | Salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
例如上述 Employee 表，SQL查询应该返回 200 作为第二高的薪水。如果不存在第二高的薪水，那么查询应返回 null。

+---------------------+
| SecondHighestSalary |
+---------------------+
| 200                 |
+---------------------+
*/

/*
 * offset X是跳过X个数据，limit Y是选取Y个数据
 * limit  X,Y中X表示跳过X个数据，读取Y个数据
 * selete * from testtable limit 2,1;
 * 是从数据库中第三条开始查询，取一条数据，即第三条数据读取，一二条跳过
 * selete * from testtable limit 2 offset 1;
 * 是从数据库中的第二条数据开始查询两条数据，即第二条和第三条。
 */

public class Number176 {
	/*
	 * SELECT
    IFNULL(
      (SELECT DISTINCT Salary
       FROM Employee
       ORDER BY Salary DESC
        LIMIT 1 OFFSET 1),
    NULL) AS SecondHighestSalary
	 */
}
