/*
 * 编写一个 SQL 查询来实现分数排名。如果两个分数相同，则两个分数排名（Rank）相同。请注意，平分后的下一个名次应该是下一个连续的整数值。换句话说，名次之间不应该有“间隔”。

+----+-------+
| Id | Score |
+----+-------+
| 1  | 3.50  |
| 2  | 3.65  |
| 3  | 4.00  |
| 4  | 3.85  |
| 5  | 4.00  |
| 6  | 3.65  |
+----+-------+
例如，根据上述给定的 Scores 表，你的查询应该返回（按分数从高到低排列）：

+-------+------+
| Score | Rank |
+-------+------+
| 4.00  | 1    |
| 4.00  | 1    |
| 3.85  | 2    |
| 3.65  | 3    |
| 3.65  | 3    |
| 3.50  | 4    |
+-------+------+
*/

/*
 * COUNT(DISTINCT b.Score) FROM Scores b WHERE b.Score >= a.Score
 * 从成绩中去重后选出大于等于a.Score的记录有多少条就是排名多少
 * 比如a.Score = 3.85，成绩中去重后共有2条记录大于等于3.85，则a.Score排名就是2
 */
public class Number178 {
	/*
	 * SELECT a.Score as Score,
	   (SELECT COUNT(DISTINCT b.Score) FROM Scores b WHERE b.Score >= a.Score) AS Rank
	   FROM Scores a
	   ORDER BY a.Score DESC
	 */
}
