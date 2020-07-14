## Part 1: Test it with SQL
SHOW COLUMNS FROM techjobs.job;
## Part 2: Test it with SQL
SELECT * FROM techjobs.employer WHERE location = 'St. Louis City';
## Part 3: Test it with SQL
DROP TABLE techjobs.job;
## Part 4: Test it with SQL
SELECT DISTINCT skillname, skilldescription, jobname
FROM 
(SELECT job.name as jobname, job_skills.skills_id as a
FROM job
INNER JOIN job_skills ON techjobs.job.id = techjobs.job_skills.jobs_id) as x
INNER JOIN
(SELECT DISTINCT skill.name as skillname, skill.description as skilldescription, job_skills.skills_id as b
FROM job_skills
INNER JOIN skill ON techjobs.job_skills.skills_id = techjobs.skill.id) as y
ON x.a = y.b ORDER BY y.skillname;